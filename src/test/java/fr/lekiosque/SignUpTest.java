package fr.lekiosque;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by FatimaZahra on 31/01/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SignUpTest {


    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);
    int step = 0;

    @Before
    public void beforeEachTest() {
        if (step == 1) {
            d.getDriver().findElement(By.id("CDSignUpEmailUIText")).clear();
            d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).clear();
            d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).clear();
        }
    }

    @Test
    public void testAGraphicControll() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("CDMenuSignUpUIButton")));
        d.getDriver().findElement(By.id("CDMenuSignUpUIButton")).click();
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpTitle1UILabel")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpFacebookUIButton")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpTitle2UILabel")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpEmailUIText")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).isDisplayed());
        Assert.assertTrue(d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).isDisplayed());
    }

    @Test
    public void testBCreateAccountWithEmptyFields() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("CDSignUpEmailUIButton")));
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")));
        d.getDriver().findElement(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")).click();
    }

    @Test
    public void testCSingUpWithWrongEmail() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("CDSignUpEmailUIButton")));
        d.getDriver().findElement(By.id("CDSignUpEmailUIText")).sendKeys("wrong@mail");
        d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).sendKeys("1111");
        d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).sendKeys("1111");
        d.getDriver().hideKeyboard();
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")));
        d.getDriver().findElement(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")).click();
        step = 1;
    }

    @Test
    public void testDValidEmailShortPw() throws Exception {
        d.getDriver().findElement(By.id("CDSignUpEmailUIText")).sendKeys("correct@mail.com");
        d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).sendKeys("11");
        d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).sendKeys("11");
        d.getDriver().hideKeyboard();
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")));
        d.getDriver().findElement(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")).click();
    }

    @Test
    public void testEValidEmailDifferentPasswords() throws Exception {
        d.getDriver().findElement(By.id("CDSignUpEmailUIText")).sendKeys("correct@mail.com");
        d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).sendKeys("1111");
        d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).sendKeys("1112");
        d.getDriver().hideKeyboard();
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")));
        d.getDriver().findElement(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")).click();
    }

    @Test
    public void testFSignUpWithExistingAccount() throws Exception {
        d.getDriver().findElement(By.id("CDSignUpEmailUIText")).sendKeys("m.doudouch@lekiosque.fr");
        d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).sendKeys("1111");
        d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).sendKeys("1111");
        d.getDriver().hideKeyboard();
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")));
        d.getDriver().findElement(By.xpath("//android.widget.FrameLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
                "/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]" +
                "/android.widget.Button[1]")).click();
    }

    @Test
    public void testGSignUpWithValidCredentials() throws Exception {
        //Generate a string with random 5 caracters:
        String login = GenericClass.emailAccount();
        d.getDriver().findElement(By.id("CDSignUpEmailUIText")).sendKeys(login);
        d.getDriver().findElement(By.id("CDSignUpPasswordUIText")).sendKeys("1111");
        d.getDriver().findElement(By.id("CDSignUpPasswordConfirmUIText")).sendKeys("1111");
        d.getDriver().hideKeyboard();
        d.getDriver().findElement(By.id("CDSignUpEmailUIButton")).click();
        wait.until(visibilityOfElementLocated(By.id("CDSignUpCompleted")));
        d.getDriver().findElement(By.id("CDSignUpCompleted")).click();
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
    }

    /*
    @Test
    public void testHSignUpWithFacbook() throws Exception{

        //If you need a facebook test account, use this link:
        //https://lekiosk.atlassian.net/wiki/display/QA/Creating+and+managing+Facebook+test+users

        d.getDriver().findElement(By.id("CDSignUpFacebookUIButton")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.LinearLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]" +
                "/android.widget.LinearLayout[1]/android.webkit.WebView[1]" +
                "/android.webkit.WebView[1]")));
        d.getDriver().findElement(By.xpath("//android.widget.LinearLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]" +
                "/android.widget.LinearLayout[1]/android.webkit.WebView[1]" +
                "/android.webkit.WebView[1]/android.view.View[3]" +
                "/android.widget.EditText[1]")).sendKeys("ymunyhr_baoson_1475246842@tfbnw.net");
        d.getDriver().findElement(By.xpath("//android.widget.LinearLayout[1]" +
                "/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]" +
                "/android.widget.LinearLayout[1]/android.webkit.WebView[1]" +
                "/android.webkit.WebView[1]/android.view.View[3]" +
                "/android.widget.EditText[2]")).sendKeys("lekiosk1234");
        d.getDriver().findElement(By.id("Connexion")).click();
        wait.until(visibilityOfElementLocated(By.id("OK")));
        d.getDriver().findElement(By.id("OK")).click();
        wait.until(visibilityOfElementLocated(By.id("CDSignUpCompleted")));
        d.getDriver().findElement(By.id("CDSignUpCompleted")).click();
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
    }
    */

    @AfterClass
    public static void tearDown() {
        d.getDriver().quit();
    }
}


