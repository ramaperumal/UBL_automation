package com.DOTC.pageObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;

import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class DOTCCrewSchedulerPage extends DOTCCommon {

	DOTCRestService dotc = new DOTCRestService();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	TestData testData = new TestData();
	JavascriptExecutor js;
	static List<String> premSeqNumbers = new ArrayList<String>();

	public SoftAssert softAssert = new SoftAssert();

	// ******* Elements used for Crew Scheduler Page*******

	@FindAll({ @FindBy(how = How.XPATH, using = ("//div[contains(text(),'ADMIN')]")) })
	WebElement Admin;

	@FindAll({
			@FindBy(how = How.XPATH, using = ("//div[@class='layout-row']//div[1]//md-datepicker[1]//div[1]//input[1]")) })
	WebElement StartDate;

	@FindAll({
			@FindBy(how = How.XPATH, using = ("//div[@class='layout-row']//div[2]//md-datepicker[1]//div[1]//input[1]")) })
	WebElement EndDate;

	@FindAll({ @FindBy(how = How.XPATH, using = ("//span[contains(text(),'CONTINUE')]")) })
	WebElement Continue;

	@FindAll({ @FindBy(how = How.XPATH, using = ("//span[contains(text(),'OK')]")) })
	WebElement OK;

	@FindAll({ @FindBy(how = How.XPATH, using = ("//*[@class='premiumSymbol ng-scope']")) })
	List<WebElement> TotalPremiumSeq;

	WebDriverWait wait = new WebDriverWait(driver, 30);

	public DOTCCrewSchedulerPage() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) this.driver;

	}

	public void clickDesignatePremium() {
		try {

			// if(driver.findElements(By.xpath("//li[contains(text(),'Designate
			// Premium')]")).size()>0)
			// closing the Open Time Pop up window
			// WebElement e =
			// driver.findElement(By.xpath("//i[contains(text(),'clear_white')]"));
			// e.click();
			Util.defaultwait(1000);
			String exp = "//li[contains(text(),'Designate Premium')]";

			// zoom out by 50%: because without zoom out Designated Premium is not visible
			// or clickable
			js.executeScript("document.body.style.zoom = '0.5'");
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(exp)));

			// Click Designated Premium
			js.executeScript("arguments[0].click();", element);
			// zoom in back to 100%
			js.executeScript("document.body.style.zoom = '1.0'");

			ExtentTestManager.getTest().log(LogStatus.PASS, "CS Page:Designate Premium button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CS Page: Premium button is not displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:CS Page:Designate Premium button is not displayed " + ex.getMessage(),
					true);

		}
	}

	public void clickAdmin() {
		try {
			Util.waitFor(driver, Admin);
			Util.ClickButton(driver, Admin);

			ExtentTestManager.getTest().log(LogStatus.PASS, "CS Page:Admin button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CS Page: Admin button is not displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:CS Page: Admin button is not displayed " + ex.getMessage(), true);

		}
	}

	public String getDate(String format, String offset) {
		// Format formatter = new SimpleDateFormat(format);
		Date dt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, Integer.parseInt(offset));
		dt = calendar.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		//sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		//System.out.println("Current date: "+sdf.format(dt));
		return sdf.format(dt);
	}

	public void selectDateRange(String stdtoffset, String enddtoffset) {
		try {

			String dt = getDate("ddMMMyy", stdtoffset);
			StartDate.clear();
			StartDate.click();
			StartDate.sendKeys(dt);
			
			dt = getDate("ddMMMyy", enddtoffset);
			EndDate.clear();
			EndDate.click();
			EndDate.sendKeys(dt);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "CS Page:Start and End Date is Selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CS Page: Start and End Date Selection Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:CS Page: Start Date Selection Failed " + ex.getMessage(), true);

		}

	}

	public void selectFourPartBidStatus(String base, String eqp, String div, String seat) {
		try {
			WebElement Base = Util.FindElementByXPath(driver, "//md-select[@id='base']", 1);
			WebElement Seat = Util.FindElementByXPath(driver, "//md-select[@id='seat']", 1);
			WebElement Division = Util.FindElementByXPath(driver, "//md-select[@id='division']", 1);
			WebElement Equipment = Util.FindElementByXPath(driver, "//md-select[@id='equipment']", 1);
			
			Util.waitForLoad(driver);
			Base.sendKeys(base);
			Util.waitForLoad(driver);
			Equipment.sendKeys(eqp);
			Util.waitForLoad(driver);
			Division.sendKeys(div);
			Util.waitForLoad(driver);
			Seat.sendKeys(seat);

			ExtentTestManager.getTest().log(LogStatus.PASS, "CS Page:Four Part Bid Status is Selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CS Page: Four Part Bid Status Selection Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:CS Page: Four Part Bid Status Selection Failed " + ex.getMessage(),
					true);
		}
	}

	public void clickSearch() {
		try {
			WebElement Search = Util.FindElementByXPath(driver, "//span[contains(text(),'Search')]", 1);
			Util.ClickButton(driver, Search);
			WebElement Reset = Util.FindElementByXPath(driver,
					"//thead[@class='doNotPrint visibleHeaders ng-scope']//th//span[@class='ng-scope'][contains(text(),'RESET')]",
					1);
			Util.waitForElementClickable(driver, Reset);

			// zoom out by 60%: because without zoom out Designated Premium is not visible
			// or clickable

			js.executeScript("document.body.style.zoom = '0.60'");
			ExtentTestManager.getTest().log(LogStatus.PASS, "CS Page:Search button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CS Page: Search button click Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("CS Page: Search button click Failed " + ex.getMessage(), true);
			ex.printStackTrace();
		}
	}

	//21-01-2022
	public void markAsPremium(String base, String eqp, String div, String seat) throws Exception {
		String seqnum;
		String seqdate;
		String fourPart;
		String exp;
		WebElement we;
		try {
			// Check if there are existing premium sequences for this 4 Part
			if (TotalPremiumSeq.size() > 0) {
				System.out.println(TotalPremiumSeq.size());
				for (int i = 0; i < TotalPremiumSeq.size(); i++) {
					exp = "//tbody[@class='printTableData']//*[@class='premiumSymbol ng-scope']/../../..//td[3]";
					seqnum = driver.findElements(By.xpath(exp)).get(i).getText();
					//exp = "//tbody[@class='printTableData']//tr[" + (i + 3) + "]" + "//td[3]";
					//seqnum = Util.FindElementByXPath(driver, exp, 1).getText();
					
					//exp = "//tbody[@class='printTableData']//tr[" + (i + 3) + "]//td[10]";
					//seqdate = Util.FindElementByXPath(driver, exp, 1).getText();
					exp = "//tbody[@class='printTableData']//*[@class='premiumSymbol ng-scope']/../../..//td[11]";
					seqdate = driver.findElements(By.xpath(exp)).get(i).getText();
					seqdate = seqdate.split(" ")[1];
					fourPart = base + '/' + eqp + '/' + div + '/' + seat;
					premSeqNumbers.add(seqnum + " " + seqdate + " " + fourPart);
				}
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"CS Page: Marking Premium Seq Step Passed, $ icon already exists so no seq was marked Premium",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(
						"CS Page: Marking Premium Seq Step Passed, $ icon already exists so no seq was marked Premium",
						true);

			} else {
				System.out.println("Currently No Premium Seq avalialble in this 4 Part, assigning premium seq");
				int totalSeq = Util.FindListOfElementsByXPath(driver, "//tbody[@class='printTableData']//tr", 1).size()
						- 3;
				if (totalSeq == 0) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"CS Page: Marking Premium Seq Failed, No seq returned for this 4 part, please change the 4 Part input data",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse(
							"CS Page: Marking Premium Seq Failed, No seq returned for this 4 part, please change the 4 Part input data",
							true);

				}
				// Click the checkbox to enable premium trips by iterating through each seq
				// Click Submit button and Continue button and then OK button

//				exp = "//table[@class='fullWidth openTimeTableHeader clone ng-scope']//tr[1]//th[2]//div[@class='md-icon']";
				for (int i = 0; i < totalSeq; i++) {
					exp = "//tbody/tr[" + (i + 3) + "]/td[2]/md-checkbox[1]/div[1]";
					we = Util.FindElementByXPath(driver, exp, 1);
					js.executeScript("arguments[0].click()", we);
				}
				exp = "//div[contains(@class, 'layout-align-end-stretch')]//button[contains(@class,'dialogButton md-raised')]";
				we = Util.FindElementByXPath(driver, exp, 1);
				if (we.isEnabled()) {
					js.executeScript("arguments[0].click()", we);
					Util.waitForLoad(driver);
					exp = "//span[contains(text(),'CONTINUE')]";
					we = Util.FindElementByXPath(driver, exp, 1);
					js.executeScript("arguments[0].click()", we);
					Util.waitForLoad(driver);
					try {
						exp = "//h3[contains(text(), 'Exception occurred while consuming OPS_HUB application service.')]";
						we = Util.FindElementByXPath(driver, exp, 1);
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"CS Page: Marking Premium Seq Failed, due to OPS_HUB connection",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertFalse(
								"CS Page: Marking Prem Seq Failed, due to OPS_HUB connection",
								true);
					} catch(Exception ex) {}
					
					exp = "//span[contains(text(),'OK')]";
					we = Util.FindElementByXPath(driver, exp, 1);
					js.executeScript("arguments[0].click()", we);
					Util.defaultwait(1000);
					
					exp = "//*[@class='premiumSymbol ng-scope']";
					if (Util.FindListOfElementsByXPath(driver, exp, 1).size() > 0) {
						System.out.println("Premium symbol found");
						for (int i = 0; i < totalSeq; i++) {
							exp = "//tbody[@class='printTableData']//tr[" + (i + 3) + "]//td[4]//*[@class='premiumSymbol ng-scope']";
							if (Util.FindListOfElementsByXPath(driver, exp, 1).size() > 0) {
								System.out.println("entered print table data");
								exp = "//tbody[@class='printTableData']//tr[" + (i + 3) + "]//td[3]";
								seqnum = Util.FindElementByXPath(driver, exp, 1).getText();
								System.out.println("sequence number: "+seqnum);
								exp = "//tbody[@class='printTableData']//tr[" + (i + 3) + "]//td[11]";
								seqdate = Util.FindElementByXPath(driver, exp, 1).getText().split(" ")[1];
								System.out.println("sequence date: "+seqdate);
								fourPart = base + '/' + eqp + '/' + div + '/' + seat;
								System.out.println(fourPart);
								premSeqNumbers.add(seqnum + " " + seqdate + " " + fourPart);
							}
						}
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"CS Page: Marking Premium Seq Failed, $ icon not displayed after marking seq as premium",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse(
							"CS Page: Marking Prem Seq Failed, $ icon not displayed after marking seq as premium",
							true);
				}

			}
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "CS Page: Marking Premium Seq Step Passed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("CS Page: Marking Premium Seq Step Passed", true);

		} catch (Exception ex) {
			log.info("Exception occured:CS Page: Mark As Premium TC Failed" + ex.toString());
			ExtentTestManager.getTest().log(LogStatus.FAIL, "CS Page: Mark As Premium TC Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("CS Page: Mark As Premium TC Failed " + ex.getMessage(), true);
			throw ex;

		}
	}
}
