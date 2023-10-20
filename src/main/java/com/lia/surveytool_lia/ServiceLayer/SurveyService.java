package com.lia.surveytool_lia.ServiceLayer;

import com.lia.surveytool_lia.Model.Survey;
import com.lia.surveytool_lia.Repositories.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SurveyService {

  private final SurveyRepository surveyRepository;

  public SurveyService(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  public Survey createSurvey(Survey survey) {
    return surveyRepository.save(survey);
  }

  public Survey updateSurvey(Long id, Survey updatedSurvey) {
    return surveyRepository.findById(id)
        .map(survey -> {
          survey.setTitle(updatedSurvey.getTitle());
          return surveyRepository.save(survey);
        })
        .orElseThrow(() -> new EntityNotFoundException("Survey not found with id " + id));
  }

  public List<Survey> getAllSurveys() {
    return surveyRepository.findAll();
  }

  public Survey getSurveyById(Long id) {
    return surveyRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Survey not found with id " + id));
  }

  public void deleteSurvey(Long id) {
    surveyRepository.deleteById(id);
  }
}

