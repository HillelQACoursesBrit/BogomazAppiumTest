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

public class ExampleTest {

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
            driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout[1]/androidx.recyclerview.widget.RecyclerView[1]/android.widget.RelativeLayout[4]/android.widget.TextView").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView").click();
            driver.findElementById("ua.com.rozetka.shop:id/cv_cart").click();
            Assert.assertEquals(driver.findElementById("ua.com.rozetka.shop:id/snackbar_text").getText(),"Товар добавлен в корзину");
        }

        @Test(priority = 2)
        public void checkItemInBasketTest() {
            driver.findElementById("ua.com.rozetka.shop:id/iv_toolbar_cart").click();
            Assert.assertEquals(driver.findElementById("ua.com.rozetka.shop:id/cart_btn_checkout").getText(),"ОФОРМИТЬ ЗАКАЗ");
            Assert.assertEquals(driver.findElementById("ua.com.rozetka.shop:id/item_cart_offer_tv_title").getText(),"Мобильный телефон Huawei P30 Lite 4/64GB Peacock Blue");
        }

        @Test(priority = 3)
        public void deleteItemInBasketTest() {
            driver.findElementById("ua.com.rozetka.shop:id/item_cart_offer_iv_menu").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView").click();
            Assert.assertEquals(driver.findElementById("ua.com.rozetka.shop:id/empty_cart_tv_title").getText(),"Корзина пуста");
            driver.findElementById("ua.com.rozetka.shop:id/empty_cart_btn_main").click();
        }

        @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }
