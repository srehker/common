package org.powertac.common.xml;

import org.powertac.common.enumerations.ContractIssue;
import org.powertac.common.enumerations.PowerType;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class ContractIssueConverter  implements SingleValueConverter
	{
	  public ContractIssueConverter ()
	  {
	    super();
	  }

	  @Override
	  @SuppressWarnings("rawtypes")
	  public boolean canConvert (Class type)
	  {
	    return ContractIssue.class.isAssignableFrom(type);
	  }

	  @Override
	  public Object fromString (String label)
	  {
	    return ContractIssue.valueOf(label);
	  }

	  @Override
	  public String toString (Object issue)
	  {
	    return ((ContractIssue)issue).toString();
	  }

	
}
