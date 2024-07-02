import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyAccountRegistration {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //  @AfterMethod
    // public void tearDown() {
    //     driver.quit();
    // }

@Test
 public void testAccountRegistration(){

        driver.get("https://shop.pragmatic.bg/");
        driver.findElement(By.xpath("(//li[@class='dropdown']/a[@class='dropdown-toggle'])[1]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
   //     WebElement registrationButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-toggle'])[1]//following-sibling::ul/li[1]/a")));
     //   registrationButton.click();

    WebElement registrationButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#top-links .dropdown-menu li:nth-of-type(1)")));

    Actions actions = new Actions(driver);
    actions.moveToElement(registrationButton).click().perform();
    WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
    firstNameField.sendKeys("Marieta");
    WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastname")));
    lastNameField.sendKeys("Chipova");
    String prefix = RandomStringUtils.randomAlphanumeric(7);
    String sufix = RandomStringUtils.randomAlphabetic(5);
    String emailAddress = prefix + "@" + sufix + ".com";
    driver.findElement(By.id("input-email")).sendKeys(emailAddress);
    driver.findElement(By.id("input-telephone")).sendKeys("0786543334");
    driver.findElement(By.id("input-password")).sendKeys("chipovaMarieta");
    driver.findElement(By.id("input-confirm")).sendKeys("chipovaMarieta");
    WebElement checkboxAgree = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pull-right input:nth-of-type(1)")));
    checkboxAgree.click();
    driver.findElement(By.cssSelector(".pull-right input[value='Continue']")).click();
    WebElement messageregistered = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content p:nth-of-type(2)")));
    String actualMessage = messageregistered.getText();
    Assert.assertEquals(actualMessage, "You can now take advantage of member privileges to enhance your online shopping experience with us.");


    }
}
