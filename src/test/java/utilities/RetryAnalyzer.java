package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer  implements IRetryAnalyzer {

	    private int count = 0;
	    private static final int maxRetry = 1; // Retry once

	    @Override
	    public boolean retry(ITestResult result) {

	        if (count < maxRetry) {
	            count++;
	            return true;
	        }

	        return false;
	    }
	}

/*IMPORTANT

Retry works only with TestNG tests, not directly with Cucumber steps.

Since you are using:

AbstractTestNGCucumberTests

Retry must be applied at Runner level.*/
