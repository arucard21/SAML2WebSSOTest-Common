package saml2webssotest.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saml2webssotest.common.TestSuite.TestCase;

import com.google.gson.GsonBuilder;

public class TestRunnerUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(TestRunnerUtil.class);

	/**
	 * Display the list of test suites
	 * 
	 * When new test suites are created, they need to be added here manually to
	 * be listed though they can be used without being listed. (Doing this
	 * dynamically is not stable enough with Java Reflection)
	 */
	public static void listTestSuites(String tsPackage) {
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
	public static void listTestCases(TestSuite testsuite) {
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
	public static void outputMockedMetadata(TestSuite testsuite) {
		System.out.println(testsuite.getMockedMetadata());
	}

	/**
	 * Process the test results and output them as JSON
	 * 
	 * @param testresults is a list of test case results
	 */
	public static void outputTestResults(List<TestResult> testresults) {
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(testresults));
	}

}
