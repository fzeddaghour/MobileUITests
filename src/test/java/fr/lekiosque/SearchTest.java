package fr.lekiosque;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.Connection;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by FatimaZahra on 27/01/2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SearchTest {

    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);

    //Online mode

    @Test
    //Should not show results for an unexisting keyword
    public void testASearchWithUnexistingKeyword() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("Recherche")));
        d.getDriver().findElement(By.id("Recherche")).click();
        d.getDriver().findElement(By.id("CDSearchUIText")).clear();
        d.getDriver().findElement(By.id("CDSearchUIText")).sendKeys("XXXX" + "\n");
        wait.until(visibilityOfElementLocated(By.id("CDSearchNoResultUIText")));
        Assert.assertEquals("Aucun résultat", this.d.getDriver().findElement(By.id("CDSearchNoResultUIText")).getText());
    }

    @Test

    //Should show result for titles close to the given keyword
    public void testBSearchWithCloseKeyWord() throws Exception {
        d.getDriver().findElement(By.id("Effacer la requête")).click();
        d.getDriver().findElement(By.id("CDSearchUIText")).sendKeys("poin" + "\n");
        wait.until(visibilityOfElementLocated(By.id("CDSearchItemUICover")));
        d.getDriver().findElement(By.id("CDSearchItemUICover")).click();
        wait.until(visibilityOfElementLocated(By.id("CDIssueTitleUILabel")));
        Assert.assertEquals("Le Point", this.d.getDriver().findElement(By.id("CDIssueTitleUILabel")).getText());
    }

    @Test
    //Should show results for an existing title
    public void testCSearchForExistingTitle() throws Exception {
        d.getDriver().findElement(By.id("CDMenuBackUIButtun")).click();
        wait.until(visibilityOfElementLocated(By.id("Effacer la requête")));
        d.getDriver().findElement(By.id("Effacer la requête")).click();
        d.getDriver().findElement(By.id("CDSearchUIText")).sendKeys("Le Point" + "\n");
        wait.until(visibilityOfElementLocated(By.id("CDSearchItemUICover")));
        d.getDriver().findElement(By.id("CDSearchItemUICover")).click();
        wait.until(visibilityOfElementLocated(By.id("CDIssueTitleUILabel")));
        Assert.assertEquals("Le Point", this.d.getDriver().findElement(By.id("CDIssueTitleUILabel")).getText());
    }

    //OffLine mode

    @Test
    //No results found in offLine mode.
    public void testDSearchInOffLineMode() throws Exception {
        //set connection to OFF
        d.getDriver().setConnection(Connection.AIRPLANE);
        //test Start
        d.getDriver().findElement(By.id("CDMenuBackUIButtun")).click();
        wait.until(visibilityOfElementLocated(By.id("Effacer la requête")));
        d.getDriver().findElement(By.id("Effacer la requête")).click();
        d.getDriver().findElement(By.id("CDSearchUIText")).sendKeys("OffLineText" + "\n");
        wait.until(visibilityOfElementLocated(By.id("CDSearchTextInternetProblem")));
        Assert.assertEquals("Problème de connexion", this.d.getDriver().findElement(By.id("CDSearchTextInternetProblem")).getText());

    }

    //TODO search in cache
    //Search in cache test case could not be tested because the functionality is not implemented, nothing is saved in cache

    @AfterClass
    public static void setConnectionBack() {

        d.getDriver().setConnection(Connection.ALL);
    }

    public static void tearDown() {
        d.getDriver().quit();
    }
}
