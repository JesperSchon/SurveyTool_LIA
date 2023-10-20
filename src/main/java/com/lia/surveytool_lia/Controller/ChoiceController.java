package com.lia.surveytool_lia.Controller;

import com.lia.surveytool_lia.Model.Choice;
import com.lia.surveytool_lia.ServiceLayer.ChoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/choices")
public class ChoiceController {

  private final ChoiceService choiceService;

  @Autowired
  public ChoiceController(ChoiceService choiceService) {
    this.choiceService = choiceService;
  }

  @PostMapping("/create")
  public ResponseEntity<Choice> createChoice(@RequestParam Long questionId, @RequestParam String choiceText) {
    try {
      Choice createdChoice = choiceService.addChoice(questionId, choiceText);
      return new ResponseEntity<>(createdChoice, HttpStatus.CREATED);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  // Här kan du lägga till fler metoder för att uppdatera, ta bort och hämta val om det behövs.
}

