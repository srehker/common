package org.powertac.common.msg;

import org.powertac.common.Broker;
import org.powertac.common.IdGenerator;
import org.powertac.common.ValidatableMessage;
import org.powertac.common.state.XStreamStateLoggable;
import org.powertac.common.xml.BrokerConverter;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public abstract class ContractNegotiationMessage extends XStreamStateLoggable
implements ValidatableMessage{	

	@XStreamAsAttribute
	  private long contractId;
	
	@XStreamAsAttribute
	  protected long id = IdGenerator.createId();
	  
	  /** The broker originating this message */
	  @XStreamConverter(BrokerConverter.class)
	  protected Broker broker;
	  
	  public ContractNegotiationMessage (Broker broker)
	  {
	    super();
	    this.broker = broker;
	  }

	  public long getId ()
	  {
	    return id;
	  }

	  public Broker getBroker ()
	  {
	    return broker;
	  }
	  
	  @Override
	  public boolean isValid ()
	  {
	    return true;
	  }
	  
	  // protected constructor for simplified deserialization
	  protected ContractNegotiationMessage()
	  {
	    super();
	  }

}
