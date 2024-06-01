package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

    public static void main(String[] args) {
        WebDriver driver;

        System.setProperty("webdriver.chrome.driver", "driver//chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://site.getnet.com.br/");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            WebElement ajudaMenu = driver.findElement(By.xpath("//li//*[contains(text(), 'Ajuda')]"));

            wait.until(ExpectedConditions.elementToBeClickable(ajudaMenu));
            ajudaMenu.click();

            WebElement centralAjuda = driver.findElement(By.xpath("//a[contains(text(), 'Central de ajuda')]"));

            wait.until(ExpectedConditions.visibilityOf(centralAjuda));
            wait.until(ExpectedConditions.elementToBeClickable(centralAjuda));
            centralAjuda.click();

            WebElement inputFaqSearch = driver.findElement(By.id("faq-search-input"));
            WebElement searchButton = driver.findElement(By.cssSelector(" .c-search-faq__button"));

            wait.until(ExpectedConditions.visibilityOf(inputFaqSearch));
            wait.until(ExpectedConditions.elementToBeClickable(inputFaqSearch));
            inputFaqSearch.click();
            inputFaqSearch.sendKeys("Boleto");

            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchButton.click();

            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(), 'Eu concluí a negociação, de que forma receberei meu boleto?')]")));

            WebElement opcaoBoleto = driver.findElement(
                    By.xpath("//h2[contains(text(), 'Eu concluí a negociação, de que forma receberei meu boleto?')]"));

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", opcaoBoleto);

            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(opcaoBoleto));
            wait.until(ExpectedConditions.elementToBeClickable(opcaoBoleto));
            opcaoBoleto.click();

            WebElement modal = driver.findElement(By.cssSelector(".is-modal-open > .o-modal__content"));
            List<WebElement> textoModal = driver
                    .findElements(By.cssSelector(".is-modal-open > .o-modal__content > div"));

            wait.until(ExpectedConditions.visibilityOf(modal));

            for (WebElement resultado : textoModal) {
                System.out.println(resultado.getText());
                if (resultado.toString().length() > 0) {
                    System.out.println("A modal foi aberta e contém o texto esperado.");
                } else {
                    System.out.println("A modal foi aberta, mas o texto esperado não foi encontrado.");
                }
            }

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
