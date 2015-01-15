package saml2webssotest.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saml2webssotest.common.TestSuite.TestCase;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.GsonBuilder;

public abstract class TestRunner {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(TestRunner.class);
	/**
	 * The testcase(s) that should be run
	 */
	public ArrayList<TestCase> testcases;
	/**
	 * Contains the mock server
	 */
	public Server mockServer;
	/**
	 * Display the list of test suites
	 */
	public void listTestSuites(String tsPackage) {
		Reflections tsReflections = new Reflections(tsPackage);
		Set<Class<? extends TestSuite>> testsuites = tsReflections.getSubTypesOf(TestSuite.class);
		for (Class<? extends TestSuite> ts : testsuites){
			// only print concrete classes
			if(!Modifier.isAbstract(ts.getModifiers()) && !Modifier.isInterface(ts.getModifiers())){
				System.out.println(ts.getSimpleName());
			}
		}
	}

	/**
	 * Display the list of test cases for the current test suite
	 */
	public void listTestCases(TestSuite testsuite) {
		// iterate through all test cases
		for (Class<?> testcase : testsuite.getClass().getDeclaredClasses()) {
			// check if the class object is in fact a test case
			if (TestCase.class.isAssignableFrom(testcase)) {
				// output the name of the test case
				System.out.println(testcase.getSimpleName());
				TestCase tc;
				try {
					tc = (TestCase) testcase.getConstructor(testsuite.getClass()).newInstance(testsuite);
					// also output the description of the test case
					System.out.println("\t" + tc.getDescription());
				} catch (InstantiationException e) {
					logger.error("Could not create a new instance of the test case", e);
				} catch (IllegalAccessException e) {
					logger.error("Could not create a new instance of the test case", e);
				} catch (IllegalArgumentException e) {
					logger.error("Could not create a new instance of the test case", e);
				} catch (InvocationTargetException e) {
					logger.error("Could not create a new instance of the test case", e);
				} catch (NoSuchMethodException e) {
					logger.error("Could not retrieve the constructor of the test case class", e);
				} catch (SecurityException e) {
					logger.error("Could not retrieve the constructor of the test case class", e);
				}
				System.out.println("");
			} else {
				logger.error("Class was not a test case");
			}
		}
	}

	/**
	 * Display the mock IdP's metadata for the provided test suite.
	 * 
	 * @param testsuite
	 *            is the test suite for which we should display the metadata
	 */
	public void outputMockedMetadata(TestSuite testsuite) {
		System.out.println(testsuite.getMockedMetadata());
	}

