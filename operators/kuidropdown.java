package operators;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

//import org.openqa.selenium.internal.WrapsDriver;

public class kuidropdown {
	
	WebDriver driver;
    String name;
    WebElement parentElem;

    public kuidropdown(WebElement parentElem) {
        this.parentElem = parentElem;
        name = parentElem.getAttribute("aria-owns");
       // System.out.println(name);
        this.driver = ((WrapsDriver) parentElem).getWrappedDriver();

    }
    public void selectItem(String item) throws InterruptedException {
    	
    	if (!parentElem.getAttribute("aria-disabled").equals("true")) {                  
        expand();
        Thread.sleep(200);
       
        	driver.findElement(By.cssSelector("ul[id='"+name+"']"))
            .findElement(By.xpath("./li[text()=\"" + item + "\"]")).click();
        /*driver.findElement(By.cssSelector("ul[id='"+name+"']"))
        .findElement(By.xpath("./li[contains(text(),'"+item+"']")).click();*/
        
        //[contains(@title, \"My Day's Schedule\")
        Thread.sleep(200);
        collapse();
        
        Thread.sleep(200);
        
    	 }else{
    		 System.out.println("Element skipped");
    	 }
    }
    


    /**
     * Open Dropdown
     */
    private void expand() {
        if (!parentElem.getAttribute("aria-expanded").equals("true")) {
            parentElem.findElement(By.cssSelector("span.k-icon")).click();
        }
    }

    /**
     * Close Dropdown
     */
    private void collapse() {
        if (!parentElem.getAttribute("aria-expanded").equals("false")) {
            parentElem.findElement(By.cssSelector("span.k-icon")).click();
        }
    }
   
    
   
    
}
