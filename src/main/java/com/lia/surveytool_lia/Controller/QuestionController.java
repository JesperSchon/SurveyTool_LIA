package com.lia.surveytool_lia.Controller;

import com.lia.surveytool_lia.Model.Question;
import com.lia.surveytool_lia.ServiceLayer.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys/{surveyId}/questions")
public class QuestionController {

  @Autowired
  private QuestionService questionService;

  @PostMapping
  public ResponseEntity<Question> addQuestionToSurvey(@PathVariable Long surveyId, @RequestBody Question question) {
    Question addedQuestion = questionService.addQuestionToSurvey(surveyId, question.getQuestionText(), question.getType());
    return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
  }
}

