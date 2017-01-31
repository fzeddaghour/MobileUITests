package androidtests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Connection;
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

public class NewsstandTestClass {

    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);

    //Check that blocks are visible
    @Test
    public void testACheckBlocksVisibility() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        wait.until(visibilityOfElementLocated(By.id("CDNewsstandUICarousel")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandUICarousel")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandUIHorizontalListView")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("menu_newsstand_action_categories")).isDisplayed());
    }

    //Change category and check that blocks are visible
    @Test
    public void testBChangeCategory() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("menu_newsstand_action_categories")));
        d.getDriver().findElement(By.id("menu_newsstand_action_categories")).click();
        wait.until(visibilityOfElementLocated(By.id("CDCategoryUIIssueCoverActualité")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDCategoryUIIssueCoverQuotidiens")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDCategoryUIIssueCoverInternationaux")).isDisplayed());
        d.getDriver().findElement(By.id("CDCategoryUIIssueCoverActualité")).click();
        wait.until(visibilityOfElementLocated(By.id("CDCategoryIssueUICover")));
        Assert.assertEquals("Actualité", this.d.getDriver().findElement(By.id("CDCategoryIssueSubTitleUIText")).getText());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDCategorySubCategoryUIMenuBar")).isDisplayed());
        d.getDriver().findElement(By.id("android:id/up")).click();
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("menu_newsstand_action_categories")));
    }

    //switch store and check that elements are present
    @Test
    public void testCSwitchStore() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("menu_newsstand_action_categories")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuCountryUIButton")));
        d.getDriver().findElement(By.id("CDMenuCountryUIButton")).click();
        MobileElement listItem = (MobileElement) d.getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.FrameLayout\")).scrollIntoView(new UiSelector().text(\"United Kingdom\"));");

        //GenericClass.scroll("android.widget.FrameLayout","United Kingdom"); //TODO: do not escape slash caracter or try a different locator
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDNewsstandUICarousel")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandUICarousel")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandUIHorizontalListView")).isDisplayed());
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuCountryUIButton")));
        d.getDriver().findElement(By.id("CDMenuCountryUIButton")).click();
        GenericClass.scroll("android.widget.FrameLayout", "France");
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
    }


    //switch product category BD or MAG
    @Test
    public void testDSwitchProductCategory() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandMenuProductTypeUISpinner")).isDisplayed());
        d.getDriver().findElement(By.id("CDNewsstandMenuProductTypeUISpinner")).click();
        d.getDriver().findElement(By.id("CDNewsstandMenuProductTypeUITextBD")).click();
        wait.until(visibilityOfElementLocated(By.id("CDNewsstandUICarousel")));
        Assert.assertEquals("Les BD ", d.getDriver().findElement(By.id("CDNewsstandBlockTitleUIText")).getText());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandUIHorizontalListView")).isDisplayed());
    }
    //Turn off internet connection and check that the butom bar is displayed
    /*
        @Test
        public void testEOfflineMode() throws Exception {
            d.getDriver().setConnection(Connection.AIRPLANE);
            wait.until((visibilityOfElementLocated(By.id("menu_newsstand_action_categories"))));
            Assert.assertTrue(d.getDriver().findElement(By.id("CDNewsstandNoConnectionUIBar")).isDisplayed());
            d.getDriver().setConnection(Connection.ALL);
        }
        */

    @AfterClass
    public static void tearDown() {
        d.getDriver().quit();
    }


}
