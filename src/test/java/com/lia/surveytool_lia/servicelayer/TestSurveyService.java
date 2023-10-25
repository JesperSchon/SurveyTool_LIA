package com.lia.surveytool_lia.servicelayer;

import com.lia.surveytool_lia.interfaces.SurveyServiceIF;
import com.lia.surveytool_lia.model.Survey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestSurveyService {

    @BeforeEach
    public void init() {
        surveyService.deleteAllSurveys();
    }


    @Autowired
    private SurveyServiceIF surveyService;


    @Test
    public void test_Should_CreateSurvey_When_ProvidedSurvey() {
        // Skapa en ny enkät
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);
        assertNotNull(surveyDB.getId());
        assertEquals(survey.getId(), surveyDB.getId());
    }

    @Test
    public void test_Should_UpdateSurvey_When_ProvidedSurvey() {
        //Skapa en ny undersökning
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);
        assertEquals(survey.getTitle(), surveyDB.getTitle());
        assertEquals(survey.getId(), surveyDB.getId());

        //Uppdatera undersökningens egenskaper
        surveyDB.setTitle("Updated Test Survey");

        //Anropa updateSurvey för att spara ändringarna
        Survey updatedSurvey = surveyService.updateSurvey(surveyDB.getId(), surveyDB);

        //Kontrollera att undersökningen har uppdaterats korrekt i databasen
        assertEquals(surveyDB.getTitle(), updatedSurvey.getTitle());
        assertEquals(surveyDB.getId(), updatedSurvey.getId());
    }

    @Test
    public void test_Should_ReturnAllSurveys_When_Called() {
        //Skapa en ny undersökning
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);
        assertEquals(survey.getTitle(), surveyDB.getTitle());
        assertEquals(survey.getId(), surveyDB.getId());

        //Skapa en ny undersökning
        final Survey survey2 = new Survey();
        survey2.setTitle("Test Survey 2");
        Survey surveyDB2 = surveyService.createSurvey(survey2);
        assertEquals(survey2.getTitle(), surveyDB2.getTitle());
        assertEquals(survey2.getId(), surveyDB2.getId());

        //Hämta alla undersökningar
        assertEquals(2, surveyService.getAllSurveys().size());
    }

    @Test
    public void test_Should_ReturnSurvey_When_CalledById() {
        //Skapa en ny undersökning
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);
        assertEquals(survey.getTitle(), surveyDB.getTitle());
        assertEquals(survey.getId(), surveyDB.getId());

        //Hämta undersökningen med id 1
        Survey surveyDB2 = surveyService.getSurveyById(surveyDB.getId());
        assertEquals(surveyDB.getTitle(), surveyDB2.getTitle());
        assertEquals(surveyDB.getId(), surveyDB2.getId());
    }

    @Test
    public void test_Should_DeleteSurvey_When_CalledById() {
        //Skapa en ny undersökning
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);
        assertEquals(survey.getTitle(), surveyDB.getTitle());
        assertEquals(survey.getId(), surveyDB.getId());

        //Ta bort undersökningen med id 1
        surveyService.deleteSurvey(surveyDB.getId());

        //Kontrollera att undersökningen har tagits bort
        assertEquals(0, surveyService.getAllSurveys().size());
    }

    @Test
    public void test_ShouldDeleteAll_When_Called(){
        //Skapa en ny undersökning
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);
        assertEquals(survey.getTitle(), surveyDB.getTitle());
        assertEquals(survey.getId(), surveyDB.getId());

        //Skapa en ny undersökning
        final Survey survey2 = new Survey();
        survey2.setTitle("Test Survey 2");
        Survey surveyDB2 = surveyService.createSurvey(survey2);
        assertEquals(survey2.getTitle(), surveyDB2.getTitle());
        assertEquals(survey2.getId(), surveyDB2.getId());

        assertEquals(2, surveyService.getAllSurveys().size());

        //Ta bort alla undersökningar
        surveyService.deleteAllSurveys();

        //Kontrollera att alla undersökningar har tagits bort
        assertEquals(0, surveyService.getAllSurveys().size());
    }


}
