import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LogIn extends Driver{

 @Test
    public void logIn(){
     driver.findElement(By.linkText("Form Authentication")).click();
     driver.findElement(By.id("username")).sendKeys("tomsmith");
     driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
     driver.findElement(By.className("radius")).click();

 }

}
