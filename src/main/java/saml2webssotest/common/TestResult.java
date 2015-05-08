package saml2webssotest.common;

/**
 * TestResult is a simple container class for the result of a single test case
 * 
 * @author RiaasM
 *
 */
public class TestResult {
	String result;
	String resultMessage;
	boolean mandatory;
	String name;
	String description;

	/**
	 * Construct the TestResult object
	 * @param result is the result status of the test case
	 * @param resultMessage is the message given by the test case
	 */
	public TestResult(boolean resultBool, String resultMessage) {
		if(resultBool){
			result = "PASS";
		}
		else{
			result = "FAIL";
		}
		this.resultMessage = resultMessage;
	}
	
	/**
	 * Construct the TestResult object with all its variables
	 * @param result is the result status of the test case
	 * @param resultMessage is the message given by the test case
	 * @param mandatory defines if the tested functionality was mandatory
	 * @param name is the name of the test
	 * @param description is the description of the test
	 */
	public TestResult(boolean resultBool, String resultMessage, boolean mandatory, String name, String description) {
		if(resultBool){
			result = "PASS";
		}
		else{
			result = "FAIL";
		}
		this.resultMessage = resultMessage;
		this.mandatory = mandatory;
		this.name = name;
		this.description = description;
	}

	public boolean getResult() {
		if (result.equals("PASS")){
			return true;
		}
		return false;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null){
			return false;
		}
		if(!(other instanceof TestResult)){
			return false;
		}
		TestResult otherTC = (TestResult) other;
		return getResult() == otherTC.getResult() &&
				getResultMessage().equalsIgnoreCase(otherTC.getResultMessage()) &&
				getName().equalsIgnoreCase(otherTC.getName()) &&
				getDescription().equalsIgnoreCase(otherTC.getDescription()) &&
				getMandatory() ==  otherTC.getMandatory();
	}

	@Override
	public int hashCode() {
		// return non-negative hashcode
		return Math.abs(new Boolean(getResult()).hashCode()+getResultMessage().hashCode()+getName().hashCode()+getDescription().hashCode()+new Boolean(getMandatory()).hashCode());
	}
}
