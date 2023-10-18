package com.lia.surveytool_lia.Repositories;

import com.lia.surveytool_lia.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>{
}
