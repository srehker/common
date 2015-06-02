package org.powertac.common;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.powertac.common.enumerations.PowerType;
import org.powertac.common.msg.ContractOffer;
import org.powertac.common.repo.ContractRepo;
import org.powertac.common.spring.SpringApplicationContext;
import org.powertac.common.state.Domain;

@Domain
public class Contract {

	private long id;
	private double energyPrice; // per kWh
	private double peakLoadPrice; // per Month per kWh
	private DateTime startDate;
	private DateTime endDate;
	private double earlyExitFee; // when DECOMMIT is send (also before
									// startDate)

	public enum State {
		PENDING, OFFERED, ACCEPTED, WITHDRAWN, KILLED
	}

	private TimeService timeService;

	private ContractRepo contractRepo;

	private long offerId;

	private ContractOffer contractOffer;

	private Instant offerDate;

	/** The broker behind this contract */
	private Broker broker;

	/** Current state of this contract */
	private State state = State.PENDING;

	public Contract(ContractOffer offer) {
		id= IdGenerator.createId();
		System.out.println("Created contract with id="+id);
		setContractOffer(offer);
		setOfferId(offer.getId());
		setBroker(offer.getBroker());
		offer.setContractId(id);		
	}

	/**
	 * Initializes contract setting offerdate.
	 */
	public boolean init() {
		if (null == timeService)
			timeService = (TimeService) SpringApplicationContext
					.getBean("timeService");
		if (null == contractRepo)
			contractRepo = (ContractRepo) SpringApplicationContext
					.getBean("contractRepo");

		setOfferDate(timeService.getCurrentTime());

		
		contractRepo.addContract(this);

		return true;
	}

	@Deprecated
	public Contract(double energyPrice, double peakLoadPrice,
			DateTime startDate, DateTime endDate, double earlyExitFee) {
		super();
		this.energyPrice = energyPrice;
		this.peakLoadPrice = peakLoadPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.earlyExitFee = earlyExitFee;
	}

	public double getEnergyPrice() {
		return energyPrice;
	}

	public void setEnergyPrice(double energyPrice) {
		this.energyPrice = energyPrice;
	}

	public double getPeakLoadPrice() {
		return peakLoadPrice;
	}

	public void setPeakLoadPrice(double peakLoadPrice) {
		this.peakLoadPrice = peakLoadPrice;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public double getEarlyExitFee() {
		return earlyExitFee;
	}

	public void setEarlyExitFee(double earlyExitFee) {
		this.earlyExitFee = earlyExitFee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public Instant getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Instant offerDate) {
		this.offerDate = offerDate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ContractOffer getContractOffer() {
		return contractOffer;
	}

	public void setContractOffer(ContractOffer contractOffer) {
		this.contractOffer = contractOffer;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public PowerType getPowerType() {
		return contractOffer.getPowerType();
	}

}
