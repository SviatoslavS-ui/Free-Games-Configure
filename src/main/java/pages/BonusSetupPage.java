package pages;

import libs.ConfigClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import parentPage.ParentPage;

import java.io.IOException;
import java.util.Set;

public class BonusSetupPage extends ParentPage {

    @FindBy(xpath = "//input[@id='tcBonusPolicyDetails_tpnlGeneral_ucGeneralTab_txtTitle']")
    private WebElement bonusTitleField;

    @FindBy(xpath = "//span[@id='__tab_tcBonusPolicyDetails_tpnlBonus']")
    private WebElement bonusManageTab;

    @FindBy(xpath = "//span[@id='__tab_tcBonusPolicyDetails_tpnlPlayersFilter']")
    private WebElement playerManageTab;

    @FindBy(xpath = "//select[@id='tcBonusPolicyDetails_tpnlBonus_ucBonusTab_ucBonusTypeLogin_ddlBonusAmountType']")
    private WebElement bonusTypeDetail;

    @FindBy(xpath = "//input[@id='tcBonusPolicyDetails_tpnlBonus_ucBonusTab_ucBonusTypeLogin_ucFreeGamesBonusList_lstGames_btnExpandCollapse']")
    private WebElement expandGameList;

    @FindBy(xpath = "//label[contains(text(),'HiLo 21 (1180)')]")
    private WebElement setGameCheckBox;

    @FindBy(xpath = "//table[@class='tblPlayersFilter criteria-table']//label[contains(text(),'Non Depositor')]")
    private WebElement setNonDepositor;

    @FindBy(xpath = "//input[@name='btnSave']")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@id='tcBonusPolicyDetails_tpnlPlayersFilter_ucPlayersFilterTab_btnAttachBonus']")
    private WebElement attachPlayerButton;


    public String gameExpandList = "//input[@name='tcBonusPolicyDetails$tpnlBonus$ucBonusTab$ucBonusTypeLogin$ucFreeGamesBonusList$lstGames$btnExpandCollapse']";
    public String gameDenomination = "//select[@name='tcBonusPolicyDetails$tpnlBonus$ucBonusTab$ucBonusTypeLogin$ucFreeGamesBonusList$ddlDenominations']";
    public String gameBetAmount = "//input[@id='tcBonusPolicyDetails_tpnlBonus_ucBonusTab_ucBonusTypeLogin_ucBonusCodeFixedAmount_txtBonusAmount']";
    public String gameWandering = "//input[@id='tcBonusPolicyDetails_tpnlBonus_ucBonusTab_ucWageringFactor_txtWageringFactor']";
    public String gamePlayerID = "//textarea[@id='tcBonusPolicyDetails_tpnlPlayersFilter_ucPlayersFilterTab_txtExternalPlayerIDs']";
    public String attachPlayer = "//input[@id='tcBonusPolicyDetails_tpnlPlayersFilter_ucPlayersFilterTab_btnAttachBonus']";

    public BonusSetupPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String bonusSetupPageInit() {
        String newWindow = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        actionsElements.switchToOtherWindow(newWindow);
        return newWindow;
    }

    public void defineBonusTitle() {
        try {
            String bonusTitle = ConfigClass.getConfigValue("bonus_title");
            actionsElements.enterTextToTextFields(bonusTitleField, bonusTitle);
            logger.info("Bonus title typed");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            logger.error("can`t get bonus title from file ...");
        }
    }

    public void chooseBonusTab() {
        actionsElements.clickButton(bonusManageTab);
    }

    public void choosePlayerTab() {
        actionsElements.clickButton(playerManageTab);
    }

    public void chooseBonusDetail() {
        actionsElements.selectElementFromDD(bonusTypeDetail, "Free Games");
    }

    public void chooseGameToConfig() {
        actionsElements.clickButtonWhenClickable(gameExpandList);
    }

    public void setGameToConfig() {
        try {
            String gameTitle = ConfigClass.getConfigValue("game_title");
            String gameTitleXpath = "//label[contains(text(),"+gameTitle+")]";
            actionsElements.clickButtonWhenClickable(gameTitleXpath);
            logger.info("Game title selected");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            logger.error("can`t get game title from file ...");
        }
    }

    public void setGameDenomination() {
        try {
            String gameDenom = ConfigClass.getConfigValue("bet_denomination");
            actionsElements.selectFromDDwhenClickable(gameDenomination, gameDenom);
            logger.info("Game denomination selected");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            logger.error("can`t get bet denomination from file ...");
        }
    }

    public void setGameBetAmountAndWandering() {
        actionsElements.enterTextWhenClickable(gameBetAmount, "10");
        actionsElements.enterTextWhenClickable(gameWandering, "0");
    }

    public void setNonDepositor() {
        actionsElements.clickButton(setNonDepositor);
    }

    public void enterPlayerID() {
        try {
           String playerID = ConfigClass.getConfigValue("player_id");
           actionsElements.enterTextWhenClickable(gamePlayerID, playerID);
           logger.info("Player ID entered");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Can`t get Player ID from file ...");
        }
    }

    public void pressSaveButton() {
        actionsElements.clickButtonWith2Alerts(saveButton);
    }

    public void attachPlayerToBonus() {
        actionsElements.clickButton(attachPlayerButton);
    }

}
