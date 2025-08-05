package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Formulario extends BasePage{
    
    // ========== CAMPOS DE TEXTO (Priorizando ID > CSS > XPath) ==========
    
    // First Name - 
    private final By firstNameField = By.cssSelector("input[placeholder='First Name']");

    // Last Name - 
    private final By lastNameField = By.cssSelector("input[placeholder='Last Name']");

    // Address - 
    private final By addressField = By.cssSelector("textarea.form-control");

    // Email - 
    private final By emailField = By.cssSelector("#eid input");

    // Phone - 
    private final By phoneField = By.cssSelector("input[type='tel']");

    // Password - ID (PERFECTO - máxima prioridad)
    private final By passwordField = By.id("firstpassword");

    // Confirm Password - ID (PERFECTO - máxima prioridad)
    private final By confirmPasswordField = By.id("secondpassword");

    // radioButton Male
    private final By radioButtonMale = By.cssSelector("input[type='radio'][value='Male']");

    // checkbox
    private final By checkbox1 = By.id("checkbox1");
    private final By checkbox2 = By.id("checkbox2");
    private final By checkbox3 = By.id("checkbox3");


    //languages
    private final By languagesField = By.id("msdd");
    private final By languagesDropdown = By.cssSelector(".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content.ui-corner-all");
    private final By languageOptions = By.cssSelector(".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content.ui-corner-all li a");


    //skills
    private final By skillsField = By.id("Skills");

    //country
    private final By countryDropdown = By.id("country");

    //Date of Birth
    private final By yearDropdown = By.id("yearbox");
    private final By monthDropdown = By.cssSelector("select[ng-model='monthbox']");
    private final By dayDropdown = By.id("daybox");

    public void visitarPaginaFormulario(){
        navigateTo("https://demo.automationtesting.in/Register.html");
    }

    

    public void fillFormulario(String firstName, String lastName, String address, String email, String phone, String language, String skill, String country, String year, String month, String day, String password, String confirmPassword){
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(addressField, address);
        sendKeys(emailField, email);
        sendKeys(phoneField, phone);
        clickElement(radioButtonMale);
        clickElement(checkbox1);
        clickElement(checkbox2);
        clickElement(checkbox3);
        selectLanguage(language); // Seleccionar idioma
        selectSkill(skill); // Seleccionar skill
        selectCountry(country); // Seleccionar pais
        selectDateOfBirth(year, month, day);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, confirmPassword);
    }

    public void selectLanguage(String language){
        // 1. Abrir el dropdown de idiomas
        clickElement(languagesField);
        
        // 2. Esperar a que aparezcan las opciones
        findElement(languagesDropdown);
        
        // 3. Buscar y seleccionar el idioma específico
        // Aqui se obtienen todos los elementos del dropdown
        List<WebElement> options = bringMeAllElements(languageOptions);
        
        for (WebElement option : options) {
            String optionText = option.getText();
            System.out.println("- " + optionText);
            if (optionText.equalsIgnoreCase(language)) {
                System.out.println("✅ SELECCIONANDO: '" + language + "'");
                option.click(); // Hacer clic directamente en el WebElement
                break; // Salir del método una vez seleccionado
            }
        }
        
        System.out.println("❌ IDIOMA NO ENCONTRADO: '" + language + "'");
    }

    public void selectSkill(String skill) {
        selectFromDropdown(skillsField, skill);
        System.out.println("✅ SKILL SELECCIONADO: '" + skill + "'");
    }

    public void selectCountry(String country) {
        selectFromDropdown(countryDropdown, country);
        System.out.println("✅ COUNTRY SELECCIONADO: '" + country + "'");
    }

    public void selectDateOfBirth(String year, String month, String day) {
        selectFromDropdown(yearDropdown, year);
        selectFromDropdown(monthDropdown, month);
        selectFromDropdown(dayDropdown, day);
        System.out.println("✅ DATE OF BIRTH SELECCIONADO: '" + year + " - " + month + " - " + day + "'");
    }


}
