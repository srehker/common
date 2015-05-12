package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Domain (fields = {"broker", "contractId"})
@XStreamAlias("contract-decommit")
public class ContractDecommit extends ContractUpdate{
	public ContractDecommit (Broker broker, ContractOffer contract)
	  {
	    super(broker, contract);
	  }
	  
	  // protected default constructor for reflection
	  protected ContractDecommit ()
	  {
	    super();
	  }
}
