package org.powertac.common.msg;

import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Domain(fields = { "broker", "customerId", "contractId", "energyPrice",
		"peakLoadPrice", "duration", "earlyWithdrawPayment", "powerType",
		"discussedIssue", "acceptedEnergyPrice", "acceptedPeakLoadPrice",
		"acceptedDuration", "acceptedEarlyWithdrawPayment" })
@XStreamAlias("contract-accept")
public class ContractAccept extends ContractOffer {

	/**
	 * copies accept states from offer, SET CURRENTLY ACCEPTED BY HAND BEFORE
	 * SENDING
	 * 
	 * @param contract
	 */
	public ContractAccept(ContractOffer contract) {
		super(contract);
	}

	// protected default constructor for reflection
	protected ContractAccept() {
		super();
	}

	public void setAcceptedDuration(boolean acceptedDuration) {
		this.acceptedDuration = acceptedDuration;
	}

	public void setAcceptedPeakLoadPrice(boolean acceptedPeakLoadPrice) {
		this.acceptedPeakLoadPrice = acceptedPeakLoadPrice;
	}

	public void setAcceptedEnergyPrice(boolean acceptedEnergyPrice) {
		this.acceptedEnergyPrice = acceptedEnergyPrice;
	}

	public void setAcceptedEarlyWithdrawPayment(
			boolean acceptedEarlyWithdrawPayment) {
		this.acceptedEarlyWithdrawPayment = acceptedEarlyWithdrawPayment;
	}

	public boolean isEveryIssueAccepted() {
		return acceptedDuration && acceptedEarlyWithdrawPayment
				&& acceptedEnergyPrice && acceptedPeakLoadPrice;
	}
}
