import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RosettaTest {

    public static DesiredCapabilities capabilities;
    public static URL url;
    public static AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, new File("app/Rozetka.apk").getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appPackage", "ua.com.rozetka.shop");
        capabilities.setCapability("appActivity", "ua.com.rozetka.shop.ui.InitActivity");
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void addItemTest() {
        MobileElement clickMenu = driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]");
        clickMenu.click();
        MobileElement clickCategorySale = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout[1]/androidx.recyclerview.widget.RecyclerView[1]/android.widget.RelativeLayout[4]/android.widget.TextView");
        clickCategorySale.click();
        MobileElement clickSale = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView");
        clickSale.click();
        MobileElement addToCart = driver.findElementById("ua.com.rozetka.shop:id/cv_cart");
        addToCart.click();
        MobileElement checkPopap = driver.findElementById("ua.com.rozetka.shop:id/snackbar_text");
        Assert.assertEquals(checkPopap.getText(),"Товар добавлен в корзину");
    }

    @Test(priority = 2)
    public void checkItemInBasketTest() {

        MobileElement el5 = driver.findElementById("ua.com.rozetka.shop:id/iv_toolbar_cart");
        el5.click();
        MobileElement el6 = driver.findElementById("ua.com.rozetka.shop:id/cart_btn_checkout");
        Assert.assertEquals(el6.getText(),"ОФОРМИТЬ ЗАКАЗ");
        MobileElement el7 = driver.findElementById("ua.com.rozetka.shop:id/item_cart_offer_tv_title");
        Assert.assertEquals(el7.getText(),"Мобильный телефон Huawei P30 Lite 4/64GB Peacock Blue");
    }

    @Test(priority = 3)
    public void deleteItemInBasketTest() {

        MobileElement el1 = driver.findElementById("ua.com.rozetka.shop:id/item_cart_offer_iv_menu");
        el1.click();
        MobileElement el2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        el2.click();
        MobileElement el3 = driver.findElementById("ua.com.rozetka.shop:id/empty_cart_tv_title");
        Assert.assertEquals(el3.getText(),"Корзина пуста");
        MobileElement el4 = driver.findElementById("ua.com.rozetka.shop:id/empty_cart_btn_main");
        el4.click();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

