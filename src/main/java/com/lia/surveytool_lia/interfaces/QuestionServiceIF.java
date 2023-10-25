package com.lia.surveytool_lia.interfaces;

import com.lia.surveytool_lia.model.BaseQuestion;

import java.util.List;

public interface QuestionServiceIF {
    BaseQuestion createQuestion(Long surveyId, BaseQuestion question);

    BaseQuestion updateQuestion(Long questionId, BaseQuestion updatedQuestion);

    List<BaseQuestion> getAllQuestionsBySurveyId(Long surveyId);

    BaseQuestion getQuestionById(Long id);

    void deleteQuestion(Long id);
}
