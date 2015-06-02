package org.powertac.common.msg;

import org.powertac.common.Broker;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("contract-update")
public abstract class ContractUpdate extends ContractNegotiationMessage{
	
	

	  
	  public ContractUpdate (Broker broker, ContractOffer contractOffer)
	  {
	    super(broker, contractOffer.customerId);
	    this.contractId = contractOffer.getContractId();
	  }
	  
	  public ContractUpdate (Broker broker, long contractId, long customerId)
	  {
	    super(broker, customerId);
	    this.contractId = contractId;
	  }

	  
	  // protected constructor for simplified deserialization
	  protected ContractUpdate ()
	  {
	    super();
	  }

}
