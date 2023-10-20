package com.lia.surveytool_lia.Controller;

import com.lia.surveytool_lia.Model.BaseQuestion;
import com.lia.surveytool_lia.Model.ChoiceQuestion;
import com.lia.surveytool_lia.Model.ScaleQuestion;
import com.lia.surveytool_lia.Model.TextQuestion;
import com.lia.surveytool_lia.ServiceLayer.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

  @RestController
  @RequestMapping("/api/questions")
  public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
      this.questionService = questionService;
    }

    @PostMapping("/create/choice/{surveyId}")
    public ResponseEntity<BaseQuestion> createChoiceQuestion(@PathVariable Long surveyId, @RequestBody ChoiceQuestion question) {
      BaseQuestion createdQuestion = questionService.createQuestion(surveyId, question);
      return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PostMapping("/create/scale/{surveyId}")
    public ResponseEntity<BaseQuestion> createScaleQuestion(@PathVariable Long surveyId, @RequestBody ScaleQuestion question) {
      BaseQuestion createdQuestion = questionService.createQuestion(surveyId, question);
      return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PostMapping("/create/text/{surveyId}")
    public ResponseEntity<BaseQuestion> createTextQuestion(@PathVariable Long surveyId, @RequestBody TextQuestion question) {
      BaseQuestion createdQuestion = questionService.createQuestion(surveyId, question);
      return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

  @PutMapping("/update/{id}")
  public ResponseEntity<BaseQuestion> updateQuestion(@PathVariable Long id, @RequestBody BaseQuestion question) {
    try {
      BaseQuestion updatedQuestion = questionService.updateQuestion(id, question);
      return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<BaseQuestion> getQuestion(@PathVariable Long id) {
    try {
      BaseQuestion question = questionService.getQuestionById(id);
      return new ResponseEntity<>(question, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<BaseQuestion>> getAllQuestionsBySurveyId(@RequestParam Long surveyId) {
    List<BaseQuestion> questions = questionService.getAllQuestionsBySurveyId(surveyId);
    return new ResponseEntity<>(questions, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    try {
      questionService.deleteQuestion(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}


