/*
 * Copyright (c) 2011 by the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.powertac.common.msg;

import org.joda.time.Instant;
import org.powertac.common.CustomerInfo;
import org.powertac.common.IdGenerator;
import org.powertac.common.enumerations.PowerType;
import org.powertac.common.state.Domain;
import org.powertac.common.xml.CustomerConverter;

import com.thoughtworks.xstream.annotations.*;

/**
 * This message is used to send the bootstrap of every customer in the competition to the brokers
 * in order to see what kind of customers they are.
 * @author Anthony Chrysopoulos
 */
@Domain
@XStreamAlias("customer-bootstrap-data")
public class CustomerBootstrapData
{
  @XStreamAsAttribute
  private long id = IdGenerator.createId();
  
  @XStreamAsAttribute
  @XStreamConverter(CustomerConverter.class)
  private CustomerInfo customer;
  
  @XStreamAsAttribute
  private PowerType powerType;
  
  private Instant startTime;
  
  private double[] bootstrapData;

  public CustomerBootstrapData (CustomerInfo customer,
                                PowerType powerType,
                                Instant startTime,
                                double[] bootstrap)
  {
    super();
    this.customer = customer;
    this.powerType = powerType;
    this.startTime = startTime;
    this.bootstrapData = bootstrap;
  }

  public long getId ()
  {
    return id;
  }

  public CustomerInfo getCustomer ()
  {
    return customer;
  }

  public PowerType getPowerType ()
  {
    return powerType;
  }

  public Instant getStartTime ()
  {
    return startTime;
  }

  public double[] getBootstrapData ()
  {
    return bootstrapData;
  }
}