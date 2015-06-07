package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Domain (fields = {"broker", "contractId"})
@XStreamAlias("contract-confirm")
public class ContractConfirm extends ContractNegotiationMessage{
	public ContractConfirm (Broker broker, ContractAccept contract)
	  {
	    super(broker, contract.getCustomerId());
	    this.contractId = contract.getContractId();
	  }
	
	public ContractConfirm (Broker broker, ContractDecommit contract)
	  {
	    super(broker, contract.getCustomerId());
	    this.contractId = contract.getContractId();
	  }
	  
	  // protected default constructor for reflection
	  protected ContractConfirm ()
	  {
	    super();
	  }
}
