package Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long questionId;
  private String questionText;

  @ManyToOne
  @JoinColumn(name="type_id")
  private TypeOfAnswer type;

  @ManyToOne
  @JoinColumn(name="survey_id")
  private Survey survey;

  // getters, setters, constructors...
}


