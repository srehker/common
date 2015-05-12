package org.powertac.common.msg;

import org.powertac.common.Broker;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("contract-update")
public abstract class ContractUpdate extends ContractNegotiationMessage{
	
	
	@XStreamAsAttribute
	private long contractId;
	  
	  public ContractUpdate (Broker broker, ContractOffer offeredContract)
	  {
	    super(broker);
	    this.contractId = offeredContract.getId();
	  }
	  
	  public ContractUpdate (Broker broker, long contractId)
	  {
	    super(broker);
	    this.contractId = contractId;
	  }

	  public long getContractId ()
	  {
	    return contractId;
	  }
	  
	  // protected constructor for simplified deserialization
	  protected ContractUpdate ()
	  {
	    super();
	  }

}
