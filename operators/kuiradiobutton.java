package operators;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class kuiradiobutton {
	
	WebDriver driver;
	
		public void kuiradiobuttonset(List<WebElement> objradio, String fval){
		int count = objradio.size();
		//System.out.println(fieldName+"count:" +count);
		for (int i = 0; i < count; i++) {
			System.out.println(i);
			String sValue = objradio.get(i).getText();
			//if (objradio.get(i).isDisplayed()){
			// Store the checkbox name to the string variable, using 'Value'
			// attribute
			
			System.out.println(sValue);
			
			// Select the checkbox if its value is the same that you want.
			
				if (((String) sValue).equalsIgnoreCase(fval)) {
					
					objradio.get(i).click();
//					JavascriptExecutor jse = (JavascriptExecutor)driver;
//					jse.executeScript("argument[0].click();", objradio);
					//objradio.get(i).click();
					
					/*
					if (objradio.get(i).isEnabled()){
						Point rac= objradio.get(i).getLocation();
						System.out.println(rac);
						objradio.get(i).click();
					}else{
						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,-250)");
						
					*/
					System.out.println(fval);
					// This statement will get you out of the for loop.
				break;
				}
			}
			/*else if(((String) sValue).equalsIgnoreCase(fval)){
				
				//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+objradio.get(i).getLocation().y+")");
				JavascriptExecutor jse = (JavascriptExecutor)driver;

				//jse.executeScript("scroll(250, 0)");
				
				jse.executeScript("window.scrollBy(0,-250)");
				objradio.get(i).click();
				System.out.println(fval);
				// This statement will get you out of the for loop.
				break;
				}*/
						
			}
		
		}
	
		 
