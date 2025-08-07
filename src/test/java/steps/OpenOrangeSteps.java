package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BasePage;
import pages.OpenOrange;

public class OpenOrangeSteps {
    
    OpenOrange op = new OpenOrange();

    @Before
    public void setUp() {
        BasePage.initializeDriver();
        op = new OpenOrange();
    }
    
    @After
    public void tearDown() {
        //BasePage.quitDriver();
    }

@Given("^Ingreso al login de OrangeHRM con usuario \"([^\"]*)\" y contraseña \"([^\"]*)\" y acceso al dashboard$")
public void navigateToLoginPage(String username, String password) {
    op.navigateToOrangeHRM();
    op.login(username, password);
}

@When("^Creo un nuevo empleado con primer nombre \"([^\"]*)\" segundo nombre \"([^\"]*)\" apellido \"([^\"]*)\" usuario \"([^\"]*)\" contrasena1 \"([^\"]*)\" contrasena2 \"([^\"]*)\"$")
public void createEmployeeWithDetails(String firstName, String middleName, String lastName, String employeeUsername, String password1, String password2) throws InterruptedException {
    op.navigatePIM();
    op.createEmployee(firstName, middleName, lastName);
    op.getEmployeeId();
    op.fillLoginDetails(employeeUsername, password1, password2);
}

@Then("^Verifico que el empleado se creo correctamente")
public void verifyLogin() throws InterruptedException {
    String employeeIdFromTable = op.verifyLoginDetails();
    Assert.assertEquals("Los Employee IDs no coinciden", op.getStoredEmployeeId(), employeeIdFromTable);
}

}
