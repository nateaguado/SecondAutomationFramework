package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {
    int retry = 1;

    int numberOfRetriies = 3;

    @Override
    public boolean retry(ITestResult iTestResult){
        if(retry < numberOfRetriies){
            retry++;
            return true;
        }
        return false;
    }
}
