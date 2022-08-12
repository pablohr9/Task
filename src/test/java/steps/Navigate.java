package steps;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Navigate {
	WebDriver driver;
	@Given("^Navigate to western union page$")
	public void navigate_to_western_union_page() throws Throwable {
		System.setProperty("webdriver.gecko.driver","driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.navigate().to("https://www.westernunion.com/lt/en/home.html");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Send support to Ukraine']")));
        }
    
    @When("^Expand Burger menu$")
    public void expand_burger_menu() throws Throwable {
        driver.findElement(By.xpath("//button[@class='hamburger-button']")).click();
    }
    
    @When("^Select Settings page")
    public void select_settings_page() throws Throwable {
    	driver.findElement(By.xpath("//a[@role='menuitem']/span[text()='Settings']")).click();
    	WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//translate[text()='Settings']")));
        
    }
    
    @When("^Change WU.com Country to United States")
    public void change_Country_to_United_States() throws Throwable {
    	WebElement selectElement = driver.findElement(By.xpath("//select[@id='Question']"));
    	Select selectObject = new Select(selectElement);
    	selectObject.selectByVisibleText("United States");
    	WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click='redirect();']/translate[text()='Yes']"))).click();
    }
    
    @Then("^Assert that correct country page was loaded.$")
    public void check_country() throws Throwable {
    	WebElement element = driver.findElement(By.id("footer-country-dropdown"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scrolling down the page		
        js.executeScript("arguments[0].scrollIntoView();", element);
        //Get the country value
        String country = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='b-flag-select__flag']/following-sibling::div/div[contains(@class,'b-flag-select__value')]"))).getAttribute("innerHTML");
        Assert.assertEquals("United States", country, "Incorrect country");
        driver.quit();  
    }      
}