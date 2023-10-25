package com.lia.surveytool_lia.controller;

import com.lia.surveytool_lia.model.Choice;
import com.lia.surveytool_lia.servicelayer.ChoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/choices")
@CrossOrigin(origins = "http://localhost:3000")
public class ChoiceController {

  private final ChoiceService choiceService;

  @Autowired
  public ChoiceController(ChoiceService choiceService) {
    this.choiceService = choiceService;
  }

  @PostMapping("/create/{questionId}")
  public ResponseEntity<Choice> createChoice(@PathVariable Long questionId, @RequestBody String choiceText) {
    try {
      Choice createdChoice = choiceService.addChoice(questionId, choiceText);
      return new ResponseEntity<>(createdChoice, HttpStatus.CREATED);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}

