package aidian3k.springframework.springproject.recipeproject.converters;

import aidian3k.springframework.springproject.recipeproject.commands.NotesCommand;
import aidian3k.springframework.springproject.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Nullable
    @Synchronized
    @Override
    public NotesCommand convert(Notes source) {
        if(source == null){
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setRecipeNote(source.getRecipeNotes());
        notesCommand.setId(source.getId());
        return notesCommand;
    }
}
