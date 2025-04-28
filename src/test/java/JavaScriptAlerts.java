import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class JavaScriptAlerts extends Driver{

    @Test
    public void verifyElements(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        //String pageName = driver.getTitle();
        //Verify the title in the page
        String expectedPaeTitle = "JavaScript Alerts";
        String actualPageTitle = driver.findElement(By.tagName("h3")).getText();
        assertEquals(expectedPaeTitle, actualPageTitle);

        //Find the list of buttons.
        // Then find all buttons in the list and verify the text on the button.
        WebElement buttonList = driver.findElement(By.tagName("ul"));
        List<WebElement> buttons = buttonList.findElements(By.tagName("button"));
        assertEquals(3, buttons.size());
        for (WebElement button : buttons) {
            String buttonAction = (button.getAttribute("onclick"));
            Assert.assertNotNull(buttonAction);

            String expectedButtonText = "Click for JS " + buttonAction.replace("js", "").replace("()", "");
            String actualButtonText = button.getText();
            assertEquals(expectedButtonText,  actualButtonText);
        }

//      Verify the result element
        String expectedResultText = "Result:";
        String actualResultText = driver.findElement(By.tagName("h4")).getText();
        assertEquals(expectedResultText, actualResultText);
    }

}
