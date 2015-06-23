package org.powertac.common.enumerations;

import java.util.HashMap;

import org.powertac.common.xml.ContractIssueConverter;

import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamConverter(ContractIssueConverter.class)
public class ContractIssue {

	private enum TypeLabel {
		ENERGY_PRICE, PEAK_LOAD_PRICE, DURATION, EARLY_WITHDRAW_PRICE, NONE
	}


	// index to convert strings to PowerType instances
	private static HashMap<String, ContractIssue> index = new HashMap<String, ContractIssue>();

	public static final ContractIssue ENERGY_PRICE = new ContractIssue(
			TypeLabel.ENERGY_PRICE);

	public static final ContractIssue PEAK_LOAD_PRICE = new ContractIssue(
			TypeLabel.PEAK_LOAD_PRICE);

	public static final ContractIssue DURATION = new ContractIssue(
			TypeLabel.DURATION);

	public static final ContractIssue EARLY_WITHDRAW_PRICE = new ContractIssue(
			TypeLabel.EARLY_WITHDRAW_PRICE);

	public static final ContractIssue NONE = new ContractIssue(TypeLabel.NONE);



	// This is the instance data field.
	private TypeLabel label;
	
	
	private ContractIssue(TypeLabel label) {
		super();
		this.label = label;
		index.put(label.toString(), this);
	}

	public static ContractIssue valueOf(String name) {
		return index.get(name);
	}
	
	@Override
	  public String toString ()
	  {
	    return label.toString();
	  }

}
