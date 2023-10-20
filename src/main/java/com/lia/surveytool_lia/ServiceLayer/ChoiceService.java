package com.lia.surveytool_lia.ServiceLayer;

import com.lia.surveytool_lia.Model.BaseQuestion;
import com.lia.surveytool_lia.Model.Choice;
import com.lia.surveytool_lia.Model.ChoiceQuestion;
import com.lia.surveytool_lia.Repositories.BaseQuestionRepository;
import com.lia.surveytool_lia.Repositories.ChoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceService {

  private final ChoiceRepository choiceRepository;
  private final BaseQuestionRepository questionRepository;

  @Autowired
  public ChoiceService(ChoiceRepository choiceRepository, BaseQuestionRepository questionRepository) {
    this.choiceRepository = choiceRepository;
    this.questionRepository = questionRepository;
  }

  public Choice addChoice(Long questionId, String choiceText) {
    BaseQuestion question = questionRepository.findById(questionId)
        .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

    if (!(question instanceof ChoiceQuestion)) {
      throw new IllegalArgumentException("The question is not a choice question");
    }

    Choice choice = new Choice();
    choice.setText(choiceText);
    choice.setQuestion((ChoiceQuestion) question);

    return choiceRepository.save(choice);
  }
}
