package com.lia.surveytool_lia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private BaseQuestion question;

  private String textAnswer;

  @ManyToMany
  @JoinTable(
      name = "answer_choice",
      joinColumns = @JoinColumn(name = "answer_id"),
      inverseJoinColumns = @JoinColumn(name = "choice_id"))
  private List<Choice> choiceAnswers;

  private Integer scaleAnswer;
}


