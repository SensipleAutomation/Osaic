package webpages;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.codoid.products.exception.FilloException;

import datadriven.data;
import operators.scs;
import reports.ExtentManager;
import reports.ExtentTestManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;
import utility.ConfigFileReader;

public class loginpage {

	WebDriver driver;

	private static String PAGE_URL_PROD_BO = "http://spatro:Dec-2022@portaluseradmin/NTLMAuthenticate/NTLMAuthenticateAdvisorPortal.aspx?referrer=/support/bo_reports/index.aspx";
	// private static String PAGE_URL_PROD_FO = "https://www.v2020.com/login.aspx";
	private static String PAGE_URL_PROD_FO = "https://www.v2020.com/APLoginredirect.aspx";
	private static String PAGE_URL_DEV_BO = "http://agdev.v2020.com/login.aspx";
	private static String PAGE_URL_DEV_FO = "http://avincent:Welcome18@qaportaluseradmin/NTLMAuthenticate/NTLMAuthenticateAdvisorPortal.aspx?referrer=/SelectDelegateUserId.aspx";
	private static String PAGE_URL_QA_BO = "http://spatro:Dec-2022@qaportaluseradmin/NTLMAuthenticate/NTLMAuthenticateAdvisorPortal.aspx?referrer=/SelectDelegateUserId.aspx";
	private static String PAGE_URL_QA_FO = "http://agqa.v2020.com/login.aspx";
	private static String CRM_PAGE_URL = "https://osaic--qafull.sandbox.my.salesforce.com/";

	@FindBy(id = "userid")
	public WebElement o_userid;

	@FindBy(id = "ext-gen63")
	public WebElement o_userclick;

	@FindBy(id = "ext-gen4")
	public WebElement o_passwordexpireno;

	@FindBy(id = "loginpwd")
	public WebElement o_pass;

	@FindBy(css = "img[alt='Submit']")
	public WebElement o_login;

	@FindBy(id = "ext-gen121")
	public WebElement o_popuplogin;

	@FindBy(css = "input[name='rdChallangeList'][value='QUESTION']")
	public WebElement o_RSAoptions;

	@FindBy(css = "button[id='btnContinue'][text='Continue']")
	public WebElement o_RSAoptionContinue;

	@FindBy(css = "span[id='lblQuestion'][class='labelText']")
	public WebElement o_RSAquestion;

	@FindBy(css = "input[name='questionsList1_answer'][type='password']")
	public WebElement o_RSAanswer;

	@FindBy(css = "button[id='btnchQuestion'][text='Continue']")
	public WebElement o_RSAquestionContinue;

	public loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public synchronized homepage navtohomepageBO() throws FilloException, InterruptedException, IOException {
		synchronized (loginpage.class) {

			if (System.getProperty("env").equals("QA")) {
				driver.get(PAGE_URL_QA_BO);
			} else if (System.getProperty("env").equals("PROD")) {
				driver.get(PAGE_URL_PROD_BO);
			} else if (System.getProperty("env").equals("DEV")) {
				driver.get(PAGE_URL_DEV_BO);
			}
		}
		return new homepage(driver);
	}

	public synchronized homepage loginToCRM(String username, String password)
			throws FilloException, InterruptedException, IOException {
		driver.get(CRM_PAGE_URL);

		return new homepage(driver);
	}

	public synchronized homepage navtohomepage(String qry) throws FilloException, InterruptedException, IOException {
		synchronized (loginpage.class) {
			String excelname = System.getProperty("env");
			if (!excelname.contains("FO")) {
				Thread.sleep(10);
				o_userid.clear();
				Thread.sleep(10);
				data.inText(o_userid, "Username", qry);
				o_userclick.click();
				Thread.sleep(3000);
				data.inText(o_pass, "Password", qry);
				o_login.click();
				Thread.sleep(4000);
				boolean ispresenta = driver.getPageSource().contains("Continue with this Session");
				Thread.sleep(30);
				if (ispresenta == true) {

					// String draw =o_popuplogin.getCssValue("class");
					System.out.println("Welcome");
					/*
					 * Alert alert = driver.switchTo().alert(); alert.accept();
					 */
					Thread.sleep(30);
					o_popuplogin.click();

				}
				Thread.sleep(6000);
				boolean passwordnoti = driver.getPageSource().contains("Do you want to change it now?");
				// List<WebElement> ispresentb = driver.findElements(By.id("ext-gen162"));
				Thread.sleep(30);

				if (passwordnoti == true) {

					// String draw =o_popuplogin.getCssValue("class");
					// System.out.println("Welcome");
					/*
					 * Alert alert = driver.switchTo().alert(); alert.accept();
					 */
					Thread.sleep(30);
					driver.findElement(By.id("ext-gen164")).click();

				}
				// Password Notification
				Thread.sleep(3000);
				Thread.sleep(4000);

				boolean CyberGuard = driver.getPageSource().contains("CyberGuard Program Minimum Requirements");
				if (CyberGuard == true) {

					// String draw =o_popuplogin.getCssValue("class");
					// System.out.println("Welcome");
					/*
					 * Alert alert = driver.switchTo().alert(); alert.accept();
					 */
					Thread.sleep(30);
					driver.findElement(By.id("continue_btn_for_ag")).click();

				}
				Thread.sleep(7000);

				if (driver.getTitle().equals("RSA_V3_Authentication")) {
					PageFactory.initElements(driver, this);
					Thread.sleep(500);
					driver.findElement(By.cssSelector("input[name='rdChallangeList'][value='QUESTION']")).click();
					driver.findElement(By.cssSelector("button[id='btnContinue'][text='Continue']")).click();
					Thread.sleep(500);

					String quest = driver.findElement(By.cssSelector("span[id='lblQuestion'][class='labelText']"))
							.getText();
					String lastchara = quest.substring(quest.length() - 1);
					// System.out.println(lastchara);
					if (lastchara.equals("?")) {
						String lastWord = quest.substring(quest.lastIndexOf(" ") + 1);
						String removeQmark = lastWord.substring(0, lastWord.length() - 1);
						Thread.sleep(500);
						System.out.println(removeQmark);
						o_RSAanswer.sendKeys(removeQmark);
					} else {
						String[] parts = quest.split("\\?");

						String first = parts[0];
						String lastWord = first.substring(first.lastIndexOf(" ") + 1);
						Thread.sleep(500);
						// String second = parts[1];
						System.out.println(lastWord);
						o_RSAanswer.sendKeys(lastWord);

					}

					o_RSAquestionContinue.click();
					Thread.sleep(50);
				}

			}
		}
		return new homepage(driver);

	}

