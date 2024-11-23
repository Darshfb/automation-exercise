package retry_test;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer
{
    private int startCount = 0;
    private static final int maxCount = 3;
    @Override
    public boolean retry(ITestResult iTestResult)
    {
        if(startCount < maxCount)
        {
            startCount++;
            return true;
        }
        return false;
    }

}
