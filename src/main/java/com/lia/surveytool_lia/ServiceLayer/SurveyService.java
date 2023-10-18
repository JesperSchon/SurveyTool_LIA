package com.lia.surveytool_lia.ServiceLayer;

import com.lia.surveytool_lia.Model.Survey;
import com.lia.surveytool_lia.Repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SurveyService {
  @Autowired
  private SurveyRepository surveyRepository;


  public Survey createSurvey(String title) {
    Survey survey = new Survey();
    survey.setTitle(title);
    return surveyRepository.save(survey);
  }

  public List<Survey> getAllSurveys() {
    return surveyRepository.findAll();
  }

}
