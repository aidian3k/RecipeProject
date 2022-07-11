package aidian3k.springframework.springproject.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue ( strategy =  GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ingredient ingredient;

    @Lob
    private String description;
}
