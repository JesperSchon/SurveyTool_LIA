package com.lia.surveytool_lia.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "questionType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseQuestion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String questionText;

  @Column(name = "questionType", insertable = false, updatable = false)
  private String questionType;

  @ManyToOne
  @JoinColumn(name = "survey_id")
  @JsonBackReference
  private Survey survey;
}


