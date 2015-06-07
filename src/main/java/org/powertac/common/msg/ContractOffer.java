package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.enumerations.PowerType;
import org.powertac.common.state.Domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Domain(fields = { "broker", "customerId", "contractId", "energyPrice",
		"peakLoadPrice", "duration", "earlyWithdrawPayment", "powerType",
		"acceptedEnergyPrice", "acceptedPeakLoadPrice", "acceptedDuration",
		"acceptedEarlyWithdrawPayment" })
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

	@XStreamAsAttribute
	protected boolean acceptedEnergyPrice = false;
	@XStreamAsAttribute
	protected boolean acceptedPeakLoadPrice = false;
	@XStreamAsAttribute
	protected boolean acceptedDuration = false;
	@XStreamAsAttribute
	protected boolean acceptedEarlyWithdrawPayment = false;

	/**
	 * used for initial offer
	 * 
	 * @param broker
	 * @param customerId
	 * @param energyPrice
	 * @param peakLoadPrice
	 * @param duration
	 * @param earlyExitFee
	 * @param powertype
	 */
	public ContractOffer(Broker broker, long customerId, double energyPrice,
			double peakLoadPrice, long duration, double earlyExitFee,
			PowerType powertype) {
		super(broker, customerId);

		this.energyPrice = energyPrice;
		this.peakLoadPrice = peakLoadPrice;
		this.duration = duration;
		this.earlyWithdrawPayment = earlyExitFee;
		this.powerType = powertype;
	}

	/**
	 * used for counter offer to track ACCEPT-booleans
	 * 
	 * @param offer
	 */
	public ContractOffer(ContractOffer offer) {
		super(offer.getBroker(), offer.customerId);
		this.energyPrice = offer.getEnergyPrice();
		this.peakLoadPrice = offer.getPeakLoadPrice();
		this.duration = offer.getDuration();
		this.earlyWithdrawPayment = offer.getEarlyWithdrawPayment();
		this.powerType = offer.getPowerType();
		this.contractId = offer.contractId;
		this.acceptedDuration = offer.isAcceptedDuration();
		this.acceptedEarlyWithdrawPayment = offer
				.isAcceptedEarlyWithdrawPayment();
		this.acceptedEnergyPrice = offer.isAcceptedEnergyPrice();
		this.acceptedPeakLoadPrice = offer.isAcceptedPeakLoadPrice();
	}

	/**
	 * used for accept to track ACCEPT-booleans
	 * 
	 * @param accept
	 */
	public ContractOffer(ContractAccept accept) {
		super(accept.getBroker(), accept.customerId);
		this.energyPrice = accept.getEnergyPrice();
		this.peakLoadPrice = accept.getPeakLoadPrice();
		this.duration = accept.getDuration();
		this.earlyWithdrawPayment = accept.getEarlyWithdrawPayment();
		this.powerType = accept.getPowerType();
		this.contractId = accept.contractId;
		this.acceptedDuration = accept.isAcceptedDuration();
		this.acceptedEarlyWithdrawPayment = accept
				.isAcceptedEarlyWithdrawPayment();
		this.acceptedEnergyPrice = accept.isAcceptedEnergyPrice();
		this.acceptedPeakLoadPrice = accept.isAcceptedPeakLoadPrice();
	}

	/**
	 * for reflection
	 */
	protected ContractOffer() {

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

	public boolean isAcceptedEnergyPrice() {
		return acceptedEnergyPrice;
	}

	public boolean isAcceptedPeakLoadPrice() {
		return acceptedPeakLoadPrice;
	}

	public boolean isAcceptedDuration() {
		return acceptedDuration;
	}

	public boolean isAcceptedEarlyWithdrawPayment() {
		return acceptedEarlyWithdrawPayment;
	}

	public String toString() {
		return "Offer[energyPrice=" + energyPrice + ", peakLoadPrice="
				+ peakLoadPrice + ", duration=" + duration
				+ ", earlyWithdrawPayment=" + earlyWithdrawPayment
				+ ", accEnergyPrice=" + acceptedEnergyPrice
				+ ", accPeakLoadPrice=" + acceptedPeakLoadPrice
				+ ", accDuration=" + acceptedDuration + ", accEarlyExit="
				+ acceptedEarlyWithdrawPayment + "]";

	}

}
