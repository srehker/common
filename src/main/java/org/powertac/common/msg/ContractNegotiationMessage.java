package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.IdGenerator;
import org.powertac.common.ValidatableMessage;
import org.powertac.common.state.XStreamStateLoggable;
import org.powertac.common.xml.BrokerConverter;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public abstract class ContractNegotiationMessage extends XStreamStateLoggable
		implements ValidatableMessage {

	@XStreamAsAttribute
	protected long contractId;
	
	/**
	 * customer the contract is offered
	 */
	@XStreamAsAttribute
	protected long customerId;

	@XStreamAsAttribute
	protected long id = IdGenerator.createId();

	/** The broker originating this message */
	@XStreamConverter(BrokerConverter.class)
	protected Broker broker;

	public ContractNegotiationMessage(Broker broker, long customerId) {
		super();
		this.broker = broker;
		this.customerId = customerId;
	}

	public long getId() {
		return id;
	}

	public Broker getBroker() {
		return broker;
	}
	

	public long getCustomerId() {
		return customerId;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	// protected constructor for simplified deserialization
	protected ContractNegotiationMessage() {
		super();
	}

}
