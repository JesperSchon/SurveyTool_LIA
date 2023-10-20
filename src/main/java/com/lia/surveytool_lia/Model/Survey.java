package com.lia.surveytool_lia.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Survey {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;


  @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<BaseQuestion> questions = new ArrayList<>();
}
