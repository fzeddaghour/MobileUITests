package fr.lekiosque;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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

public class ProductSheetTest {

    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);
    SignUpTest s = new SignUpTest();

    @BeforeClass
    public void BeforeAll() {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuSignUpUIButton")));
        d.getDriver().findElement(By.id("CDMenuSignUpUIButton")).click();
        try {
            s.testGSignUpWithValidCredentials();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAAccessAndCreateAccount() throws Exception {

    }

    @AfterClass
    public static void tearDown() {
        d.getDriver().quit();
    }
}
