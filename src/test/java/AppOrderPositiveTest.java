
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppOrderPositiveTest {
    private WebDriver driver;

    //    @BeforeAll
//    static void configureWebdriver() {
//        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
//    }
    @BeforeAll
    public static void setup() { WebDriverManager.chromedriver().setup(); }

   @BeforeEach
    public void createBrowser() {
       ChromeOptions options = null;
       options = new ChromeOptions();
       options.addArguments("--disable-dev-shm-usage");
       options.addArguments("--no-sandbox");
       options.addArguments("--headless");
       driver = new ChromeDriver(options);
       driver.get("http://localhost:9999");
   }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    void shouldTestSuccessOrderIfCorrectFilling() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иван Иванов");
        elements.get(1).sendKeys("+79231335015");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }


}
