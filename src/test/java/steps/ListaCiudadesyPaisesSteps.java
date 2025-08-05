package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ListaCiudadesyPaises;

public class ListaCiudadesyPaisesSteps {
    ListaCiudadesyPaises ls = new ListaCiudadesyPaises();

    @Given("^I navigate to the list page$")
    public void navegarListPage() throws InterruptedException{
        ls.navegarPagina();
    }

    @When("^I search (.+) in the list$")
    public void escriboInputBusqueda(String city) throws InterruptedException {
        ls.realizarBusqueda(city);
    }

    /* 
    @Then("^I can find the text in the list$")
    public void ResultadosDeMiBusquedaEnLista() throws InterruptedException {
        List<String> lista = ls.getAllSearchResults();
        boolean textIsThere = lista.contains("Lima");
        if(textIsThere){
            System.out.println("Tu busqueda se encuentra en la lista");
        }else{
            throw new Error("Tu busqueda no se encuentra en la lista");
        }
    }*/

    /* 
    @Then("^I can find the text in the list$")
    public void ResultadosDeMiBusquedaEnLista()throws InterruptedException{
    
    List<String> lista = ls.getAllSearchResults2();
    boolean textIsThere = lista.contains("Lima,");
    if(textIsThere){
        System.out.println("Tu búsqueda se encuentra en la lista");
    } else{
        throw new AssertionError("Tu búsqueda no se encuentra en la lista");
    }
}*/

@Then("^I can find (.+) in the list$")
public void ResultadosDeMiBusquedaEnLista(String state) throws InterruptedException {
    String foundText = ls.getFoundTextInSearchResults(state);
    assertThat(foundText).isEqualTo(state);
}

}
