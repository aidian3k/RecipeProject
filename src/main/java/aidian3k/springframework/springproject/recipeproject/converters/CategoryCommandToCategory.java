package aidian3k.springframework.springproject.recipeproject.converters;

import aidian3k.springframework.springproject.recipeproject.commands.CategoryCommand;
import aidian3k.springframework.springproject.recipeproject.domain.Category;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert( @NonNull CategoryCommand source) {

        if(source == null){
            return null;
        }

        val category = new Category();

        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}
