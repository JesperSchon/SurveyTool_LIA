package com.lia.surveytool_lia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@DiscriminatorValue("CHOICE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChoiceQuestion extends BaseQuestion {
  private boolean isMultipleChoice = false;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Choice> choices;
}

