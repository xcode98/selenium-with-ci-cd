package steps;



import org.junit.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.Formulario;
import pages.BasePage;

public class FormularioSteps {
    
    private Formulario formulario;
    
    @Before
    public void setUp() {
        BasePage.initializeDriver();
        formulario = new Formulario();
    }
    
    @After
    public void tearDown() {
        //BasePage.quitDriver();
    }
    
    @Dado("^completo el formulario con nombre \"(.+)\" apellido \"(.+)\" direccion \"(.+)\" email \"(.+)\" telefono \"(.+)\" genero \"(.+)\" idioma \"(.+)\" pais \"(.+)\" anio \"(.+)\" mes \"(.+)\" dia \"(.+)\" password \"(.+)\" confirmPassword \"(.+)\"$")
    public void completoFormularioConParametros(String nombre, String apellido, String direccion, String email, String telefono, String genero, String idioma, String pais, String anio, String mes, String dia, String password, String confirmPassword) {
        formulario.visitarPaginaFormulario();
        
        // Llenar formulario con parámetros del Feature
        formulario.fillFormulario(
            nombre, 
            apellido, 
            direccion, 
            email, 
            telefono, 
            genero,
            idioma,
            pais,
            anio, 
            mes, 
            dia, 
            password, 
            confirmPassword
        );
    }
    @Entonces("todas las validaciones del formulario deben ser correctas")
    public void validarFormularioCompleto() {
        // Aserciones aquí
        // Agregar aserciones para cada campo es decir hacerlo por partes
        ;
    }
}
