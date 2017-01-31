package androidtests;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by FatimaZahra on 31/01/2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ForgottenPasswordTestClass {

    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);
    int step = 0;

    @Before
    public void beforeEachTest() {
        if (step == 1) {
            d.getDriver().findElement(By.id("CDSignInEmailUIText")).clear();
            d.getDriver().findElement(By.id("CDSignInPasswordUIText")).clear();
        }
    }

    @Test
    public void testAAccessSignInView() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuSignInUIButton")));
        d.getDriver().findElement(By.id("CDMenuSignInUIButton")).click();
        wait.until(visibilityOfElementLocated(By.id("CDSignInPasswordForgotUIButton")));
        d.getDriver().findElement(By.id("CDSignInPasswordForgotUIButton")).click();
        step = 1;
    }

    @Test
    //Should raise an error when the email field is empty
    public void testResetPasswordWithEmptyEmailField() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenResetUIButton")));
        d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).click(); //Password reset button
        Assert.assertTrue(d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).isDisplayed());
    }

    @Test
    //Should raise an error when the ID is given instead of the email
    public void testResetPasswordWithID() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenEmailUIText")));
        d.getDriver().findElement(By.id("CDPwdForgottenEmailUIText")).sendKeys("YIEH"); //email field
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenResetUIButton")));
        d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).click(); //Password reset button
        //Assert.assertTrue(d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).isDisplayed());
    }

    @Test
    //Should raise an error when we provide a unexisting email
    public void testResetPasswordWithUnexistingEmail() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenEmailUIText")));
        d.getDriver().findElement(By.id("CDPwdForgottenEmailUIText")).sendKeys("unexisting@email.com"); //email field
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenResetUIButton")));
        d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).click(); //Password reset button
        //Assert.assertTrue(d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).isDisplayed());
    }

    @Test
    //Reset password correctly with a valid email
    public void testResetPasswordWithValidEmail() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenEmailUIText")));
        d.getDriver().findElement(By.id("CDPwdForgottenEmailUIText")).sendKeys("testMobile1.test@lekiosque.fr"); //email field
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenResetUIButton")));
        d.getDriver().findElement(By.id("CDPwdForgottenResetUIButton")).click(); //Password reset button
        wait.until(visibilityOfElementLocated(By.id("CDPwdForgottenCloseUIButton")));
        Assert.assertTrue(d.getDriver().findElement(By.id("CDPwdForgottenCloseUIButton")).isDisplayed()); //Close button of confirmation page
    }

    @AfterClass
    public static void tearDown() {
        d.getDriver().quit();
    }
}

