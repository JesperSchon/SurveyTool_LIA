package com.lia.surveytool_lia.Controller;

import com.lia.surveytool_lia.Model.Survey;
import com.lia.surveytool_lia.ServiceLayer.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @PostMapping("/create")
  public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
    Survey createdSurvey = surveyService.createSurvey(survey);
    return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Survey>> getAllSurveys() {
    List<Survey> surveys = surveyService.getAllSurveys();
    return ResponseEntity.ok(surveys);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
    Survey survey = surveyService.getSurveyById(id);
    return ResponseEntity.ok(survey);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey updatedSurvey) {
    Survey survey = surveyService.updateSurvey(id, updatedSurvey);
    return ResponseEntity.ok(survey);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
    surveyService.deleteSurvey(id);
    return ResponseEntity.noContent().build();
  }
}


