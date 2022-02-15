import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.concurrent.*;

public class Health {
 
  
	 


  //public void main(String[] args)throws  InterruptedException {

	  		WebDriver driver;

	  		 @Test
	  		 public void Webscrapping() throws InterruptedException {




	  		String strXpath, title, nutrientValues, recipeLink, ingrediant, methods;
	  		String i1, s1, category, imagelink;
	  		ArrayList<Object[]> recipedata = new ArrayList<Object[]>();  


	  		WebDriverManager.chromedriver().setup();

	  		driver = new ChromeDriver();
	  		driver.manage().window().maximize();

	  		driver.get("https://www.tarladalal.com/");

	  		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);






	  		WebElement label=driver.findElement(By.xpath("//input[@name='ctl00$txtsearch']"));
	  		label.sendKeys("Healthy heart");
	  		WebElement submit=driver.findElement(By.xpath("//input[@id='ctl00_imgsearch']"));
	  		submit.click();

	  		Thread.sleep(1000);
	  		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	  		int noofrecipes = driver.findElements(By.xpath("//div[@class='rcc_rcpcore']")).size();
	  		System.out.println(noofrecipes);


	  		for(int i=1; i<=4; i++) {

	  			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	  			strXpath = "//a[@class='respglink']["+i+"]";

	  			driver.findElement(By.xpath(strXpath)).click();
	  			try {
	  				Thread.sleep(2000);
	  			} catch (InterruptedException e) {

	  				e.printStackTrace();
	  			}

	  			for(int j=1; j<=20; j++) {

	  				WebElement singlerecipe = driver.findElement(By.xpath("(//span[@class='rcc_recipename'])["+j+"]/a"));
	  				title = singlerecipe.getText();
	  				recipeLink = singlerecipe.getAttribute("href");
	  				singlerecipe.click();

	  				WebElement categoryElement = driver.findElement(By.xpath("(//span[@itemprop='name'])[5]"));
	  				category = categoryElement.getText();

	  				WebElement imageLinkElement = driver.findElement(By.xpath("(//img[@id='ctl00_cntrightpanel_imgRecipe'])[1]"));
	  				imagelink = imageLinkElement.getAttribute("src");

	  				WebElement NutrientvaluesElement = driver.findElement(By.xpath("//*[@id='accompaniments']"));

	  				nutrientValues = NutrientvaluesElement.getText();

	  				WebElement Ingrediant = driver.findElement(By.xpath("//*[@id='rcpinglist']"));
	  				ingrediant = Ingrediant.getText();

	  				WebElement Methods = driver.findElement(By.xpath("//*[@id='recipe_small_steps']"));
	  				methods = Methods.getText();
	  				try {
	  					Thread.sleep(2000);
	  				} catch (InterruptedException e) {

	  					e.printStackTrace();
	  				}


	  				driver.navigate().back();

	  				Thread.sleep(3000);
Reporter.log("Successfully scrapped the recipes");

	  				recipedata.add(new Object[] {title,category,ingrediant,methods,nutrientValues,imagelink,recipeLink});
	  				ExcelFunctions ef = new ExcelFunctions();
	  				try {
	  					ef.saveDataToExcel(recipedata);
	  				} catch (Exception e) {

	  					e.printStackTrace();
	  				}
	  			}
	  		}

	  	
  }
}
