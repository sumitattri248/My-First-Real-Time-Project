package mt.StepDefinition;

import org.openqa.selenium.support.PageFactory;

import MT.baseClass.MTExcelRW;
import MT.baseClass.MTWrapperclass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mt.Pages.RegistrationPages;


public class TC01Register extends MTWrapperclass {
	static RegistrationPages rpom;
	static String[] data = null;

	@Given("^the user launch the application in the browser$")
	public void the_user_launch_the_application_in_the_browser() throws Throwable {
		launchBrowser("chrome", "http://newtours.demoaut.com/index.php");
	}

	
	@When("^the user open the MercuryTours Registration page$")
	public void the_user_open_the_MercuryTours_Registration_page() throws Throwable {
		rpom = PageFactory.initElements(driver, RegistrationPages.class);
		rpom.registerlink();
		MTExcelRW xl = new MTExcelRW();

		data=new String[11];
		int j=0; 
		for (int i=1;i<=11;i++) {

			data[j]=xl.readXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", i, 3); 
			System.out.println(data[j]);

			j++;
		}
	}

	
	@Then("^the user fills all the Details$")
	public void the_user_fills_all_the_Details() throws Throwable {
		rpom.RegisterPage(data);
	}

	
	@Then("^click on the Register button$")
	public void click_on_the_Register_button() throws Throwable {
		rpom.submit();
	}

	
	@Then("^I validate the outcomes$")
	public void i_validate_the_outcomes() throws Throwable {
		MTExcelRW xl = new MTExcelRW();
		String ar = rpom.actRes();
		System.out.println(ar);
		String er = xl.readXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", 1, 4);
		System.out.println(er);
		if (er.equals(ar)) {
			System.out.println("pass");
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", ar, "Sheet1", 1, 5);
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", "pass", "Sheet1", 1, 6);
		}
		else
		{
			System.out.println("fail");
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", ar, "Sheet1", 1, 5);
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", "fail", "Sheet1", 1, 6);
		}
		screenShot("src\\test\\resources\\ScreenShots\\Register.png");
		quit();
	}

}
