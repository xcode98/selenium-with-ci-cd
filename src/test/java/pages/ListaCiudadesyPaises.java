package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ListaCiudadesyPaises extends BasePage{
    
    //public String inputBusqueda = "//INPUT[@id='LocationSearch_input']";
    //public String listaResultados = "//div[@id='LocationSearch_listbox']";
    
    private final By inputBusqueda = By.id("headerSearch_LocationSearch_input");
    private final By listaResultados = By.id("headerSearch_LocationSearch_listbox");
    // Localizadores para el banner de cookies de weather.com
    private final By cookieIframe = By.id("sp_message_iframe_1233284");
    private final By acceptAllButton = By.xpath("//button[contains(text(), 'Aceptar todo')]");



    public void navegarPagina() throws InterruptedException {
        navigateTo("https://weather.com/es-PE/tiempo/hoy/l/PEXX0011:1:PE?Goto=Redirected");
        
        // Aceptar cookies
        aceptarCookies();
    }
    
    public void aceptarCookies() throws InterruptedException {
        try {
            switchToIframe(cookieIframe);
            clickElement(acceptAllButton);
        }finally {
            switchToDefaultContent();
        }
    }
    
    public void realizarBusqueda(String city) throws InterruptedException{
        Thread.sleep(5000);
        sendKeys(inputBusqueda, city);
        
        // Esperar más tiempo para que aparezcan los resultados (segunda iteración)
        Thread.sleep(5000);
    }

    public List<String> getAllSearchResults(){ 
        return bringMeAllElements(listaResultados)
            .stream()
            .map(WebElement::getText) 
            .peek(result -> System.out.println("- " + result)) 
            .collect(Collectors.toList()); 
    }
    

    public String getFoundTextInSearchResults(String searchText) {
        List<String> searchResults = getAllSearchResults();
        
        for (String result : searchResults) {
            if (result.contains(searchText)) {
                System.out.println("✅ ENCONTRADO: '" + searchText + "' en: " + result);
                return searchText;
            }
        }
        
        System.out.println("❌ NO ENCONTRADO: '" + searchText + "'");
        return null;
    }
}
