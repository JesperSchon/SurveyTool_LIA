package com.lia.surveytool_lia.ServiceLayer;

import com.lia.surveytool_lia.Model.Question;
import com.lia.surveytool_lia.Model.Survey;
import com.lia.surveytool_lia.Repositories.QuestionRepository;
import com.lia.surveytool_lia.Repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private SurveyRepository surveyRepository;

  public Question addQuestionToSurvey(Long surveyId, String questionText, Question.QuestionType type) {
    Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found!"));
    Question question = new Question();
    question.setQuestionText(questionText);
    question.setType(type);
    question.setSurvey(survey);
    return questionRepository.save(question);
  }

  public void deleteQuestion(Long questionId){
    questionRepository.deleteById(questionId);
  }
}
