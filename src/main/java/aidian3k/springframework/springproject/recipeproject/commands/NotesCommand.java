package aidian3k.springframework.springproject.recipeproject.commands;

import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
    private String recipeNote;
}
