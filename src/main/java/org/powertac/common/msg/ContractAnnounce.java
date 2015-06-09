package org.powertac.common.msg;

import org.powertac.common.enumerations.PowerType;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Domain(fields = { "broker", "contractId", "customerId", "powerType" })
@XStreamAlias("contract-announce")
public class ContractAnnounce extends ContractNegotiationMessage {

	public ContractAnnounce(long customerId) {
		super(null, customerId);

	}

	// protected default constructor for reflection
	protected ContractAnnounce() {
		super();
	}
}
