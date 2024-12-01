package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {".//Features/AmazonLogin.feature"},
		glue = "stepdefinition",
		dryRun = false,
		monochrome = true,
		//tags = "@regression",//scenarios under sanity will execute
	  plugin = {"pretty","html:target/cucumber-reports/reports_html.html","json:target/cucumber-reports/report_json.json"}
		)

public class Runner extends AbstractTestNGCucumberTests {

}
