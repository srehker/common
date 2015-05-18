package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.enumerations.PowerType;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Domain(fields = { "broker", "customerId", "energyPrice", "peakLoadPrice", "duration", "earlyWithdrawPayment", "powerType" })
@XStreamAlias("contract-offer")
public class ContractOffer extends ContractNegotiationMessage {

	@XStreamAsAttribute
	private double energyPrice = 0.0; // per kWh
	@XStreamAsAttribute
	private double peakLoadPrice = 0.0; // highest peak per Month per kWh
	@XStreamAsAttribute
	private long duration; // in ms
	@XStreamAsAttribute
	private double earlyWithdrawPayment; // when DECOMMIT is send 
	@XStreamAsAttribute
	private PowerType powerType = PowerType.CONSUMPTION;

	public ContractOffer(Broker broker, long customerId, double energyPrice,
			double peakLoadPrice, long duration,
			double earlyExitFee, PowerType powertype) {
		super();
		
		this.energyPrice = energyPrice;
		this.peakLoadPrice = peakLoadPrice;
		this.duration = duration;
		this.earlyWithdrawPayment = earlyExitFee;
		this.powerType = powertype;
	}

	public PowerType getPowerType() {
		return powerType;
	}

	public void setPowerType(PowerType powerType) {
		this.powerType = powerType;
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
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public double getEarlyWithdrawPayment() {
		return earlyWithdrawPayment;
	}

	public void setEarlyWithdrawPayment(double earlyWithdrawPayment) {
		this.earlyWithdrawPayment = earlyWithdrawPayment;
	}
	
	public String toString(){
		return "Offer[energyPrice="+energyPrice+", peakLoadPrice="+peakLoadPrice+", duration="+duration+", earlyWithdrawPayment="+earlyWithdrawPayment+"]";
		
	}

}
