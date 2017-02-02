package fr.lekiosque;

import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by FatimaZahra on 31/01/2017.
 */
public class GenericClass {

    static AndroidConfigClass d = new AndroidConfigClass();

    public static final String kioskMail = ".test@lekiosque.fr";
    public static final String lk = "LK";

    //Generate random email addresses to be used to create new test accounts
    public static String emailAccount() {

        String email = lk + RandomStringUtils.random(6, true, true) + kioskMail;
        return email;
    }

    //Scroll up/down into a list view and select an element
    public static void scroll(String elementClassName, String itemTitle) {
        MobileElement listItem = (MobileElement) d.getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(" + elementClassName + ")).scrollIntoView(new UiSelector().text(" + itemTitle + "));");
        listItem.click();
    }

    //Check if an element is present
    public boolean isElementPresent(By by) {
        try {
            d.getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
