package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportTablePage 
{
	 WebDriver driver;
	 By year = By.id("GraduationYearId");
	 By term = By.id("GraduationTermId");
	 By phase = By.id("JobPhaseId");
	 By degree = By.id("HasJointDegree");
	 By generateReprot= By.id("generateReportBtn");
	 
	  public ReportTablePage(WebDriver driver)
	  {
		  this.driver = driver;
	  }
	  
	  public void click_Generate()
	  {
		  //driver.findElement(generate).click();	
	  }
}
