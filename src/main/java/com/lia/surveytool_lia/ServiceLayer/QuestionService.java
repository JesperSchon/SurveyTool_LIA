package ServiceLayer;

import Model.Question;
import Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
  @Autowired
  private QuestionRepository questionRepository;

  public Question addQuestion(Question question){
    return questionRepository.save(question);
  }

  public List<Question> getAllQuestions() {
    return questionRepository.findAll();
  }
}
