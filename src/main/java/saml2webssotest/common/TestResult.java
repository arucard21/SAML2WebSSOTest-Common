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
}
