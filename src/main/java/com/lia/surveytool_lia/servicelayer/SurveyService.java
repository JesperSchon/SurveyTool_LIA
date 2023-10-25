package com.lia.surveytool_lia.servicelayer;

import com.lia.surveytool_lia.interfaces.SurveyServiceIF;
import com.lia.surveytool_lia.model.BaseQuestion;
import com.lia.surveytool_lia.model.Survey;
import com.lia.surveytool_lia.repositories.BaseQuestionRepository;
import com.lia.surveytool_lia.repositories.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SurveyService implements SurveyServiceIF {

  private final SurveyRepository surveyRepository;
  @Autowired
  private BaseQuestionRepository baseQuestionRepository;

  public SurveyService(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }
  @Override
  @Transactional
  public Survey createSurvey(Survey survey) {
    return surveyRepository.save(survey);
  }

  @Override
  public Survey updateSurvey(Long id, Survey updatedSurvey) {
    return surveyRepository.findById(id)
        .map(survey -> {
          survey.setTitle(updatedSurvey.getTitle());
          return surveyRepository.save(survey);
        })
        .orElseThrow(() -> new EntityNotFoundException("Survey not found with id " + id));
  }

  @Override
  public List<Survey> getAllSurveys() {
    return surveyRepository.findAll();
  }

  @Override
  public Survey getSurveyById(Long id) {
    return surveyRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Survey not found with id " + id));
  }

  @Override
  public void deleteSurvey(Long id) {
    surveyRepository.deleteById(id);
  }

  @Override
  public void deleteAllSurveys() {
    surveyRepository.deleteAll();
  }

  public Survey getSurveyWithQuestions(Long surveyId) {
    Optional<Survey> survey = surveyRepository.findById(surveyId);
    if (survey.isPresent()) {
      // Anropa metoden från repository som hämtar frågorna för enkäten
      List<BaseQuestion> questions = baseQuestionRepository.findBySurveyId(surveyId);
      survey.get().setQuestions(questions);
      return survey.get();
    }
    // Hantera situationen då enkäten inte finns
    throw new NoSuchElementException("Ingen enkät med ID: " + surveyId);
  }
}

