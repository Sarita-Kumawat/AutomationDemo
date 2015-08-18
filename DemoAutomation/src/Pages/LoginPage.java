package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{
 WebDriver driver;
 By unName = By.id("Username");
 By unpass = By.id("Password");
 By login = By.id("loggingIn");
  
 public LoginPage(WebDriver Driver)
 {
	 this.driver =Driver;
 }
 
 public void click_login(String user,String Pass)
 {
	 driver.findElement(unName).sendKeys(user);
	 driver.findElement(unpass).sendKeys(Pass);
	 driver.findElement(login).click();
 }
}
