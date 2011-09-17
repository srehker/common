/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.powertac.common;

import java.util.List;

import org.joda.time.Instant;
import org.powertac.common.interfaces.TransactionProcessor;
import org.powertac.common.xml.BrokerConverter;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public abstract class BrokerTransaction
{
  @XStreamAsAttribute
  protected long id = IdGenerator.createId();
  
  /** Whose transaction is this? */
  @XStreamConverter(BrokerConverter.class)
  protected Broker broker;

  /** The timeslot for which this meter reading is generated */
  protected Instant postedTime;

  public BrokerTransaction (Instant when, Broker broker)
  {
    super();
    this.postedTime = when;
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

  public Instant getPostedTime ()
  {
    return postedTime;
  }
  
  /**
   * Visitor style processing to get around Java dispatching weakness.
   */
  public abstract void process (TransactionProcessor svc, List msgs);
}