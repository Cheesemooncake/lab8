import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.net.URL;

public class HobbitTest {

    private AppiumDriver driver;

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        //capabilities.setCapability("app", "C:\\Users\\anastasia\\IdeaProjects\\laba\\apks\\org.wikipedia.apk");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("locale", "ru");
        capabilities.setCapability("language", "ru");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHobbitReadingList() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода", 15);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "The Lord of the Rings", "Невозможно найти поле ввода", 5);

        waitForElementAndClick(By.xpath(
                        "//*[@class='android.view.ViewGroup']//*[@text='The Lord of the Rings']"),
                "Невозможно найти статью 'The Lord of the Rings'", 25);

        waitForElementAndClick(By.xpath("//*[@text='Сохранить']"),
                "Не найдена кнопка 'Сохранить'", 25);

        waitForElementAndClick(By.xpath("//*[@text='Добавить в список']"),
                "Невозможно найти пункт 'Добавить в список'", 25);

        waitForElementAndClear(By.xpath("//*[@id='text_input']"),
                "Невозможно очистить поле ввода названия", 15);

        waitForElementAndSendKeys(By.xpath("//*[@id='text_input']"),
                "The Lord of the Rings", "Невозможно ввести название 'The Lord of the Rings'", 15);

        waitForElementAndClick(By.xpath("//*[@text='ОК']"),
                "Невозможно подтвердить название списка", 5);

        waitForElementAndClick(By.xpath("//*[@text='Посмотреть список']"),
                "Невозможно нажать кнопку 'Просмотреть список'", 15);

        waitForElementAndClick(By.xpath("//*[@id='item_overflow_menu']"),
                "Не найдена кнопка с тремя точками для удаления списка", 15);

        waitForElementAndClick(By.xpath("//*[@text='Удалить список']"),
                "Невозможно найти опцию 'Удалить список'", 15);

        waitForElementAndClick(By.xpath("//*[@text='ОК']"),
                "Невозможно подтвердить удаление списка", 15);

        boolean isListPresent = driver.findElements(By.xpath("//*[contains(@text, 'The Lord of the Rings')]")).size() > 0;
        Assert.assertFalse("Список для чтения 'The Lord of the Rings' не был удален", isListPresent);
    }
}
