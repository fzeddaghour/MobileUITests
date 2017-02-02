package fr.lekiosque;

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

/* This test class is to find every element of the Menu Drawer for Connected & non connected users */

public class MenuTest {

    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);

    //Anonymous User:
    @Test
    public void testAMenuUserInfoNonConnected() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuUserName")));

        Assert.assertTrue(d.getDriver().findElement(By.id("CDMenuAvatar")).isDisplayed());
        Assert.assertEquals("Anonyme", this.d.getDriver().findElement(By.id("CDMenuUserName")).getText());
        Assert.assertEquals("0 crédit", this.d.getDriver().findElement(By.id("CDMenuCredit")).getText());
    }

    @Test
    public void testBMenuIems() throws Exception {
        //Bloc MenuItems - Kiosque, Ma bibliohèque, Lecture en cours, Nos offres:
        Assert.assertTrue(d.getDriver().findElement(By.id("Newsstand")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("MySpace")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("Reader")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("Offers")).isDisplayed());
    }

    @Test
    public void testCMenuSettingsnStore() throws Exception {
        //Bloc Aide & Paramètres, Store:
        Assert.assertTrue(d.getDriver().findElement(By.id("CDMenuBtnSettings")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android" +
                ".support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android" +
                ".widget.RelativeLayout[1]/android.widget.RelativeLayout[1]")).isDisplayed());
    }

    @Test
    public void testDMenuSignInButton() throws Exception {
        //Bouton Connexion
        wait.until(visibilityOfElementLocated(By.id("CDMenuSignInUIButton")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDMenuSignInUIButton")).isDisplayed());
    }

    @Test
    public void testDMenuSignUpButton() throws Exception {
        //Bouton Inscription
        Assert.assertTrue(d.getDriver().findElement(By.id("CDMenuSignUpUIButton")).isDisplayed());
    }

    @Test
    public void testEMenuUserInfoLoggedIn() {
        //Pré-Condition: Log in with valid account
        //Clear any text if i needed
        d.getDriver().findElement(By.id("CDMenuSignInUIButton")).click();
        wait.until(visibilityOfElementLocated(By.id("CDSignInEmailUIText")));
        d.getDriver().findElement(By.id("CDSignInEmailUIText")).clear();
        d.getDriver().findElement(By.id("CDSignInPasswordUIText")).clear();
        d.getDriver().findElement(By.id("CDSignInEmailUIText")).sendKeys("test@menu.fr");
        d.getDriver().findElement(By.id("CDSignInPasswordUIText")).sendKeys("1111");
        wait.until(visibilityOfElementLocated(By.id("CDSignInUIButton")));
        d.getDriver().findElement(By.id("CDSignInUIButton")).click();

        //Close the Offer popin.
        wait.until(visibilityOfElementLocated(By.id("CDOfferInvitationUIButtoncloseBtn")));
        if (d.getDriver().findElement(By.id("CDOfferInvitationUIButtoncloseBtn")).isDisplayed() == true) {
            d.getDriver().findElement(By.id("CDOfferInvitationUIButtoncloseBtn")).click();
        }

        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        Assert.assertEquals("test@menu.fr", this.d.getDriver().findElement(By.id("CDMenuUserName")).getText());
        Assert.assertEquals("5 crédits", this.d.getDriver().findElement(By.id("CDMenuCredit")).getText());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDMenuDisconnectButton")).isDisplayed());
    }

    @AfterClass
    public static void tearDown() {
        d.getDriver().quit();
    }

}
