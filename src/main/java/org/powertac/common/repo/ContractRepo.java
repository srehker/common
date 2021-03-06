package org.powertac.common.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.powertac.common.Broker;
import org.powertac.common.Contract;
import org.powertac.common.TimeService;
import org.powertac.common.enumerations.PowerType;
import org.powertac.common.msg.ContractOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepo implements DomainRepo {
	static private Logger log = Logger.getLogger(ContractRepo.class.getName());

	 @Autowired
	 private TimeService timeService;

	private HashMap<Long, ContractOffer> offers;
	private HashSet<Long> deletedContracts;
	private HashMap<PowerType, Contract> defaultContracts;
	private HashMap<Long, Contract> contracts;
	private HashMap<Long, LinkedList<Contract>> brokerContracts;

	public ContractRepo() {
		super();
		offers = new HashMap<Long, ContractOffer>();
		deletedContracts = new HashSet<Long>();
		defaultContracts = new HashMap<PowerType, Contract>();
		contracts = new HashMap<Long, Contract>();
		brokerContracts = new HashMap<Long, LinkedList<Contract>>();
	}

	/**
	 * Adds a ContractOffer to the repo just in case another offer (or this one)
	 * has not already been added sometime in the past.
	 */
	public synchronized void addSpecification(ContractOffer offer) {
		if (isRemoved(offer.getId()) || null != offers.get(offer.getId())) {
			log.error("Attempt to insert Contract offer with duplicate ID "
					+ offer.getId());
			return;
		}
		offers.put(offer.getId(), offer);
	}

	public void removeSpecification(long id) {
		offers.remove(id);
	}

	public void setDefaultContract(ContractOffer newSpec) {
		addSpecification(newSpec);
		Contract Contract = new Contract(newSpec, timeService.getCurrentTime().getMillis());
		Contract.init();
		defaultContracts.put(newSpec.getPowerType(), Contract);
	}

	public Contract getDefaultContract(PowerType type) {
		Contract result = defaultContracts.get(type);
		if (null == result) {
			result = defaultContracts.get(type.getGenericType());
		}
		if (null == result) {
			log.error("Cannot find default Contract for PowerType " + type);
		}
		return result;
	}

	public synchronized ContractOffer findSpecificationById(long id) {
		return offers.get(id);
	}

	public synchronized List<ContractOffer> findContractOffersByBroker(
			Broker broker) {
		List<ContractOffer> result = new ArrayList<ContractOffer>();
		for (ContractOffer offer : offers.values()) {
			if (offer.getBroker() == broker) {
				result.add(offer);
			}
		}
		return result;
	}


	public synchronized List<ContractOffer> findAllContractOffers() {
		return new ArrayList<ContractOffer>(offers.values());
	}

	public synchronized void addContract(Contract Contract) {
		// add to the Contracts list
		if (isRemoved(Contract.getId())
				|| null != contracts.get(Contract.getId())) {
			log.error("Attempt to insert Contract with duplicate ID "
					+ Contract.getId());
			return;
		}
		contracts.put(Contract.getId(), Contract);

		// add to the brokerContracts list
		LinkedList<Contract> ContractList = brokerContracts.get(Contract
				.getBroker().getId());
		if (null == ContractList) {
			ContractList = new LinkedList<Contract>();
			brokerContracts.put(Contract.getBroker().getId(), ContractList);
		}
		ContractList.push(Contract);
	}

	public synchronized Contract findContractById(long id) {
		return contracts.get(id);
	}


	public List<Contract> findContractsByBroker(Broker broker) {
		List<Contract> result = brokerContracts.get(broker.getId());
		if (null == result)
			return new LinkedList<Contract>();
		else
			return result;
	}

	/**
	 * Removes a Contract and its offerification from the repo. remembers that
	 * Contract has been removed, prevents re-adding.
	 */
	public synchronized void removeContract(Contract Contract) {
		contracts.remove(Contract.getId());
		deletedContracts.add(Contract.getId());
		removeSpecification(Contract.getId());
	}

	/**
	 * Deletes a Contract and its offerification from the repo, without
	 * tracking. Should not be used in the server.
	 */
	public synchronized void deleteContract(Contract contract) {
		contracts.remove(contract.getId());
		List<Contract> bt = brokerContracts.get(contract.getBroker().getId());
		bt.remove(contract);
		removeSpecification(contract.getId());
	}

	/**
	 * Tests whether a Contract has been deleted.
	 */
	public synchronized boolean isRemoved(long ContractId) {
		return deletedContracts.contains(ContractId);
	}

	@Override
	public synchronized void recycle() {
		offers.clear();
		contracts.clear();
		defaultContracts.clear();
		deletedContracts.clear();
		brokerContracts.clear();
	}

}
