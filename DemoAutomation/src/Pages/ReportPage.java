package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportPage 
{
	 WebDriver driver;
	 By generate = By.xpath("//tr[td[text()='Table 1.A']]//a[text()='Generate']");
	 
	  public ReportPage(WebDriver driver)
	  {
		  this.driver = driver;
	  }
	  
	  public void click_Generate()
	  {
		  driver.findElement(generate).click();	
	  }
}
