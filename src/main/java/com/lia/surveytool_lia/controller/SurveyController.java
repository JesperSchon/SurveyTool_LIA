package com.lia.surveytool_lia.controller;

import com.lia.surveytool_lia.model.Survey;
import com.lia.surveytool_lia.servicelayer.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "http://localhost:3000")
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
  public ResponseEntity<Survey> getSurvey(@PathVariable Long id) {
    try {
      Survey survey = surveyService.getSurveyWithQuestions(id);
      return ResponseEntity.ok(survey);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
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


