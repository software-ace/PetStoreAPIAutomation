package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter; // responsible for the UI in your report
    public ExtentReports extent; // responsible for the common data in the reports like OS/user details, etc...
    public ExtentTest test; // responsible for creating the entries in your report based on test execution
    String reportName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter("./reports/" + reportName); // specify location of the report

        sparkReporter.config().setDocumentTitle("Pet Store API test report");
        sparkReporter.config().setReportName("Pet Store API test report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pet Store API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Software-Ace");
    }

    public void onTestSuccess(ITestResult testResult) {
        test = extent.createTest(testResult.getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.createNode(testResult.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult testResult) {
        test = extent.createTest(testResult.getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.createNode(testResult.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, testResult.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult testResult) {
        test = extent.createTest(testResult.getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.createNode(testResult.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, testResult.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
