package runnerScripts;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
			features = {".\\src\\\test\\java\\featureFiles\\Login.feature"},
			glue = {"stepDefinition","hook"},
			dryRun = false,
			monochrome = true,
			plugin = {"html: reports\\cucumberreports.html",
"json: reports\\cucumberjson.json",
"com.aventstack.extentreports.cucumber.adapter.ExtentCucumber Adapter: "}
		)
public class Runner1 extends AbstractTestNGCucumberTests
{

}
