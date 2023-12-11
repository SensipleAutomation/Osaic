package operators;

//import static org.junit.Assert.fail;

//import static org.junit.Assert.fail;

import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 
public class opera {
	public int MAX_ATTEMPTS = 5;
	public int MAX_TIMEOUT = 5;
	WebDriver driver;
	 public WebElement waitForElement(By by) {
	        int attempts = 0;
	        int size = driver.findElements(by).size();

	        while (size == 0) {
	            size = driver.findElements(by).size();
	            /*if (attempts == MAX_ATTEMPTS) fail(String.format("Could not find %s after %d seconds",
	                                                             by.toString(),
	                                                             MAX_ATTEMPTS));
	            attempts++;
	            try {
	                Thread.sleep(1000); // sleep for 1 second.
	            } catch (Exception x) {
	                fail("Failed due to an exception during Thread.sleep!");
	                x.printStackTrace();
	            }*/
	        }

	        if (size > 1) System.err.println("WARN: There are more than 1 " + by.toString() + " 's!");

	        return driver.findElement(by);
	    }
	 
	 public void waitforvisible(WebElement element) throws Error{
		 //new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(element));
		 WebDriverWait wait = new WebDriverWait(driver, 90);
		 wait.until(ExpectedConditions.visibilityOf(element));
	 }
	

		 
}
