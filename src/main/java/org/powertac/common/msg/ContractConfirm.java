package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Domain (fields = {"broker", "contractId"})
@XStreamAlias("contract-confirm")
public class ContractConfirm extends ContractUpdate{
	public ContractConfirm (Broker broker, ContractOffer contract)
	  {
	    super(broker, contract);
	  }
	  
	  // protected default constructor for reflection
	  protected ContractConfirm ()
	  {
	    super();
	  }
}
