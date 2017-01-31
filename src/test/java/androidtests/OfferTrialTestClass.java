package androidtests;

import io.appium.java_client.MobileElement;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by FatimaZahra on 31/01/2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class OfferTrialTestClass {

    GenericClass generic;
    public String randomEmail = generic.emailAccount();
    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);
    //Xpaths
    private String helpSettings = "//android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"; //Aide & Paremetres menu title
    private String developerSettings = "//android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"; //Developer Settings menu title
    private String popinMessage = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ScrollView[1]/android.widget.TextView[1]";
    private String cardNumberTextBox = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]";
    private String cardExpirationMonth = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]";
    private String cardExpirationYear = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[2]";
    private String cardCryptogram = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[2]";
    private String popinTitle = "android:id/alertTitle";
    private String popinBtn1 = "android:id/button1";
    private String popinButtonsPanel = "android:id/buttonPanel";

    @Test
    //Should switch to preprod database
    public void testASwitchToPreProdEnvironment() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuBtnSettings")));
        d.getDriver().findElement(By.id("CDMenuBtnSettings")).click();
        wait.until(visibilityOfElementLocated(By.xpath(helpSettings)));
        MobileElement listItem = (MobileElement) d.getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"Developer Settings\"));");
        listItem.click();
        //GenericClass.scroll("android.widget.ListView","Developer Settings");
        wait.until(visibilityOfElementLocated(By.xpath(developerSettings)));
        listItem = (MobileElement) d.getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"Environment : PRODUCTION\"));");
        listItem.click();
        //GenericClass.scroll("android.widget.ListView","Environment : PRODUCTION");
        wait.until(visibilityOfElementLocated(By.id("CDSwitchToPreProdEnvUIButton")));
        listItem = (MobileElement) d.getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"DEVELOPMENT\"));");
        listItem.click();
        //GenericClass.scroll("android.widget.ListView","DEVELOPMENT");
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        d.getDriver().findElement(By.id("android:id/up")).click();
        d.getDriver().findElement(By.id("android:id/up")).click();
        Assert.assertTrue(d.getDriver().findElement(By.id("menu_newsstand_action_categories")).isDisplayed());
    }


    //Offline mode
    @Test
    //Should be asked to login or register
    public void testBConnectOrRegister() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until((visibilityOfElementLocated(By.id("Offers"))));
        d.getDriver().findElement(By.id("Offers")).click();
        wait.until(visibilityOfElementLocated(By.id("CDOfferTriaDiscoverlUIButton")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDOfferTriaDiscoverlUIButton")).isDisplayed());
        d.getDriver().findElement(By.id("CDOfferTriaDiscoverlUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath(popinMessage)));
        Assert.assertEquals("Vous devez posséder un compte LeKiosk pour souscrire à un forfait", d.driver.findElement(By.xpath(popinMessage)).getText());
    }

    @Test
    //Should create an account to proceed subscription
    public void testCCreateAnAccount() throws Exception {
        d.getDriver().findElement(By.id(popinBtn1)).click();
        wait.until(visibilityOfElementLocated(By.id("CDSignUpEmailUIButton")));
        d.getDriver().findElement(By.id("CDSignUpEmailUIText")).sendKeys(randomEmail);
        d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).sendKeys("1234");
        d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).sendKeys("1234");
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.id(popinButtonsPanel)));
        Assert.assertTrue(d.getDriver().findElement(By.xpath(popinMessage)).isDisplayed());
        Assert.assertEquals("Vous devez renseigner un moyen de paiement afin de profiter de l’offre d’essai gratuit", d.getDriver().findElement(By.xpath(popinMessage)).getText());
    }

    /*
    @Test
    //Should subscribe to the free trial offer
    public void testDSubscribeToTrialOffer() throws Exception {
        d.getDriver().findElement(By.id(popinBtn1)).click();
        Assert.assertEquals("Choisissez votre moyen de paiement", d.getDriver().findElement(By.id(popinTitle)).getText());
        d.getDriver().findElement(By.id(popinBtn1)).click();
        wait.until(visibilityOfElementLocated(By.xpath(cardNumberTextBox)));
        d.getDriver().findElement(By.xpath(cardNumberTextBox)).sendKeys("1111222233334444");
        d.getDriver().findElement(By.xpath(cardExpirationMonth)).sendKeys("01");
        d.getDriver().findElement(By.xpath(cardExpirationYear)).sendKeys("2019");
        d.getDriver().findElement(By.xpath(cardCryptogram)).sendKeys("123");
        d.getDriver().findElement(By.id(popinBtn1)).click();
        Assert.assertEquals("Achat en cours...", d.getDriver().findElement(By.xpath(popinMessage)).getText());
        //wait for 2 to 5 secends before checking next condition
       // wait.until(visibilityOfElementLocated(By.id(popinTitle)));
        Assert.assertEquals("Félicitation", d.getDriver().findElement(By.id(popinTitle)).getText());
        d.getDriver().findElement(By.id(popinBtn1)).click();
        wait.until(visibilityOfElementLocated(By.id("menu_newsstand_action_categories")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        Assert.assertEquals("10 crédits",d.getDriver().findElement(By.id("CDMenuCreditsUIText")).getText());
    }
    */

    //TODO not eligible mode
    //TODO Access offer trial page from different paths


    @AfterClass
    public static void tearDown() {
        d.getDriver().quit();
    }

}
