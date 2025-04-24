import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Driver {
    public WebDriver driver;

@BeforeClass
    public void  SetUp() throws InterruptedException {
        driver = new ChromeDriver() ;
        driver.manage().window().maximize();
     driver.get("https://the-internet.herokuapp.com/");
     Thread.sleep(3000);
    }

    @AfterClass
 public void tearDown()
 {
     if (driver!=null)
         driver.quit();
 }
}
