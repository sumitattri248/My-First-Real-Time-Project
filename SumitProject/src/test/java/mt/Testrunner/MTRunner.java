package mt.Testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\main\\resources\\Feature\\MT.Feature",
		plugin= {"pretty","html:Reports\\cucumberHTMLReport","json:Reports\\cucumberHTMLReport\\jsonreport",
		"com.cucumber.listener.ExtentCucumberFormatter:Reports\\ExtentReports\\extentreport.html"},
		glue= {"mt.StepDefinition"},
		monochrome=true	
		)
public class MTRunner {
	@AfterClass
	public static void extendReport() {
		Reporter.loadXMLConfig("src\\test\\resources\\extent-config.xml");
	}


}
