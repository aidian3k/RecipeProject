package aidian3k.springframework.springproject.recipeproject.converters;

import aidian3k.springframework.springproject.recipeproject.commands.NotesCommand;
import aidian3k.springframework.springproject.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Nullable
    @Synchronized
    @Override
    public Notes convert(NotesCommand source) {

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNote());
        return notes;
    }
}
