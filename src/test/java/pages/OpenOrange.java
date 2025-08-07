package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class OpenOrange extends BasePage {
    
    // Variable para almacenar el Employee ID
    private String storedEmployeeId = "";
    
    //Locators del login
    private final By inputName = By.cssSelector("input[name='username']");
    private final By inputPassword = By.cssSelector("input[name='password']");
    private final By btnLogin = By.cssSelector("button[type='submit']");


    //Category PIM
    private final By btnPIM = By.cssSelector("a[href='/web/index.php/pim/viewPimModule']");
    private final By btnAddEmployee = By.xpath("//a[text()='Add Employee']");

    //Locators for module add employee
    private final By inputFirstName = By.cssSelector("input[name='firstName']");
    private final By inputMiddleName = By.cssSelector("input[name='middleName']");
    private final By inputLastName = By.cssSelector("input[name='lastName']");
    private final By checkBox = By.cssSelector(".oxd-switch-wrapper");
    //Extraer el valor del input Employee Id
    private final By inputEmployeeIdNew = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
 

    //Locatos for Create Login Details
    private final By inputUsernameEmployee = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By inputPasswordEmployee = By.xpath("(//input[@type='password'])[1]");
    private final By inputConfirmPasswordEmployee = By.xpath("(//input[@type='password'])[2]");

    private final By btnSave = By.cssSelector("button[type='submit']");
    
    // Locator para obtener el Employee ID de la primera fila de resultados en la tabla
    private final By employeeIdInTable = By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-row')][1]//div[@class='oxd-table-cell oxd-padding-cell'][2]//div");

    private final By usernameError = By.cssSelector("span.oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message");

    public void navigateToOrangeHRM() {
        navigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login(String name, String password) {
        sendKeys2(inputName, name);
        sendKeys2(inputPassword, password);
        clickElement(btnLogin);
    }

    public void navigatePIM(){
        clickElement(btnPIM);
        clickElement(btnAddEmployee);
    }

    public String getEmployeeId(){        
        try {
            WebElement element1 = findElement(inputEmployeeIdNew);
            if (element1 != null) {
                String value1 = element1.getAttribute("value");
                if (value1 != null && !value1.isEmpty()) {
                    return value1;
                }
            }
        } catch (Exception e) {
            System.out.println("DEBUG - Error con inputEmployeeIdNew: " + e.getMessage());
        }
        return "";
    }

    public void createEmployee(String firstName, String middleName, String lastName) throws InterruptedException{
        sendKeys2(inputFirstName, firstName);
        sendKeys2(inputMiddleName, middleName);
        sendKeys2(inputLastName, lastName);
        
        // Esperar un poco para que se genere el Employee ID
        Thread.sleep(500);
        
        storedEmployeeId = getEmployeeId();
        System.out.println("🆔 Employee ID capturado durante la creación: " + storedEmployeeId);
        
        clickElement(checkBox);
        Thread.sleep(1500);
    }

    public void fillLoginDetails(String username, String password, String confirmPassword) throws InterruptedException{
        sendKeys2(inputUsernameEmployee, username);
        // Espera breve para que la validación del frontend se dispare
        Thread.sleep(300);
        // Verifica si aparece el mensaje de error
        if (isElementDisplayed(usernameError)) {
            System.out.println("El nombre de usuario '" + username + "' ya existe. Deteniendo el proceso.");
            throw new RuntimeException("Nombre de usuario ya existe: " + username);
        }
        sendKeys2(inputPasswordEmployee, password);
        sendKeys2(inputConfirmPasswordEmployee, confirmPassword);
        clickElement(btnSave);
        Thread.sleep(3000);
    }

    public String getStoredEmployeeId() {
        return storedEmployeeId;
    }

    public String verifyLoginDetails() {
        clickElement(btnPIM);
        
        // Separar el envío del texto y la presión de ENTER
        sendKeys2(inputEmployeeIdNew, getStoredEmployeeId());
        
        // Presionar ENTER de forma separada
        WebElement searchField = findElement(inputEmployeeIdNew);
        searchField.sendKeys(Keys.ENTER);
        
        System.out.println("🔍 Búsqueda ejecutada para Employee ID: " + storedEmployeeId);
        
        
        //Esperar a que aparezcan los resultados de la búsqueda
        try {
            Thread.sleep(3000); // Espera más tiempo para que carguen los resultados
            
            // Verificar que hay resultados en la tabla
            WebElement tableBody = findElement(By.xpath("//div[@class='oxd-table-body']"));
            if (tableBody != null) {
                System.out.println("✅ Tabla de resultados encontrada");
                
                // Extraer el Employee ID de la primera fila de la tabla de resultados
                WebElement employeeIdElement = findElement(employeeIdInTable);
                String employeeIdFromTable = employeeIdElement.getText().trim();
                
                System.out.println("📊 Employee ID almacenado: " + storedEmployeeId);
                System.out.println("📊 Employee ID encontrado en tabla: " + employeeIdFromTable);
                
                // Verificar si los IDs coinciden (pueden tener formato diferente)
                if (employeeIdFromTable.equals(storedEmployeeId) || 
                    employeeIdFromTable.contains(storedEmployeeId) ||
                    storedEmployeeId.contains(employeeIdFromTable)) {
                    System.out.println("✅ Employee IDs coinciden!");
                } else {
                    System.out.println("⚠️ Employee IDs NO coinciden - verificando formato...");
                }
                
                return employeeIdFromTable;
            } else {
                System.out.println("❌ No se encontró la tabla de resultados");
                return "";
            }
            
        } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera: " + e.getMessage());
        }
    }
    
    
}
