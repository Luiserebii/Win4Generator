package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Win4Generator {

	private WebDriver driver;
	private ArrayList<LottoNum> prevLottoNums;

	public Win4Generator(){
		prevLottoNums = new ArrayList<LottoNum>();
	}

	public void updateWinningNumbers(){

		try {

			driver = new FirefoxDriver();

			driver.navigate().to("http://nylottery.ny.gov/wps/portal/Home/Lottery/home/your+lottery/winning+numbers/win4pastwinning+numbers");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

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
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			PrintWriter writer = new PrintWriter("data/winners.txt");
			int pageCnt = Integer.valueOf(driver.findElement(By.id("totalPage")).getText());

			System.out.println("PAGECNT: " + pageCnt);

			for(int i = 1; i < pageCnt + 1; i++){

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<WebElement> tableRows = driver.findElements(By.cssSelector("#winningNumbersTable tbody td"));

				for(WebElement we : tableRows){
					writer.println(we.getText());
					writer.flush();
				}

				if(i != pageCnt){
					WebElement nextButton = driver.findElement(By.id("next"));
					nextButton.click();
				}
				System.out.println("Page " + i + " processed!");
			}
			writer.close();
			driver.close();
			System.out.println("FINISHED!");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadPrevLottoNums(){

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/winners.txt"));
			String currLine = null;
			while ((currLine = reader.readLine()) != null){
				String d = currLine;
				String[] tempNums = reader.readLine().trim().split("-");
				//Convert String[] to proper int[]... NOTE: Insert as local LottoNum constructor?
				int[] nums = new int[4];
				for(int i = 0; i < tempNums.length; i++){
					nums[i] = Integer.valueOf(tempNums[i]);
				}
				prevLottoNums.add(new LottoNum(d, nums));
				reader.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