	/**
	 * Process the test results and output them as JSON
	 * 
	 * @param testresults is a list of test case results
	 */
	public void outputTestResults(List<TestResult> testresults) {
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(testresults));
	}

	/**
	 * Create a new mock server for the given test suite with the provided handler.
	 * Additional handles can be added to the returned Server object, if needed.
	 * 
	 * @param mockserverURL is the URL on which the mock server will be used
	 * @param handler is the handler that will be used in the given test suite
	 * @return the created mock server
	 */
	public Server newMockServer(URL mockServerURL, Handler handler) {
		// create the mock IdP and add all required handlers
		Server mockServer = new Server(
				new InetSocketAddress(mockServerURL.getHost(), mockServerURL.getPort()));
		
		// add a context handler to properly handle the sso path
		ContextHandler context = new ContextHandler();
		context.setContextPath(mockServerURL.getPath());
		mockServer.setHandler(context);

		// add the SAML Request handler to the mock IdP
		mockServer.setHandler(handler);
		
		return mockServer;
	}

	/**
	 * Retrieve the test case from the test suite that matches the given test case name, or 
	 * all test cases if the given test case name is empty or null. 
	 * 
	 * @param testsuite is the test suite in which the test cases are located
	 * @param testcaseName is the name of the test case that should be returned. If empty, all 
	 * test cases in the test suite will be returned
	 * @return a list containing either the test case from the given test suite that matches 
	 * the given test case name, or all test cases in the test suite if the given test case 
	 * name is null or empty
	 */
	public ArrayList<TestCase> getTestCases(TestSuite testsuite, String testcaseName) {
		ArrayList<TestCase> testcases = new ArrayList<TestCase>();
		for (Class<?> testcaseClass : testsuite.getClass().getDeclaredClasses()) {
			TestCase curTestcase;
			try {
				curTestcase = (TestCase) testcaseClass.getConstructor(testsuite.getClass()).newInstance(testsuite);
				// add the test case to the list if it matches the name that was provided, or if no name was provided
				if (testcaseName == null || testcaseName.isEmpty() || testcaseName.equals(testcaseClass.getSimpleName())) {
					testcases.add(curTestcase);
				}
			} catch (InstantiationException e) {
				logger.error("Could not instantiate an instance of the test suite or case", e);
			} catch (IllegalAccessException e) {
				logger.error("Could not access the test suite class or test case class", e);
			} catch (IllegalArgumentException e) {
				logger.error("Could not create a new instance of the test case", e);
			} catch (InvocationTargetException e) {
				logger.error("Could not create a new instance of the test case", e);
			} catch (NoSuchMethodException e) {
				logger.error("Could not retrieve the constructor of the test case class", e);
			} catch (SecurityException e) {
				logger.error("Could not retrieve the constructor of the test case class", e);
			} 
		}
		return testcases;
	}

	/**
	 * Retrieves a page at the given URL in the given webclient and performs the given interactions
	 * on that page.
	 * If no URL is given, only the interactions are performed in the given browser
	 * 
	 * @param htmlPage is the page with which we we wish to interact
	 * @param interactions is a list of interactions that should be performed on the retrieved page
	 * @return the page as it was retrieved after all interactions are completed
	 */
	public Page interactWithPage(Page htmlPage, List<Interaction> interactions) {
		// start login attempt with target SP
		try {
			// execute all interactions
			for(Interaction interaction : interactions){
				if(htmlPage instanceof HtmlPage){
					// cast the Page to an HtmlPage so we can interact with it
					HtmlPage loginPage = (HtmlPage) htmlPage;
					logger.trace("Login page");
					logger.trace(loginPage.getWebResponse().getContentAsString());
				
					// cast the interaction to the correct class
					if(interaction instanceof FormInteraction) {
						FormInteraction formInteraction = (FormInteraction) interaction;
						htmlPage = formInteraction.executeOnPage(loginPage);
						
					    logger.trace("Login page (after form submit)");
					    logger.trace(loginPage.getWebResponse().getContentAsString());
					}
					else if(interaction instanceof LinkInteraction) {
						LinkInteraction linkInteraction = (LinkInteraction) interaction;
						htmlPage = linkInteraction.executeOnPage(loginPage);
						
						logger.trace("Login page (after link click)");
					    logger.trace(htmlPage.getWebResponse().getContentAsString());
					}
					else {
						htmlPage = interaction.executeOnPage(loginPage);
						
						logger.trace("Login page (after element click)");
					    logger.trace(htmlPage.getWebResponse().getContentAsString());
					}
				}
				else{
					logger.error("The login page is not an HTML page, so it's not possible to interact with it");
					logger.trace("Retrieved page:");
					logger.trace(htmlPage.getWebResponse().getContentAsString());
					break;
				}
			}
			// return the retrieved page
			return htmlPage; 
		} catch (FailingHttpStatusCodeException e) {
			logger.error("The login page did not return a valid HTTP status code");
		} catch (ElementNotFoundException e){
			logger.error("The interaction link lookup could not find the specified element");
		}
		return null;
	}

	/**
	 * Run the requested testcase(s) from the requested test suite.
	 * 
	 * Prints the results in JSON format to System.out
	 */
	public void run(){
		// run the test case(s) from the test suite
		LinkedList<TestResult> testresults = new LinkedList<TestResult>();
		for (TestCase testcase : testcases) {
			boolean status;
			String resultMessage;
			try {
				// initialize a new mock server for use in the test case
				initMockServer();
				// run the test case
				status = runTest(testcase);
				// kill the mock server if it is still running
				if(mockServer != null && mockServer.isRunning()){
					killMockServer();
				}
				resultMessage = testcase.getResultMessage();
			} catch (Exception e) {
				// make sure any exceptions thrown while running a test are caught here
				// so they don't cause all other tests to be cancelled
				logger.error("An exception occurred while running test case: " + testcase.getClass().getSimpleName(), e);
				status = false;
				resultMessage = "The following exception has occurred (See the log for the full stacktrace): " + e.toString();
			}
	
			TestResult result = new TestResult(status, resultMessage);
			result.setName(testcase.getClass().getSimpleName());
			result.setDescription(testcase.getDescription());
			result.setMandatory(testcase.isMandatory());
			// add this test result to the list of test results
			testresults.add(result);
		}
		outputTestResults(testresults);
		// stop the mock server if still running
		try {
			if (mockServer != null && mockServer.isStarted()) {
				mockServer.stop();
			}
		} catch (Exception e) {
			logger.error("The mock server could not be stopped", e);
		}
	}

	public abstract void initMockServer();
	
	public abstract void killMockServer();

	public abstract boolean runTest(TestCase testcase);

	public abstract void loadConfig(String optionValue); 
}
