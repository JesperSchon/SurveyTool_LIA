package com.lia.surveytool_lia.ServiceLayer;

import com.lia.surveytool_lia.Model.BaseQuestion;
import com.lia.surveytool_lia.Model.ChoiceQuestion;
import com.lia.surveytool_lia.Model.ScaleQuestion;
import com.lia.surveytool_lia.Model.TextQuestion;
import com.lia.surveytool_lia.Repositories.BaseQuestionRepository;
import com.lia.surveytool_lia.Repositories.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

  private final BaseQuestionRepository baseQuestionRepository;
  private final SurveyRepository surveyRepository;

  public QuestionService(BaseQuestionRepository baseQuestionRepository, SurveyRepository surveyRepository) {
    this.baseQuestionRepository = baseQuestionRepository;
    this.surveyRepository = surveyRepository;
  }

  public BaseQuestion createQuestion(Long surveyId, BaseQuestion question) {
    return surveyRepository.findById(surveyId)
        .map(survey -> {
          question.setSurvey(survey);
          switch (question.getQuestionType()) {
            case "CHOICE":
              ChoiceQuestion choiceQuestion = new ChoiceQuestion();
              choiceQuestion.setQuestionText(question.getQuestionText());
              // Lägg till andra fält för ChoiceQuestion om det behövs
              return baseQuestionRepository.save(choiceQuestion);
            case "TEXT":
              TextQuestion textQuestion = new TextQuestion();
              textQuestion.setQuestionText(question.getQuestionText());
              // Lägg till andra fält för TextQuestion om det behövs
              return baseQuestionRepository.save(textQuestion);
            case "SCALE":
              ScaleQuestion scaleQuestion = new ScaleQuestion();
              scaleQuestion.setQuestionText(question.getQuestionText());
              // Lägg till andra fält för ScaleQuestion om det behövs
              return baseQuestionRepository.save(scaleQuestion);
            default:
              throw new IllegalArgumentException("Unknown question type");
          }
        })
        .orElseThrow(() -> new EntityNotFoundException("Survey not found with id " + surveyId));
  }



  public BaseQuestion updateQuestion(Long questionId, BaseQuestion updatedQuestion) {
    return baseQuestionRepository.findById(questionId)
        .map(question -> {
          question.setQuestionText(updatedQuestion.getQuestionText());
          // Uppdatera andra fält här om det behövs
          return baseQuestionRepository.save(question);
        })
        .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + questionId));
  }

  public List<BaseQuestion> getAllQuestionsBySurveyId(Long surveyId) {
    return baseQuestionRepository.findBySurveyId(surveyId);
  }

  public BaseQuestion getQuestionById(Long id) {
    return baseQuestionRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + id));
  }

  public void deleteQuestion(Long id) {
    baseQuestionRepository.deleteById(id);
  }
}

