package parentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.BonusManagementPage;
import pages.BonusSetupPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class AbstractParentTest {

    protected WebDriver webDriver;
    protected LoginPage loginPage;
    protected BonusManagementPage bonusPage;
    protected BonusSetupPage setupPage;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws Exception {
        webDriver = driverInit();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        bonusPage = new BonusManagementPage(webDriver);
        setupPage = new BonusSetupPage(webDriver);
    }

    private WebDriver driverInit() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
