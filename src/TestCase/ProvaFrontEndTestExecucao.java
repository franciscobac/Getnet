package TestCase;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import PageObject.*;

public class ProvaFrontEndTestExecucao {

    private ProvaFrontEndTest _provaFrontEndTest;
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver//chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        _provaFrontEndTest = new ProvaFrontEndTest(driver);
    }

    @Test
    public void test() {
        driver.get("https://site.getnet.com.br/");
        _provaFrontEndTest.CasoDeTesteValidarModal();
    }

    @After
    public void fecharBrowser() {
        driver.quit();
    }

}
