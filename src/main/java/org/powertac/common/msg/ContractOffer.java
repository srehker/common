package org.powertac.common.msg;

import org.joda.time.Instant;
import org.powertac.common.Broker;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Domain(fields = { "broker", "energyPrice", "peakLoadPrice", "startDate",
		"endDate", "earlyWithdrawPayment" })
@XStreamAlias("contract-offer")
public class ContractOffer extends ContractNegotiationMessage {

	@XStreamAsAttribute
	private double energyPrice = 0.0; // per kWh
	@XStreamAsAttribute
	private double peakLoadPrice = 0.0; // per Month per kWh
	@XStreamAsAttribute
	private Instant startDate;
	@XStreamAsAttribute
	private Instant endDate;
	@XStreamAsAttribute
	private double earlyWithdrawPayment; // when DECOMMIT is send (also before
											// startDate)

	public ContractOffer(Broker broker, double energyPrice,
			double peakLoadPrice, Instant startDate, Instant endDate,
			double earlyExitFee) {
		super();
		this.energyPrice = energyPrice;
		this.peakLoadPrice = peakLoadPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.earlyWithdrawPayment = earlyExitFee;
	}

	public double getEnergyPrice() {
		return energyPrice;
	}

	public void setEnergyPrice(double energyPrice) {
		this.energyPrice = energyPrice;
	}

	public double getPeakLoadPrice() {
		return peakLoadPrice;
	}

	public void setPeakLoadPrice(double peakLoadPrice) {
		this.peakLoadPrice = peakLoadPrice;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public double getEarlyWithdrawPayment() {
		return earlyWithdrawPayment;
	}

	public void setEarlyWithdrawPayment(double earlyWithdrawPayment) {
		this.earlyWithdrawPayment = earlyWithdrawPayment;
	}

}
