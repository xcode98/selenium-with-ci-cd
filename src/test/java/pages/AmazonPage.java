package pages;

import org.openqa.selenium.By;


public class AmazonPage extends BasePage {
    private static final String form_url = "https://www.jotform.com/build/252057434617659?s=templates";
    
    //Locators
    String labelQF = "//label[contains(text(),'Quality Assurance Forms')]";
    String selectQF = "//select[@id='input_3' and @name='q3_qualityAssurance']";
    String optionEmergency = "Emergency";

    
    // Additional form elements using ID locators

    private final By ccaNameField = By.id("label_96");
    private final By ccaFirstNameField = By.id("first_96");
    private final By ccaLastNameField = By.id("last_96");

    private final By qaNameField = By.id("label_97");
    private final By qaFirstNameField = By.id("first_97");
    private final By qaLastNameField = By.id("last_97");
    
    // Date of call
    private final By dateOfCallField = By.id("lite_mode_8");

    //Time of call
    private final By timeOfCallField = By.cssSelector("input[name='widget_timepicker'][type='text']");
    private final By timeOfCallHourField = By.cssSelector("input[aria-label='Hours']");
    private final By timeOfCallMinuteField = By.cssSelector("input[aria-label='Minutes']");
    private final By timeOfCallIframe = By.id("customFieldFrame_92");    
    
    //String filePath = "";
    
    //Input fileAdjunto
    private final By fileAdjunto = By.id("input_89"); 

    public void visitAmazon() {
        navigateTo(form_url);
    }
    
    public String selectQualityAssuranceForm() {
        // Verificar que el label esté presente
        if (isElementDisplayed(By.xpath(labelQF))) {
            // Seleccionar la opción "Emergency" del dropdown
            selectFromDropdown(By.xpath(selectQF), optionEmergency);
            return getSelectedValueFromDropdown(By.xpath(selectQF));
        }
        return "El elemento no se encuentra";
    }
    
    public void fillCCAName(String firstName, String lastName) {
        if(isElementDisplayed(ccaNameField)){
            sendKeys(ccaFirstNameField, firstName);
            sendKeys(ccaLastNameField, lastName);
        }
    }
    
    public void fillQAName(String firstName, String lastName) {
        if(isElementDisplayed(qaNameField)){
            sendKeys(qaFirstNameField, firstName);
            sendKeys(qaLastNameField, lastName);
        }
    }

    public void fillDateOfCall(String date) {
        sendKeys(dateOfCallField, date);
    }

    public void fillTimeOfCall(String hour, String minutes) {
        try {
            // 1. Cambiar al iframe del timepicker
            switchToIframe(timeOfCallIframe);
            
            // 2. Click en el campo de tiempo para abrirlo
            clickElement(timeOfCallField);
            
            // 3. Llenar las horas
            clickElement(timeOfCallHourField);
            selectAllValue(timeOfCallHourField);
            sendKeys(timeOfCallHourField, hour);

            // 4. Llenar los minutos (evitando autocompletado)
            clickElement(timeOfCallMinuteField);
            selectAllValue(timeOfCallMinuteField);
            sendKeys2(timeOfCallMinuteField, minutes);
            
        } finally {
            // 5. SIEMPRE regresar al contenido principal
            switchToDefaultContent();
        }
    }

    public void uploadFile(String filePath) {
        findElement(fileAdjunto).sendKeys(filePath);
    }
}