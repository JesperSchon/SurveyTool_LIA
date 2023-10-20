package com.lia.surveytool_lia.Model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("TEXT")
@Data
@EqualsAndHashCode(callSuper = false)
public class TextQuestion extends BaseQuestion {
}
