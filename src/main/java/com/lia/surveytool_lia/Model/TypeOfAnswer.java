package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TypeOfAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long typeId;
  private String typeDescription;  // ex. "Multiple Choice", "Text", "Checkbox"

  // getters, setters, constructors...
}

