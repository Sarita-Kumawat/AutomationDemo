package Scripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

// Working corrcetly for all filters 
public class Report_webtable_to_excel 
{
		
 static	FirefoxDriver driver;
 
 //Test method fetching data from site and storing in Excel file
 
  @Test
  public void Table1A() throws InterruptedException, IOException, InvalidFormatException {
	  
	
	    FileInputStream fis=new FileInputStream("./Excels/R1.xlsx");
		Workbook wb=WorkbookFactory.create(fis); 
	
	    driver=new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    //Login to site
	    driver.get("https://anderson-ucla.admin.stage-12twenty.com/Login");
	    driver.findElement(By.xpath("//*[@id='Username']")).sendKeys("bsupreeth@thoughtframeworks.com");
	    driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("Salaryview1!");
	    driver.findElement(By.xpath("//*[@id='loginForm']/div[2]/a")).click();
	  
	    Thread.sleep(3000);
	  
	   //Click on Data Analysis
	    driver.findElement(By.xpath("//*[text()='Data Analysis']")).click();
	   //Click on Data standard reports
	    driver.findElement(By.xpath("//*[text()='Standard Reports']")).click();
	   //Click on Table 1.A generate 
	   driver.findElement(By.xpath("//tr[td[text()='Table 1.A']]//*[text()='Generate']")).click();
	
	 
	  //select year
	   	WebElement gradyear = driver.findElement(By.xpath(".//*[@id='GraduationYearId']"));
	   	Select sel=new Select(gradyear);
	   	String year="2015";
   		sel.selectByVisibleText(year);

		 driver.findElement(By.xpath(".//*[@id='generateReportBtn']")).click();
		 Thread.sleep(5000);
		 
		 wb.createSheet(year);
		 Sheet s=wb.getSheet(year);
 
		//To locate table.
		  WebElement mytable = driver.findElement(By.xpath("//*[@id='report-data']"));
		  //To locate rows of table.
		  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		  //To calculate no of rows In table.
		  int rows_count = rows_table.size();
		  
		  ///headers
		  List<WebElement> Columns_header = rows_table.get(0).findElements(By.tagName("th"));
		  Row r1=s.createRow(0);
		 r1.createCell(0).setCellValue(Columns_header.get(0).getText());
		  
		 r1.createCell(1).setCellValue(Columns_header.get(1).getText());
		 s.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));
		  
		 r1.createCell(5).setCellValue(Columns_header.get(2).getText());
		 s.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));
		 
		 r1.createCell(9).setCellValue(Columns_header.get(3).getText());
		 s.addMergedRegion(new CellRangeAddress(0, 0, 9, 12));  
		   
		  //Loop will execute till the last row of table.
		  for (int row=1; row<rows_count; row++){
		   //To locate columns(cells) of that specific row.
		   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
		   
		   //To calculate no of columns(cells) In that specific row.
		   int columns_count = Columns_row.size();
		   System.out.println("Number of cells In Row "+row+" are "+columns_count);
		   Row r=s.createRow(row);
		   
		    		//Loop will execute till the last cell of that specific row.
		   			for (int column=0; column<columns_count; column++){
		   			//To retrieve text from that specific cell.
		   				String celtext = Columns_row.get(column).getText();
		   				//System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
		   				r.createCell(column).setCellValue(celtext);
		   				if(!celtext.isEmpty())
		   				{
		   					System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
		   				}

		   				//writing to csv
	
		   				}
		   			System.out.println("--------------------------------------------------");
		  }  
		  FileOutputStream fos=new FileOutputStream("./Excels/R1.xlsx");
			wb.write(fos);
			fos.close();
			driver.quit();	  
	  }

}



	


