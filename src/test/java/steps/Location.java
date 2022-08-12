package steps;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Location {
	WebDriver driver;
	@Given("^Open western union page$")
	public void navigate_to_western_union_page() throws Throwable {
		System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.navigate().to("https://www.westernunion.com/lt/en/home.html");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Send support to Ukraine']")));
        }
    
    @When("^open agent locations$")
    public void open_agent_location() throws Throwable {
        driver.findElement(By.xpath("//button[@class='hamburger-button']")).click();
        driver.findElement(By.xpath("//ul[contains(@class,'nav-dropdown')]/li/a[@role='menuitem']/span[text()='Find locations']")).click();
        //change frame
    	WebElement frame = driver.findElement(By.xpath("//div[@class='findlocationsiframe section']/iframe"));
        driver.switchTo().frame(frame);
    	WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Find locations']")));
        driver.findElement(By.xpath("//input[@placeholder='Address, city, state or ZIP']")).sendKeys("08247");
        driver.findElement(By.xpath("//span[text()='Continue']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'08247')]")));
    }
    @Then("^Get address details of the first location$")
    public void get_address_details_of_the_first_location() throws Throwable {
    	 //save all results into a list
    	 List<WebElement> results= driver.findElements(By.xpath("//div[contains(@class,'wu_LocationCard')][@data-wuid]"));
         System.out.print(results.get(0).getText());
         driver.quit();  
    }
}