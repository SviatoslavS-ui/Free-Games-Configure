package parentPage;

import libs.ActionsWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ParentPage {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected ActionsWithWebElements actionsElements;
    protected String originalWindow;
    protected Set<String> oldWindowsSet;

    public ParentPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        actionsElements = new ActionsWithWebElements(webDriver);
        originalWindow = webDriver.getWindowHandle();
        oldWindowsSet = webDriver.getWindowHandles();
    }
}

