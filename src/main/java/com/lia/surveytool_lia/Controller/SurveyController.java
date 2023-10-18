package com.lia.surveytool_lia.Controller;

import com.lia.surveytool_lia.Model.Survey;
import com.lia.surveytool_lia.ServiceLayer.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

  @Autowired
  private SurveyService surveyService;


  @PostMapping
  public ResponseEntity<Survey> createSurvey(@RequestBody String title) {
    Survey createdSurvey = surveyService.createSurvey(title);
    return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
  }

  @GetMapping("/getAllSurveys")
  public ResponseEntity<List<Survey>> getAllSurveys() {
    List<Survey> surveys = surveyService.getAllSurveys();
    return new ResponseEntity<>(surveys, HttpStatus.OK);
  }
}

