package runner;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import cucumberutils.Driverselector;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.cucumber.java.Scenario;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = {
				"C:\\Users\\rpuliendran\\Downloads\\eo-bdd\\eo-bdd\\src\\test\\resources\\UI_Features\\AddNewAccountEntity.feature" },

		plugin = { "pretty", "cucumberutils.Initialization",
				// "html:target/Cucumber-Reports",
				// "json:target/Cucumber-Reports/cucumber.json",
				"json:target/cucumber-reports/CucumberTestReport.json", "junit:target/cucumberjunit.xml",
				"json:reports/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },

		tags = "@vaAppointmentsCheckbox",

		monochrome = true, dryRun = false, strict = true, glue = { "seleniumgluecode", "cucumberutils" })

public class UIRunner {

	private TestNGCucumberRunner testNGCucumberRunner;
	public String tctagname;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		System.out.println("--------------------------BC--------------------------------------------");

		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

		System.out.println(System.getProperty("user.dir"));

		try {
			File downloadedfilesdelete = new File(System.getProperty("user.dir") + "//downloadedfiles//");
			if (downloadedfilesdelete.exists()) {
				FileUtils.cleanDirectory(downloadedfilesdelete);
			}

			Path FROM = Paths.get(System.getProperty("user.dir") + "///resources//resultsheet.xlsx");
			Path TO = Paths.get(System.getProperty("user.dir") + "//Result//resultsheet.xlsx");

			String Oldfile = System.getProperty("user.dir") + "//src//test//resources//Result//resultsheet.xlsx";

			Date now1 = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
			String time1 = dateFormat.format(now1);
			// System.out.println("timestamp : "+time1);

			String newname = System.getProperty("user.dir") + "//src//test//resources//Result//resultsheet_" + time1
					+ ".xlsx";
			File file = new File(Oldfile);

			// File (or directory) with new name
			File file2 = new File(newname);
			file.renameTo(file2);

			// overwrite existing file, if exists
			CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
					StandardCopyOption.COPY_ATTRIBUTES };

			Files.copy(FROM, TO, options);
		} catch (Exception e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
		// setup static method(-s) here

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before Suite");
		System.out.println("--------------------------BS--------------------------------------------");
		System.out.println("================Print environment variable==================");
		Map<String, String> env = System.getenv();
		for (Map.Entry<String, String> entry : env.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("=======================Print System variable==================");
		Map<Object, Object> sis = System.getProperties();
		for (Map.Entry<Object, Object> entry : sis.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

	}

	@BeforeTest
	public void beforeTest() throws FilloException, IOException {
		System.out.println("--------------------------BT--------------------------------------------");
		Driverselector.initializeservericebrowser();
	}

	@BeforeMethod
	public void beforeMethod() throws FilloException {
		System.out.println("--------------------------BM--------------------------------------------");
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void scenario(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) throws Throwable {

		System.out.println(cucumberFeature);

		System.out.println("--------------------------T--------------------------------------------");
		System.out.println(pickleEvent.getPickle().getScenarioLine());

		testNGCucumberRunner.runScenario(pickleEvent.getPickle());
		System.out.println("--------------------------T2--------------------------------------------");
	}

	@AfterMethod
	public void afterMethod() throws FilloException {

		System.out.println("--------------------------AM--------------------------------------------");
	}

	@DataProvider
	public Object[][] scenarios() {
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		System.out.println("--------------------------AC--------------------------------------------");
		testNGCucumberRunner.finish();
	}

	@AfterTest

	public void setUptear() throws IOException {
		System.out.println("after test");
		int closebrowser = Integer.parseInt(System.getProperty("closebrowser"));
		if (closebrowser == 1) {
			Driverselector.service.stop();
		}

	}

}
