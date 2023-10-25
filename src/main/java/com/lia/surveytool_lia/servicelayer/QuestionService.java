package com.lia.surveytool_lia.servicelayer;

import com.lia.surveytool_lia.interfaces.QuestionServiceIF;
import com.lia.surveytool_lia.model.BaseQuestion;
import com.lia.surveytool_lia.model.ChoiceQuestion;
import com.lia.surveytool_lia.model.ScaleQuestion;
import com.lia.surveytool_lia.model.TextQuestion;
import com.lia.surveytool_lia.repositories.BaseQuestionRepository;
import com.lia.surveytool_lia.repositories.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements QuestionServiceIF {

  private final BaseQuestionRepository baseQuestionRepository;
  private final SurveyRepository surveyRepository;

  public QuestionService(BaseQuestionRepository baseQuestionRepository, SurveyRepository surveyRepository) {
    this.baseQuestionRepository = baseQuestionRepository;
    this.surveyRepository = surveyRepository;
  }

  @Override
  public BaseQuestion createQuestion(Long surveyId, BaseQuestion question) {
    return surveyRepository.findById(surveyId)
            .map(survey -> {
              question.setSurvey(survey);
              switch (question.getQuestionType()) {
                case "CHOICE":
                  ChoiceQuestion choiceQuestion = new ChoiceQuestion();
                  choiceQuestion.setQuestionText(question.getQuestionText());
                  choiceQuestion.setSurvey(survey); // Sätt survey här
                  // Lägg till andra fält för ChoiceQuestion om det behövs
                  return baseQuestionRepository.save(choiceQuestion);
                case "TEXT":
                  TextQuestion textQuestion = new TextQuestion();
                  textQuestion.setQuestionText(question.getQuestionText());
                  textQuestion.setSurvey(survey); // Sätt survey här
                  // Lägg till andra fält för TextQuestion om det behövs
                  return baseQuestionRepository.save(textQuestion);
                case "SCALE":
                  ScaleQuestion scaleQuestion = new ScaleQuestion();
                  scaleQuestion.setQuestionText(question.getQuestionText());
                  scaleQuestion.setSurvey(survey); // Sätt survey här
                  // Lägg till andra fält för ScaleQuestion om det behövs
                  return baseQuestionRepository.save(scaleQuestion);
                default:
                  throw new IllegalArgumentException("Unknown question type");
              }
            })
            .orElseThrow(() -> new EntityNotFoundException("Survey not found with id " + surveyId));
  }


  @Override
  public BaseQuestion updateQuestion(Long questionId, BaseQuestion updatedQuestion) {
    return baseQuestionRepository.findById(questionId)
        .map(question -> {
          question.setQuestionText(updatedQuestion.getQuestionText());
          return baseQuestionRepository.save(question);
        })
        .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + questionId));
  }

  @Override
  public List<BaseQuestion> getAllQuestionsBySurveyId(Long surveyId) {
    return baseQuestionRepository.findBySurveyId(surveyId);
  }

  @Override
  public BaseQuestion getQuestionById(Long id) {
    return baseQuestionRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + id));
  }

  @Override
  public void deleteQuestion(Long id) {
    baseQuestionRepository.deleteById(id);
  }
}

