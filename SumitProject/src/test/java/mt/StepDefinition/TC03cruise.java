package mt.StepDefinition;

import org.openqa.selenium.By;

import MT.baseClass.MTExcelRW;
import MT.baseClass.MTWrapperclass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class TC03cruise extends MTWrapperclass{
static String[] data;
	
	@Given("^the user launches the application in browser$")
	public void the_user_launches_the_application_in_browser() throws Throwable {
		launchBrowser("chrome", "http://newtours.demoaut.com/index.php");
	}

	@Then("^click cruises$")
	public void click_cruises() throws Throwable {
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[5]/td[2]/a")).click();
	}

	@Then("^Retrive any data from the table$")
	public void retrive_any_data_from_the_table() throws Throwable {
		
		MTExcelRW xl = new MTExcelRW();
		data=new String[10];
		for(int i=1;i<=4;i++)
		{
			String xpath1="/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/th/table/tbody/tr[6]/td["+i+"]";
			data[i]=driver.findElement(By.xpath(xpath1)).getText();
			System.out.print(data[i]);
				xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", data[i], "Sheet1",25,i);
		}
	}

	@Then("^I validate$")
	public void i_validate() throws Throwable {
		MTExcelRW xl = new MTExcelRW();
		if(xl.readXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", 25, 1).equals("Mon"))
		{
			System.out.println("Pass");
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", "cruiseData Retrived", "Sheet1", 23,5);
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", "pass", "Sheet1", 23,6);
		}
		else
		{
			System.out.println("Fail");
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", "HomeTable cannot be Retrived", "Sheet1", 23,5);
			xl.writeXL("src\\test\\resources\\TestData\\MercuryExcel.xlsx", "fail", "Sheet1", 23,6);
		}
		screenShot("src\\test\\resources\\ScreenShots\\cruise.png");
		quit();
	   
	}



}
