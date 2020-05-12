package libs;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;


public class ActionsWithWebElements {

    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait20;

    public ActionsWithWebElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait20 = new WebDriverWait(webDriver, 20);
    }

    public void clickDropdownMenu(WebElement element, String subElementXpath) {
        Actions action = new Actions(webDriver);
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(element));
            action.moveToElement(element).perform();
            WebElement locator = webDriver.findElement(By.linkText(subElementXpath));
            webDriverWait10.until(ExpectedConditions.visibilityOf(locator));
            action.moveToElement(locator).click().build().perform();
            logger.info("Dropdown menu element choosen ...");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void enterTextToTextFields(WebElement element, String text) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Text entered to field ..");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Something went wrong");
        }
    }

    public void clickButton(WebElement element) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(element));
            element.click();
            logger.info("Clicked ..");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Can`t click the button");
        }
    }

    public void selectDropDownInsideIframeAndClick(String iFrameXpath, WebElement element, String addButtonXpath, String itemName) {
        try {
            WebElement iFrame = webDriver.findElement(By.xpath(iFrameXpath));
            webDriverWait10.until(ExpectedConditions.visibilityOf(iFrame));
            webDriver.switchTo().frame(iFrame);
            Select dropDownValue = new Select(element);
            webDriverWait10.until(ExpectedConditions.visibilityOf(element));
            dropDownValue.selectByVisibleText(itemName);

               Thread.sleep(3000);

            WebElement addButton = webDriver.findElement(By.xpath(addButtonXpath));
            webDriverWait10.until(ExpectedConditions.visibilityOf(addButton));
            forceButtonToClick(addButton);
            logger.info("Add Button pressed");

        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Can`t select Drop Down or click button");
        }
    }

    public void selectElementFromDD(WebElement element, String itemName) {
        Select dropDownValue = new Select(element);
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(element));
            dropDownValue.selectByVisibleText(itemName);
            logger.info("Item selected ...");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Can`t select Drop Down");
        }
    }

    public void switchToOtherWindow(String window) {
        webDriver.switchTo().window(window);
    }

    public void clickButtonWhenClickable(String buttonXpath) {
       WebElement currentButton = null;
        try {
            currentButton = webDriver.findElement(By.xpath(buttonXpath));
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(currentButton));
            currentButton.click();
            logger.info("Expand Button pressed");
        } catch (StaleElementReferenceException ex) {
            currentButton = webDriver.findElement(By.xpath(buttonXpath));
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(currentButton));
            currentButton.click();
            logger.info("Expand Button pressed any way ...");
        }
    }

    public void selectFromDDwhenClickable(String dropDownXpath, String itemName) {
        Select dropDownValue = null;
        WebElement dropDown = null;
        try {
            dropDown = webDriver.findElement(By.xpath(dropDownXpath));
            webDriverWait20.until(ExpectedConditions.visibilityOf(dropDown));
            try {
                Thread.sleep(3000);
            } catch (Exception exi) {
                exi.printStackTrace();
            }
            dropDownValue = new Select(dropDown);
            dropDownValue.selectByVisibleText(itemName);
            logger.info("Drop down selected");
        }
        catch (StaleElementReferenceException ex) {
            dropDown = webDriver.findElement(By.xpath(dropDownXpath));
            webDriverWait20.until(ExpectedConditions.visibilityOf(dropDown));
            try {
                Thread.sleep(3000);
            } catch (Exception exi) {
                exi.printStackTrace();
            }
            dropDownValue = new Select(dropDown);
            dropDownValue.selectByVisibleText(itemName);
            logger.info("Drop down selected any way ...");
        }
    }

    public void enterTextWhenClickable(String textFieldXpath, String text) {
        WebElement textField = null;
        try {
            textField = webDriver.findElement(By.xpath(textFieldXpath));
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(textField));
            textField.clear();
            textField.sendKeys(text);
            logger.info("Text printed to field");
        } catch (StaleElementReferenceException ex) {
            textField = webDriver.findElement(By.xpath(textFieldXpath));
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(textField));
            textField.clear();
            textField.sendKeys(text);
            logger.info("Text printed any way ...");
        }
    }

    public void forceButtonToClick(WebElement currentButton) {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);"
                ;
        ((JavascriptExecutor)webDriver).executeScript(script, currentButton);
    }

    public void clickButtonWith2Alerts(WebElement button) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
            logger.info("Button clicked. Alert expected.");
            acceptAlert();
            logger.info("Approve alert processed");
            acceptAlert();
            logger.info("Success alert processed");
        } catch (UnhandledAlertException ex) {
            acceptAlert();
            logger.info("Alert handled any way");
        }
    }

    public void clickButtonWith1Alert(WebElement button) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
            logger.info("Button clicked. Alert expected");
            // acceptAlert();
            logger.info("Success alert processed");
        } catch (UnhandledAlertException ex) {
            acceptAlert();
            logger.info("Alert handled any way");
        }
    }

    protected void acceptAlert() {
        try {
            Alert alert = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert();
            alert.accept();
            logger.info("Alert accepted");
        } catch (NoAlertPresentException ex) {
            logger.error("No alert here ...");
        }
    }


}
