package com.lia.surveytool_lia.interfaces;

import com.lia.surveytool_lia.model.Survey;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SurveyServiceIF {
    @Transactional
    Survey createSurvey(Survey survey);

    Survey updateSurvey(Long id, Survey updatedSurvey);

    List<Survey> getAllSurveys();

    Survey getSurveyById(Long id);

    void deleteSurvey(Long id);

    void deleteAllSurveys();
}
