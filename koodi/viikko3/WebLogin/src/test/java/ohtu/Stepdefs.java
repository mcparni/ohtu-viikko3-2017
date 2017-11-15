package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
	WebDriver driver = new HtmlUnitDriver();
    //WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }
	
	@Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    }
	
	@When("^user \"([^\"]*)\" with password \"([^\"]*)\" is created$")
	public void new_user_is_created(String username, String password) throws Throwable {
		new_username_and_password_and_password_confirmation_are_given(username, password, password);
	}
	
	@When("^a valid username \"([^\"]*)\" and valid password \"([^\"]*)\" but mismatching password confirmation \"([^\"]*)\" are entered$")
	public void valid_username_password_mismatch(String username, String password, String passwordConfirmation) throws Throwable {
		new_username_and_password_and_password_confirmation_are_given(username, password, passwordConfirmation);
	}
	
	
	@When("^valid but reserved username \"([^\"]*)\" and valid password \"([^\"]*)\" are entered$")
	public void reserved_username_and_valid_password_are_entered(String username, String password) throws Throwable { 
		username_and_matching_password_are_entered(username, password);
	}
	@When("^a valid username \"([^\"]*)\" and too short password \"([^\"]*)\" are entered$")
	public void username_and_too_short_password_are_entered(String username, String password) throws Throwable {
		username_and_matching_password_are_entered(username, password);
	}
	
	@When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void username_and_matching_password_are_entered(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
		element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }
	
	@Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
	public void new_unsuccesful_account(String username, String password) throws Throwable {
		new_user_selected();
		username_and_matching_password_are_entered(username, password);
	}
	
	@Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
	public void new_successful_account_created(String username, String password) throws Throwable {
		new_user_selected();
		new_username_and_password_and_password_confirmation_are_given(username, password, password);
		logout();
	}
	
	@When("^new username \"([^\"]*)\" and password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void new_username_and_password_and_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
		element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }

	@When("^too short username \"([^\"]*)\" and valid password \"([^\"]*)\" are entered$")
    public void too_short_username_and_valid_password_are_entered(String username, String password) throws Throwable {
        username_and_matching_password_are_entered(username, password);
    }
	
	@Given("^navigate to main page is selected$")
    public void navigate_to_mainpage() throws Throwable {
        driver.get(baseUrl + "/welcome");
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));       
        element.click();          
    }
	
	@When("^logout is selected$")
    public void logout() throws Throwable {
        driver.get(baseUrl + "/ohtu");
        WebElement element = driver.findElement(By.linkText("logout"));       
        element.click();          
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
	
	@When("^incorrect username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void inusername_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
	
	@Then("^user is logged out$")
    public void logged_out() throws Throwable {
        pageHasContent("Ohtu App");
    }
	
	@Then("^a new user is created$")
    public void new_account_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
	
	@Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void error_report(String error) throws Throwable {
        pageHasContent(error);
		pageHasContent("Create username and give password");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
