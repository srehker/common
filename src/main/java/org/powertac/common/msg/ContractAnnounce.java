package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Domain (fields = {"broker", "contractId"})
@XStreamAlias("contract-announce")
public class ContractAnnounce extends ContractUpdate{
	public ContractAnnounce (Broker broker, ContractOffer contract)
	  {
	    super(broker, contract);
	  }
	  
	  // protected default constructor for reflection
	  protected ContractAnnounce ()
	  {
	    super();
	  }
}
