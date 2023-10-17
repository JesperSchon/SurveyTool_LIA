package Controller;

import Model.Question;
import Model.Survey;
import ServiceLayer.QuestionService;
import ServiceLayer.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  @Autowired
  private QuestionService questionService;
  @Autowired
  private SurveyService surveyService;


  @PostMapping("/add-to-survey/{surveyId}")
  public ResponseEntity<Question> addQuestionToSurvey(@PathVariable Long surveyId, @RequestBody Question question) {
    Survey survey = surveyService.findSurveyById(surveyId);
    if (survey == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    question.setSurvey(survey);
    Question addedQuestion = questionService.addQuestion(question);
    return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
  }


  @GetMapping
  public List<Question> getAllQuestions() {
    return questionService.getAllQuestions();
  }

  // ... Lägg till andra CRUD-operationer som behövs.
}

