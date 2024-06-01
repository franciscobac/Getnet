package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;

public class ProvaFrontEndTest {

    private WebDriver _driver;

    public ProvaFrontEndTest(WebDriver driver) {
        _driver = driver;
    }

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(50));
    WebElement ajudaMenu = _driver.findElement(By.xpath("//li//*[contains(text(), 'Ajuda')]"));
    WebElement centralAjuda = _driver.findElement(By.xpath("//a[contains(text(), 'Central de ajuda')]"));
    WebElement inputFaqSearch = _driver.findElement(By.id("faq-search-input"));
    WebElement searchButton = _driver.findElement(By.cssSelector(" .c-search-faq__button"));
    WebElement opcaoBoleto = _driver.findElement(
            By.xpath("//h2[contains(text(), 'Eu concluí a negociação, de que forma receberei meu boleto?')]"));
    WebElement modal = _driver.findElement(By.cssSelector(".is-modal-open > .o-modal__content"));
    List<WebElement> textoModal = _driver.findElements(By.cssSelector(".is-modal-open > .o-modal__content > div"));

    public void CasoDeTesteValidarModal() {

        wait.until(ExpectedConditions.elementToBeClickable(ajudaMenu));
        ajudaMenu.click();

        wait.until(ExpectedConditions.visibilityOf(centralAjuda));
        wait.until(ExpectedConditions.elementToBeClickable(centralAjuda));
        centralAjuda.click();

        wait.until(ExpectedConditions.visibilityOf(inputFaqSearch));
        wait.until(ExpectedConditions.elementToBeClickable(inputFaqSearch));
        inputFaqSearch.click();
        inputFaqSearch.sendKeys("Boleto");

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOf(opcaoBoleto));
        wait.until(ExpectedConditions.elementToBeClickable(opcaoBoleto));
        opcaoBoleto.click();

        wait.until(ExpectedConditions.visibilityOf(modal));

        // Iterar sobre os resultados e capturar o texto
        for (WebElement resultado : textoModal) {
            System.out.println(resultado.getText());
            if (resultado.toString().length() > 0) {
                System.out.println("A modal foi aberta e contém o texto esperado.");
            } else {
                System.out.println("A modal foi aberta, mas o texto esperado não foi encontrado.");
            }
        }
    }

}
