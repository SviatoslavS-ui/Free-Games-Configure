package loginTest;

import org.junit.Test;
import parentTest.AbstractParentTest;

public class LoginBackendTest extends AbstractParentTest {

    @Test
    public void backendLogin() {
        loginPage.openPageLogin();
        loginPage.inputLoginName();
        loginPage.inputLoginPassword();
        loginPage.pressLoginButton();
        bonusPage.openCampManMenu();
        bonusPage.selectBrandDropDown();
        String newWindow = setupPage.bonusSetupPageInit();
        System.out.println("New window opened - "+newWindow);
        setupPage.defineBonusTitle();
        setupPage.chooseBonusTab();
        setupPage.chooseBonusDetail();
        setupPage.chooseGameToConfig();
        setupPage.setGameToConfig();
        setupPage.setGameDenomination();
        setupPage.setGameBetAmountAndWandering();
        setupPage.choosePlayerTab();
        setupPage.setNonDepositor();
        setupPage.enterPlayerID();
        setupPage.pressSaveButton();
        setupPage.choosePlayerTab();
        setupPage.attachPlayerToBonus();

    }


}
