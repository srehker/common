package org.powertac.common.msg;

import org.powertac.common.enumerations.PowerType;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Domain(fields = { "broker", "contractId", "customerId", "powerType", "startDate" })
@XStreamAlias("contract-announce")
public class ContractAnnounce extends ContractNegotiationMessage {
	
	private long startDate;

	public ContractAnnounce(long customerId, long startDate) {
		super(null, customerId);
		this.setStartDate(startDate);

	}

	// protected default constructor for reflection
	protected ContractAnnounce() {
		super();
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
}
