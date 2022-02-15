
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
import org.testng.annotations.Test;

import java.util.concurrent.*;


@Test
public class atoz {

	public void Precipe()throws  InterruptedException {


		WebDriver driver;


		String strXpath, title, nutrientValues, recipeLink, ingrediant, methods;
		String i1, s1, category, imagelink;
		ArrayList<Object[]> recipedata = new ArrayList<Object[]>();
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		

		driver.get("https://www.tarladalal.com/");

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);



		WebElement atoz =driver.findElement(By.xpath("//*[@id='toplinks']/a[5]"));
		atoz.click();
		WebElement B =driver.findElement(By.xpath("//*[@id=\"ctl00_cntleftpanel_mnuAlphabetsn16\"]/table/tbody/tr/td/a"));
		B.click();  
		
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		int noofrecipes = driver.findElements(By.xpath("//div[@class='rcc_rcpcore']")).size();
		System.out.println(noofrecipes);


		for(int i=1; i<=3; i++) {

			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			strXpath = "//a[@class='respglink']["+i+"]";
			driver.findElement(By.xpath(strXpath)).click();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			for(int j=1; j<=noofrecipes; j++) {

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


				recipedata.add(new Object[] {title,category,ingrediant,methods,nutrientValues,imagelink,recipeLink});
			}








			//@When("user searches for recipe in punjabi page")
			// public void user_searches_for_recipe_in_punjabi_page() {
			System.out.println("************************");
			ExcelFunctions ef = new ExcelFunctions();
			try {
				ef.saveDataToExcel(recipedata);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		//@Then("page with recipe with punjabi title should be displayed")
		//public void page_with_recipe_with_punjabi_title_should_be_displayed() {
		System.out.println("Scraped all the pages");
		driver.close();

	}



}







