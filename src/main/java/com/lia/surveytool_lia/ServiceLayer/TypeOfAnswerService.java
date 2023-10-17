package ServiceLayer;

import Model.TypeOfAnswer;
import Repositories.TypeOfAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeOfAnswerService {
  @Autowired
  private TypeOfAnswerRepository typeOfAnswerRepository;

  public TypeOfAnswer createType(TypeOfAnswer type){
    return typeOfAnswerRepository.save(type);
  }
}
