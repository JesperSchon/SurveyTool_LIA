package com.lia.surveytool_lia.Repositories;

import com.lia.surveytool_lia.Model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}