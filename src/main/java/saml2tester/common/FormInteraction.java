package saml2tester.common;

import java.util.HashMap;

/**
 * This class contains all information required to interact with a form on an HTML page.
 * 
 * @author RiaasM
 *
 */
public class FormInteraction {
	
	private String formName;
	private String submitName;
	private HashMap<String, String> inputs = new HashMap<String, String>();
	
	/**
	 * Create the FormInteraction object
	 * 
	 * @param formName is the name of the HTML form that we will interact with
	 * @param submitName is the name of the submit button on the form
	 */
	public FormInteraction(String formName, String submitName){
		this.setFormName(formName);
		this.setSubmitName(submitName);
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the submitName
	 */
	public String getSubmitName() {
		return submitName;
	}

	/**
	 * @param submitName the submitName to set
	 */
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}

	/**
	 * @return the inputs
	 */
	public HashMap<String, String> getInputs() {
		return inputs;
	}
	
	/**
	 * Add the name and value of an input field that should
	 * be filled in on the form before submitting
	 * 
	 * @param name
	 * @param value
	 */
	public void addInput(String name, String value) {
		inputs.put(name, value);
	}

}
