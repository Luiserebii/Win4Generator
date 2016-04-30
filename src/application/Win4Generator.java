package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Win4Generator {


	WebDriver driver;

	public void updateWinningNumbers(){

		try {

			driver = new FirefoxDriver();

			driver.navigate().to("http://nylottery.ny.gov/wps/portal/Home/Lottery/home/your+lottery/winning+numbers/win4pastwinning+numbers");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.switchTo().frame("Winners_NumberTracker");

			Select startYear = new Select(driver.findElement(By.id("lottoStartDateYear")));
			Select startMonth = new Select(driver.findElement(By.id("lottoStartDateMonth")));
			Select endYear = new Select(driver.findElement(By.id("lottoEndDateYear")));
			Select endMonth = new Select(driver.findElement(By.id("lottoEndDateMonth")));

			WebElement enterButton = driver.findElement(By.name("getWinningNumbers"));

			Calendar c = Calendar.getInstance();
			int currYear = c.get(c.YEAR);
			System.out.println("CURRYEAR: " + currYear);
			int monthNum = c.get(c.MONTH);

			startYear.selectByValue("2008");
			startMonth.selectByIndex(0);
			endYear.selectByValue(String.valueOf(currYear));
			endMonth.selectByIndex(monthNum);

			enterButton.click();
			
			//THIS IS WHERE THINGS ARE ODD; TRY TO COMMENT OUT THIS TRY BLOCK AND SEE THE EXCEPTION THAT HAPPENS
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<WebElement> tableRows = driver.findElements(By.cssSelector("#winningNumbersTable tbody td"));
			PrintWriter writer = new PrintWriter("data/winners.txt");

			for(WebElement we : tableRows){
				writer.println(we.getText());
				writer.flush();
			}

			writer.close();
			driver.close();
			System.out.println("FINISHED!");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	 public WebElement find(By by){
		 WebElement foundElement = null;
		    for (int milis=0; milis<3000; milis=milis+200){
		       try{
		       foundElement = driver.findElement(by);

		       }catch(Exception e){
		         try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       }

		     }
		    return foundElement;
		 }

}
