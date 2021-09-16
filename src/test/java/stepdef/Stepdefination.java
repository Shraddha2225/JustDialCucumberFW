package stepdef;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Stepdefination {
    WebDriver driver;
    String url = " https://justdial.com";
    Scenario scenario;


    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
        scenario.log("executed before each scenario");
    }

    @After
    public void cleanUp(){
        if (!(driver==null)){
            driver.quit();
        }
        scenario.log("executed after each scenario");
    }

    @BeforeStep
    public void beforeEachStep(){
        scenario.log("executed before each line of test case");
    }
    @AfterStep
    public void afterEachStep(){
        scenario.log("executed after each line of test case");
        if(!(driver==null)){
            TakesScreenshot screenshot =(TakesScreenshot)driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data,"image/png/jpeg","Failed step name" +scenario.getName());
        }
        log.debug("Each step hook is executed,screen shots taken");
    }


    //TC1:login
    @Given("User open the browser")
    public void user_open_the_browser() {
        driver = new ChromeDriver();
        log.debug("chrome initialized");
        driver.manage().window().maximize(); // maximize browser window
        log.debug("Browser maximized");
        driver.manage().deleteAllCookies(); // delete all cookies
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        log.debug("Each step hook is executed,screen shots taken");
    }

    @Given("User navigates to the application url")
    public void user_navigates_to_the_application_url() {
        driver.get(url);
        log.debug("open the url");
    }
    @When("User clicks on Sign up link at the top right corner of the application")
    public void user_clicks_on_sign_up_link_at_the_top_right_corner_of_the_application() {
        driver.findElement(By.id("h_sin_up")).click();
        log.debug(" click on sign up button");

    }
    @When("User enters name and Phone number as mention in below table and clicks on Submit Button")
    public void user_enters_name_and_phone_number_as_mention_in_below_table_and_clicks_on_submit_button(Map<String,String> userCred) {
        driver.findElement(By.id("lgn_name")).sendKeys(userCred.get("name"));
        log.debug("enter name");
        driver.findElement(By.id("lgn_mob")).sendKeys(userCred.get("phone number"));
        log.debug("enter phone number");
        driver.findElement(By.id("lgn_smtn")).click();
        log.debug("click on send otp button");
    }
    @Then("User is displayed with the message as OTP is sent on number")
    public void user_is_displayed_with_the_message_as_otp_is_sent_on_number() {
        WebElement displayMsg = driver.findElement(By.xpath("//div[@class='otp-text wrapper pb-10']"));
        Assert.assertEquals(displayMsg.isDisplayed(),true,"OTP is sent on number");
    }
}
