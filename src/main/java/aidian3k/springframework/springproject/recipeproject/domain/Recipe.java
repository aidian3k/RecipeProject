package aidian3k.springframework.springproject.recipeproject.domain;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.jetbrains.annotations.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Range(from = 1, to = Integer.MAX_VALUE)
    private Integer prepTime;

    @Range(from = 1, to = Integer.MAX_VALUE)
    private Integer cookTime;

    @Range(from = 1, to = Integer.MAX_VALUE)
    private Integer servings;

    @Lob
    @NotBlank
    @Size(min = 1, max = 255)
    private String source;

    @URL
    private String url;

    @Lob
    @NotBlank
    @Size(min = 1, max = 255)
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank
    @Size(min = 0, max = 300)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}