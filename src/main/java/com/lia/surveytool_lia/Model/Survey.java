package Model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Survey {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long surveyId;
  private String title;
  private LocalDate createdDate;
  private LocalDate updateDate;

  @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
  private List<Question> questions = new ArrayList<>();


  @PrePersist
  protected void onCreate() {
    createdDate = LocalDate.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updateDate = LocalDate.now();
  }
}