	public synchronized homepage navtohomepageasFO() throws FilloException, InterruptedException, IOException {
		synchronized (loginpage.class) {
			ConfigFileReader CR = new ConfigFileReader();
			String env1 = System.getProperty("env");
			System.out.println(env1);
			if (System.getProperty("env").equals("QA")) {
				driver.get(PAGE_URL_QA_FO);
				loginwithusernamepassword(CR.getQAFOusername(), CR.getQAFOpassword());
			} else if (System.getProperty("env").equals("PROD")) {
				driver.get(PAGE_URL_PROD_FO);
				loginwithusernamepassword(CR.getPRODFOusername(), CR.getPRODFOpassword());
			} else if (System.getProperty("env").equals("DEV")) {
				driver.get(PAGE_URL_DEV_FO);
				loginwithusernamepassword(CR.getDEVFOusername(), CR.getDEVFOpassword());
			}

			return new homepage(driver);

		}
	}

	public void loginwithusernamepassword(String UserName, String Password) throws InterruptedException {

		Thread.sleep(10);

		o_userid.clear();
		Thread.sleep(10);
		o_userid.sendKeys(UserName);

		o_userclick.click();
		Thread.sleep(3000);
		o_pass.sendKeys(Password);
		o_login.click();
		Thread.sleep(4000);
		boolean ispresenta = driver.getPageSource().contains("Continue with this Session");
		Thread.sleep(30);

		if (ispresenta == true) {
			System.out.println("Welcome");
			Thread.sleep(30);
			o_popuplogin.click();

		}
		Thread.sleep(6000);
		boolean passwordnoti = driver.getPageSource().contains("Do you want to change it now?");

		Thread.sleep(30);

		if (passwordnoti == true) {
			Thread.sleep(30);
			driver.findElement(By.id("ext-gen164")).click();

		}

		Thread.sleep(3000);
		Thread.sleep(4000);

		boolean CyberGuard = driver.getPageSource().contains("CyberGuard Program Minimum Requirements");
		if (CyberGuard == true) {
			Thread.sleep(30);
			driver.findElement(By.id("continue_btn_for_ag")).click();

		}
		Thread.sleep(7000);

		if (driver.getTitle().equals("RSA_V3_Authentication")) {
			PageFactory.initElements(driver, this);
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[name='rdChallangeList'][value='QUESTION']")).click();
			driver.findElement(By.cssSelector("button[id='btnContinue'][text='Continue']")).click();
			Thread.sleep(500);
			String quest = driver.findElement(By.cssSelector("span[id='lblQuestion'][class='labelText']")).getText();

			String lastchara = quest.substring(quest.length() - 1);

			if (lastchara.equals("?")) {
				String lastWord = quest.substring(quest.lastIndexOf(" ") + 1);
				String removeQmark = lastWord.substring(0, lastWord.length() - 1);
				Thread.sleep(500);
				System.out.println(removeQmark);
				o_RSAanswer.sendKeys(removeQmark);
			} else {
				String[] parts = quest.split("\\?");

				String first = parts[0];
				String lastWord = first.substring(first.lastIndexOf(" ") + 1);
				Thread.sleep(500);

				System.out.println(lastWord);
				o_RSAanswer.sendKeys(lastWord);

			}
			o_RSAquestionContinue.click();
			Thread.sleep(50);
		}
	}

	public void f_loginpage(String qry) throws FilloException {
		data.inText(o_userid, "Username", qry);
		o_userclick.click();
		data.inText(o_pass, "Password", qry);
		o_login.click();
		if (o_popuplogin.isDisplayed()) {
			System.out.println("popupisdisplayed");
			o_popuplogin.click();
			// ExtentTestManager.getTest().info("all done");

		}
	}
}
