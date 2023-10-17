package Controller;

import Model.Survey;
import ServiceLayer.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("surveys")
public class SurveyController {

  @Autowired
  private SurveyService surveyService;

  @PostMapping("/create")
  public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
    Survey createdSurvey = surveyService.createSurvey(survey);
    return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
  }

  @GetMapping
  public List<Survey> getAllSurveys() {
    return surveyService.getAllSurveys();
  }

  @GetMapping("/{surveyId}")
  public ResponseEntity<Survey> getSurveyById(@PathVariable Long surveyId) {
    Survey survey = surveyService.findSurveyById(surveyId);
    if (survey == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(survey, HttpStatus.OK);
  }

  // ... Lägg till andra CRUD-operationer som behövs.
}

