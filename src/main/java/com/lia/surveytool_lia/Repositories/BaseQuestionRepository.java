package com.lia.surveytool_lia.Repositories;

import com.lia.surveytool_lia.Model.BaseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseQuestionRepository extends JpaRepository<BaseQuestion, Long> {
  List<BaseQuestion> findBySurveyId(Long surveyId);

}
