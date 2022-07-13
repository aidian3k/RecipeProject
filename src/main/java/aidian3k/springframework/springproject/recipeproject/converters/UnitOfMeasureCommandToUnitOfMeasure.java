package aidian3k.springframework.springproject.recipeproject.converters;

import aidian3k.springframework.springproject.recipeproject.commands.UnitOfMeasureCommand;
import aidian3k.springframework.springproject.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    @Synchronized
    @Nullable
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source == null){
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(source.getDescription());
        unitOfMeasure.setId(source.getId());
        return unitOfMeasure;
    }
}
