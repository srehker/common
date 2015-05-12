package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Simon Rehker
 * ends negotiation without agreement
 */
@Domain (fields = {"broker", "contractId"})
@XStreamAlias("contract-end")
public class ContractEnd extends ContractUpdate{
	public ContractEnd (Broker broker, ContractOffer contract)
	  {
	    super(broker, contract);
	  }
	  
	  // protected default constructor for reflection
	  protected ContractEnd ()
	  {
	    super();
	  }
}
