package com.lia.surveytool_lia.model;
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
