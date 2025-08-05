package steps;

import static org.assertj.core.api.Assertions.assertThat;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;

import pages.BasePage;
import pages.AmazonPage;

public class AmazonSteps {
    
    private AmazonPage amazonPage;
    
    @Before
    public void setUp() {
        BasePage.initializeDriver();
        amazonPage = new AmazonPage();
    }
    
    @After
    public void tearDown() {
        //BasePage.quitDriver();
    }
    
    @Dado("visito amazon")
    public void visitoAmazon() {
        amazonPage.visitAmazon();
        String selectedValue = amazonPage.selectQualityAssuranceForm();
        assertThat(selectedValue).isEqualTo("Emergency");


        
        amazonPage.fillCCAName("John", "Doe");
        amazonPage.fillQAName("Jane", "Phillips");
        amazonPage.fillDateOfCall("12-12-2004");   
        amazonPage.fillTimeOfCall("11", "05");
        amazonPage.uploadFile("/Users/francis-pc/Downloads/F42-01-day.jpg");
    }
}
