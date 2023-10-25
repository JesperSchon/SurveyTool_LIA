package com.lia.surveytool_lia.repositories;

import com.lia.surveytool_lia.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}