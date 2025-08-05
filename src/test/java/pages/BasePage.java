package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;


public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    
    public static void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
    
    protected void navigateTo(String url) {
        driver.get(url);
    }

    // Método para encontrar un elemento
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    // Método para seleccionar opción de un dropdown
    protected void selectFromDropdown(By locator, String optionText) {
        WebElement selectElement = findElement(locator);
        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText(optionText);
    }
    
    // Método para obtener el valor seleccionado de un dropdown
    protected String getSelectedValueFromDropdown(By locator) {
        WebElement selectElement = findElement(locator);
        Select dropdown = new Select(selectElement);
        return dropdown.getFirstSelectedOption().getText();
    }
    
    protected boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Método para hacer click en un elemento
    protected void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    
    //Método para enviar texto a un campo limpiando el campo
    protected void sendKeys(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Método para enviar texto a un campo sin limpiar
    protected void sendKeys2(By locator, String text) {
        WebElement element = findElement(locator);
        element.sendKeys(text);
    }
    
    // Método para limpiar un campo
    protected void clearElement(By locator) {
        WebElement element = findElement(locator);
        element.clear();
    }
    
    // Método para seleccionar todo el valor de un campo
    protected void selectAllValue(By locator) {
        WebElement element = findElement(locator);
        element.sendKeys(org.openqa.selenium.Keys.COMMAND + "a"); // Seleccionar todo (macOS)
    }
    
    // Método para cambiar a un iframe
    protected void switchToIframe(By iframeLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
    }
    
    // Método para regresar al contenido principal
    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    
    public List<WebElement> bringMeAllElements(By locator){
        return driver.findElements(locator);
    }
    
    
    // Método para verificar si un elemento existe sin lanzar excepción
    protected boolean elementExists(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }


    
}
