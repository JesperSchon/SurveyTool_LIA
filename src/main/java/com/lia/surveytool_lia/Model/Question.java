package com.lia.surveytool_lia.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String questionText;

  @Enumerated(EnumType.STRING)
  private QuestionType type;

  @ManyToOne
  @JoinColumn(name = "survey_id")
  @JsonBackReference
  private Survey survey;

  public enum QuestionType {
    SINGLE_CHOICE,
    MULTIPLE_CHOICE,
    TEXT,
    CHECKBOX
  }
}


