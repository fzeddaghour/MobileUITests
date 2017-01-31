package androidtests;

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

public class LibraryTestClass {

    static AndroidConfigClass d = new AndroidConfigClass();
    WebDriverWait wait = new WebDriverWait(d.getDriver(), 10);
    static String obligatoryAccountText = "Compte LeKiosk obligatoire";
    static String obligatoryAccountDescripion = "Vous devez être connecté pour ajouter des magazines ou BD à votre bibliothèque.";

    @Test
    public void testAOpenLibraryNonConnected() throws Exception {
        wait.until(visibilityOfElementLocated(By.id("android:id/up")));
        d.getDriver().findElement(By.id("android:id/up")).click();
        wait.until(visibilityOfElementLocated(By.id("MySpace")));
        d.getDriver().findElement(By.id("MySpace")).click();
        wait.until(visibilityOfElementLocated(By.id("CDTextObligatoryAccount")));
        Assert.assertEquals(obligatoryAccountText, this.d.getDriver().findElement(By.id("CDTextObligatoryAccount")).getText());
        Assert.assertEquals(obligatoryAccountDescripion, this.d.getDriver().findElement(By.id("CDTextObligatoryAccountDescription")).getText());
    }
}


