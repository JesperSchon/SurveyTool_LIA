package com.lia.surveytool_lia.servicelayer;

import com.lia.surveytool_lia.interfaces.QuestionServiceIF;
import com.lia.surveytool_lia.interfaces.SurveyServiceIF;
import com.lia.surveytool_lia.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestQuestionService {

    public static final String WHAT_IS_YOUR_FAVORITE_COLOR = "What is your favorite color?";
    public static final String WHAT_IS_YOUR_FAVORITE_COLOR_REALLY = "What is your favorite color, really?";
    public static final String WHAT_IS_YOUR_FAVORITE_ANIMAL = "What is your favorite animal?";
    @Autowired
    private QuestionServiceIF questionService;

    @Autowired
    private SurveyServiceIF surveyService;

    @Test
    public void test_When_CreateChoiceQuestion_Should_CreateAndLinkQuestionToSurvey() {
        // Skapa en enkät
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey createdSurvey = surveyService.createSurvey(survey);

        // Skapa en fråga
        ChoiceQuestion question = new ChoiceQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("CHOICE");

        // Anropa metoden som ska testas
        BaseQuestion createdQuestion = questionService.createQuestion(createdSurvey.getId(), question);

        // Kontrollera att frågan har skapats och är kopplad till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(createdSurvey.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof ChoiceQuestion);
    }

    @Test
    public void test_WhenCreateTextQuestion_ShouldCreateAndLinkTextQuestionToSurvey() {
        // Skapa en enkät
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey createdSurvey = surveyService.createSurvey(survey);

        // Skapa en fråga
        TextQuestion question = new TextQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("TEXT");

        // Anropa metoden som ska testas
        BaseQuestion createdQuestion = questionService.createQuestion(createdSurvey.getId(), question);

        // Kontrollera att frågan har skapats och är kopplad till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(createdSurvey.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof TextQuestion);
    }

    @Test
    public void test_WhenCreateScaleQuestion_ShouldCreateAndLinkScaleQuestionToSurvey() {
        // Skapa en enkät
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey createdSurvey = surveyService.createSurvey(survey);

        // Skapa en fråga
        ScaleQuestion question = new ScaleQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("SCALE");

        // Anropa metoden som ska testas
        BaseQuestion createdQuestion = questionService.createQuestion(createdSurvey.getId(), question);

        // Kontrollera att frågan har skapats och är kopplad till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(createdSurvey.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof ScaleQuestion);
    }

    @Test
    public void test_WhenUpdateQuestion_ShouldUpdateQuestion() {
        // Skapa en enkät
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey createdSurvey = surveyService.createSurvey(survey);

        // Skapa en fråga
        ChoiceQuestion question = new ChoiceQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("CHOICE");

        // Anropa metoden som ska testas
        BaseQuestion createdQuestion = questionService.createQuestion(createdSurvey.getId(), question);

        // Kontrollera att frågan har skapats och är kopplad till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(createdSurvey.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof ChoiceQuestion);

        // Uppdatera frågan
        createdQuestion.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR_REALLY);

        // Anropa metoden som ska testas
        BaseQuestion updatedQuestion = questionService.updateQuestion(createdQuestion.getId(), createdQuestion);

        // Kontrollera att frågan har uppdaterats
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR_REALLY, updatedQuestion.getQuestionText());
        assertEquals(createdQuestion.getId(), updatedQuestion.getId()); // Kontrollera att id inte har ändrats
    }

    @Test
    public void test_WhenDeleteQuestionFromSurvey_ShouldDeleteQuestionFromSurvey(){
        // Skapa en enkät
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey createdSurvey = surveyService.createSurvey(survey);

        // Skapa en fråga
        ChoiceQuestion question = new ChoiceQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("CHOICE");

        BaseQuestion createdQuestion = questionService.createQuestion(createdSurvey.getId(), question);

        // Kontrollera att frågan har skapats och är kopplad till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(createdSurvey.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof ChoiceQuestion);

        // Ta bort frågan från enkäten
        questionService.deleteQuestion(createdQuestion.getId());

        // Kontrollera att frågan har tagits bort från enkäten
        assertEquals(0, questionService.getAllQuestionsBySurveyId(createdSurvey.getId()).size());
    }

    @Test
    public void test_WhenDeleteSurvey_ShouldDeleteAllQuestions(){
        //Skapa en enkät
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);

        //Skapa en fråga
        ChoiceQuestion question = new ChoiceQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("CHOICE");

        BaseQuestion createdQuestion = questionService.createQuestion(surveyDB.getId(), question);

        //Kontrollera att frågan har skapats och är kopplad till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(surveyDB.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof ChoiceQuestion);

        //Ta bort enkäten
        surveyService.deleteSurvey(surveyDB.getId());

        //Kontrollera att frågan har tagits bort
        assertEquals(0, questionService.getAllQuestionsBySurveyId(surveyDB.getId()).size());
    }

    @Test
    public void test_WhenGetAllQuestionBySurveyId_ShouldReturnAllQuestionsFromSurvey(){
        //Skapa en enkät
        final Survey survey = new Survey();
        survey.setTitle("Test Survey");
        Survey surveyDB = surveyService.createSurvey(survey);

        //Skapa två frågor
        ChoiceQuestion question = new ChoiceQuestion();
        question.setQuestionText(WHAT_IS_YOUR_FAVORITE_COLOR);
        question.setQuestionType("CHOICE");

        ChoiceQuestion question2 = new ChoiceQuestion();
        question2.setQuestionText(WHAT_IS_YOUR_FAVORITE_ANIMAL);
        question2.setQuestionType("CHOICE");

        //Lägg till frågorna i enkäten
        BaseQuestion createdQuestion = questionService.createQuestion(surveyDB.getId(), question);
        BaseQuestion createdQuestion2 = questionService.createQuestion(surveyDB.getId(), question2);

        //Kontrollera att frågorna har skapats och är kopplade till enkäten
        assertNotNull(createdQuestion.getId());
        assertEquals(surveyDB.getId(), createdQuestion.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_COLOR, createdQuestion.getQuestionText());
        assertTrue(createdQuestion instanceof ChoiceQuestion);

        assertNotNull(createdQuestion2.getId());
        assertEquals(surveyDB.getId(), createdQuestion2.getSurvey().getId());
        assertEquals(WHAT_IS_YOUR_FAVORITE_ANIMAL, createdQuestion2.getQuestionText());
        assertTrue(createdQuestion2 instanceof ChoiceQuestion);

        //Hämta alla frågor från enkäten
        assertEquals(2, questionService.getAllQuestionsBySurveyId(surveyDB.getId()).size());
    }

}
