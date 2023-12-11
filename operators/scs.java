package operators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/*import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;*/

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class scs {
	
	//WebDriver driver;
	//Screenshot screenshot;
	//static WebDriver driver;
	
	
	public void ss (WebDriver driver,String fsname) throws IOException, InterruptedException{
		String scp = System.getProperty("user.dir") +"/screenshot/Pass/";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(scp+fsname+".png"));
		Thread.sleep(5000);
		
	}
	public static String fspass (WebDriver driver, String ssname) throws IOException, InterruptedException{
		
		
		
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		Date now1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
		String time1 = dateFormat.format(now1);
		//System.out.println("timestamp : "+time1);
        
       
		String scp = System.getProperty("user.dir") +"/screenshot/Pass/"+ssname+time1+"_Pass.png";
		
		ImageIO.write(screenshot.getImage(),"PNG",new File(scp));
		//System.out.println("timestamp : "+time1);
		/*Path source = Paths.get(scp);
		Files.move(source, source.resolveSibling());*/
		
		
		/*File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(scp+ssname+".png"));
		Thread.sleep(1000);*/
		return scp;
	}
	/*public static String fsfail (WebDriver driver, String ssname) throws IOException, InterruptedException{
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		Date now1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
		String time1 = dateFormat.format(now1);
		//System.out.println("timestamp : "+time1);
        
       
		String scp = System.getProperty("user.dir") +"/screenshot/Fail/"+ssname+time1+"_Fail.png";
		
		ImageIO.write(screenshot.getImage(),"PNG",new File(scp));
		
		logger.log(Status.FAIL, MarkupHelper.createLabel(" Test case FAILED due to below issues:", ExtentColor.RED));
		 //test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));

         //test.fail(result.getThrowable())
		 logger.log(Status.FAIL, "Snapshot below: " + MediaEntityBuilder.createScreenCaptureFromPath(scp).build());
		//System.out.println("timestamp : "+time1);
		return scp;
		
		
		
	}*/
	public static String fsfail (WebDriver driver, String ssname) throws IOException, InterruptedException{
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		/*Date now1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
		String time1 = dateFormat.format(now1);*/
		//System.out.println("timestamp : "+time1);
        
       
		String scp = System.getProperty("user.dir") +"/screenshot/Fail/"+ssname+".png";
		
		ImageIO.write(screenshot.getImage(),"PNG",new File(scp));
		
		//logger.log(Status.FAIL, MarkupHelper.createLabel(" Test case FAILED due to below issues:", ExtentColor.RED));
		 //test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));

         //test.fail(result.getThrowable())
		// logger.log(Status.FAIL, "Snapshot below: " + MediaEntityBuilder.createScreenCaptureFromPath(scp).build());
		//System.out.println("timestamp : "+time1);
		return scp;
		
		
		
	}
	
	public static String fsfailesign (WebDriver driver, String ssname) throws IOException, InterruptedException{
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		Date now1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
		String time1 = dateFormat.format(now1);
		//System.out.println("timestamp : "+time1);
        
       
		String scp = System.getProperty("user.dir") +"/screenshot/Fail/"+ssname+time1+"_Fail.png";
		
		ImageIO.write(screenshot.getImage(),"PNG",new File(scp));
		
		//logger.log(Status.FAIL, MarkupHelper.createLabel(" Test case FAILED due to below issues:", ExtentColor.RED));
		 //test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));

         //test.fail(result.getThrowable())
		// logger.log(Status.FAIL, "Snapshot below: " + MediaEntityBuilder.createScreenCaptureFromPath(scp).build());
		//System.out.println("timestamp : "+time1);
		return scp;
		
		
		
	}
	
	public static String ssfail (WebDriver driver,String fsname) throws IOException, InterruptedException{
		Date now1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
		String time1 = dateFormat.format(now1);
		//System.out.println("timestamp : "+time1);
        
       
		String scp = System.getProperty("user.dir") +"/screenshot/Fail/"+fsname+time1+"_Fail.png";
		//String scp = System.getProperty("user.dir") +"/screenshot/Fail/";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(scp));
		Thread.sleep(5000);
		return scp;
		
	}
}
