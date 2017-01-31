package androidtests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by FatimaZahra on 27/01/2017.
 */
public class AndroidConfigClass {

    File appDir = new File("..//LeKiosqueAndroid/lekioskAndroid/app/build/outputs/apk/");
    File app = new File(appDir, "app-lekiosk-preprod.apk");

    static AndroidDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    public AndroidConfigClass() {
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("app", app.getAbsolutePath());

        URL url = null;

        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (driver == null) {
            driver = new AndroidDriver(url, capabilities);
            System.out.printf(driver.toString());
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}
