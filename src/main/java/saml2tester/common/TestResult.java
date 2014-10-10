package saml2tester.common;

/**
 * TestResult is a simple container class for the result of a single test case
 * 
 * @author RiaasM
 *
 */
public class TestResult {
	TestStatus result;
	String resultMessage;
	String name;
	String description;

	/**
	 * Construct the TestResult object
	 * @param result is the result status of the test case
	 * @param resultMessage is the message given by the test case
	 */
	public TestResult(TestStatus result, String resultMessage) {
		this.result = result;
		this.resultMessage = resultMessage;
	}

	public TestStatus getResult() {
		return result;
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
}
