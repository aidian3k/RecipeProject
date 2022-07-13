package aidian3k.springframework.springproject.recipeproject.converters;

import aidian3k.springframework.springproject.recipeproject.commands.CategoryCommand;
import aidian3k.springframework.springproject.recipeproject.domain.Category;
import lombok.Synchronized;
import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null){
            return null;
        }

        val CategoryCommand = new CategoryCommand();
        CategoryCommand.setId(source.getId());
        CategoryCommand.setDescription(source.getDescription());
        return CategoryCommand;
    }
}
