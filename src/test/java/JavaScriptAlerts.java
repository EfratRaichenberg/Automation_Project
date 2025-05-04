import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;


public class JavaScriptAlerts extends Driver{

    @DataProvider (name = "promptTexts")
    public Object[][] providePromptTexts() {
        return new Object[][] {
                {"AutomationCourse"},
                {"קורס אוטומציה"},
                {"Hi! Hello world :)"},
                {"12345"},
                {""}
        };
    }


    @BeforeMethod
    public void beforeTest()
    {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
    }

    @AfterMethod
    public void afterTest()
    {
        driver.navigate().back();
    }
    @Test
    public void verifyElements(){
//        driver.findElement(By.linkText("JavaScript Alerts")).click();
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
            String buttonAction = button.getAttribute("onclick");
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

//    https://toolsqa.com/selenium-webdriver/alerts-in-selenium/
    @Test
    public void buttonJSAlert() throws InterruptedException {
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        Thread.sleep(1000);
        Alert simpleAlert = driver.switchTo().alert();
        String expectedAlertMessage = "I am a JS Alert";
        String actualAlertMessage = simpleAlert.getText();
        assertEquals(expectedAlertMessage, actualAlertMessage);
        simpleAlert.accept();
        String expectedTextResult = "You successfully clicked an alert";
        String actualTextResult = driver.findElement(By.id("result")).getText();
        assertEquals(expectedTextResult, actualTextResult);
    }

    @Test
    public void buttonJSConfirmAccept() throws InterruptedException {
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Thread.sleep(1000);
        Alert confirmationAlert = driver.switchTo().alert();
        String exceptedAlertMessage = "I am a JS Confirm";
        String actualAlertMessage = confirmationAlert.getText();
        assertEquals(exceptedAlertMessage, actualAlertMessage);
        confirmationAlert.accept();
        String expectedTextResult = "You clicked: Ok";
        String actualTextResult = driver.findElement(By.id("result")).getText();
        assertEquals(expectedTextResult, actualTextResult);
    }
    @Test
    public void buttonJSConfirmDismiss() throws InterruptedException {
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Thread.sleep(1000);
        Alert confirmationAlert = driver.switchTo().alert();
        String exceptedAlertMessage = "I am a JS Confirm";
        String actualAlertMessage = confirmationAlert.getText();
        assertEquals(exceptedAlertMessage, actualAlertMessage);
        confirmationAlert.dismiss();
        String expectedTextResult = "You clicked: Cancel";
        String actualTextResult = driver.findElement(By.id("result")).getText();
        assertEquals(expectedTextResult, actualTextResult);
    }

    @Test (dataProvider = "promptTexts")
    public void buttonJSPromptSendKeys( String inputText) throws InterruptedException {
        driver.findElement(By.xpath("//button[@onClick = 'jsPrompt()']")).click();
        Thread.sleep(1000);
        Alert promptAlert = driver.switchTo().alert();
        String expectedAlertMessage = "I am a JS prompt";
        String actualAlertMessage = promptAlert.getText();
        assertEquals(expectedAlertMessage ,actualAlertMessage);
        promptAlert.sendKeys(inputText);
        promptAlert.accept();
        String expectedTextResult = "You entered:"+(inputText.isEmpty() ? "" : " " + inputText);
        String actualTextResult = driver.findElement(By.id("result")).getText();
        assertEquals(expectedTextResult, actualTextResult);
    }

    @Test
    public void buttonJSPromptDismiss() throws InterruptedException {
        driver.findElement(By.xpath("//button[@onClick = 'jsPrompt()']")).click();
        Thread.sleep(1000);
        Alert promptAlert = driver.switchTo().alert();
        String expectedAlertMessage = "I am a JS prompt";
        String actualAlertMessage = promptAlert.getText();
        assertEquals(expectedAlertMessage ,actualAlertMessage);
        promptAlert.dismiss();
        String expectedTextResult = "You entered: null";
        String actualTextResult = driver.findElement(By.id("result")).getText();
        assertEquals(expectedTextResult, actualTextResult);
    }
}
