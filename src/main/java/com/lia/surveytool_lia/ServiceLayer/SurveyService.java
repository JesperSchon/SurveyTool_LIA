package ServiceLayer;

import Model.Survey;
import Repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {
  @Autowired
  private SurveyRepository surveyRepository;

  public Survey createSurvey(Survey survey){
    return surveyRepository.save(survey);
  }

  public List<Survey> getAllSurveys() {
    return surveyRepository.findAll();
  }

  public Survey findSurveyById(Long surveyId) {
    return surveyRepository.findById(surveyId).orElse(null);
  }
}
