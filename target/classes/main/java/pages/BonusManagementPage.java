package pages;

import libs.ConfigClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;

import java.io.IOException;

public class BonusManagementPage extends ParentPage {

    @FindBy(xpath = "//a[contains(text(),'System')]")
    private WebElement systemMenu;

    @FindBy(xpath = "//iframe[@id='Wrapper']")
    private WebElement buttonIFrame;

    @FindBy(xpath = "//input[@id='btnAddUpperRight']")
    private WebElement addNewButton;

    @FindBy(xpath = "//select[@name='ucCompBrandsList$drpLstCompanies']")
    private WebElement brandDropDown;

    private String bonusMenuText = "Campaign Management";
    private String addButtonXpath = "//input[@id='btnAddUpperRight']";
    private String iFrameXpath = "//iframe[@id='Wrapper']";

    public BonusManagementPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openCampManMenu() {
        actionsElements.clickDropdownMenu(systemMenu, bonusMenuText);
    }

    public void selectBrandDropDown() {
        try {
            String selectBrandItem = ConfigClass.getConfigValue("brand_name");
            actionsElements.selectDropDownInsideIframeAndClick(iFrameXpath, brandDropDown, addButtonXpath, selectBrandItem);
            logger.info("Brand selected from Drop Down");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            logger.error("can`t get brand from file ...");
        }
    }


}
