package com.DOTC.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DOTC.stepDefinitions.DOTCMasterSteps;
import com.DOTC.supportLibraries.DriverManager;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

 
public class DOTCCommonMethods  extends DOTCMasterSteps{
       WebDriver driver = DriverManager.getWebDriver();
       WebDriverWait wait = new WebDriverWait(driver, 20);
      
       public void launchURL(String url, By userName) {
              try {
                     driver.get(url);
                     try {
         				driver.manage().window().maximize();
         			} catch (Exception e) {
         			} //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                     wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
                     currentScenario.embed(Util.takeScreenshot(driver),"image/png");
              }
              catch(Exception ex) {
                     ex.printStackTrace();
              }
       }
      
       public void clickObject(By ObjName) {
              try{
                     WebElement element = Util.waitForObjToLoad(driver,ObjName);
                     if(element!=null){
                           Actions actions = new Actions(driver);
                           actions.moveToElement(element);               
                           Thread.sleep(100);
                           element.click();
                           Thread.sleep(100);
                     }
              }catch(Exception ex){
                     ex.printStackTrace();
              }
       }
      
       public void SetData(By ObjName,String strData){
             
              try{
                     WebElement element = Util.waitForObjToLoad(driver,ObjName);
                     if(element!=null){
                           Actions actions = new Actions(driver);
                           actions.moveToElement(element);               
                           Thread.sleep(100);
                           element.clear();
                           Thread.sleep(100);
                           element.sendKeys(strData);
                           Thread.sleep(100);
                     }
              }catch(Exception ex){
                     ex.printStackTrace();
              }
       }
      
       public Boolean valObjExistence(By ObjName,Boolean blnFlag){
              Boolean objExistnc = false;
              Boolean objReturnFlag = false;
             
              try{
                     WebElement element = Util.waitForObjToLoad(driver,ObjName);
                     if(element!=null){                     
                           objExistnc = true;                     
                     }
                    
                     if(blnFlag==objExistnc){
                           objReturnFlag = true;
                     }
              }catch(Exception ex){
                     ex.printStackTrace();
              }
              return objReturnFlag;
       }
       
       public void setMessageAndScreenShot(boolean status, String message) {
    	   try {
    		   if(status)
    			   ExtentTestManager.getTest().log(LogStatus.PASS, message,
   					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    		   else
    			   ExtentTestManager.getTest().log(LogStatus.FAIL, message,
    	   					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    	   } catch(Exception ex) {
    		   ex.printStackTrace();
    	   }
       }
      
       
}
 