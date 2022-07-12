package aidian3k.springframework.springproject.recipeproject.repositories;

import aidian3k.springframework.springproject.recipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescriptionTeaSpoon() {
        //given
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        //when
        String expectedDescription = "Teaspoon";

        //then
        assertEquals(expectedDescription, unitOfMeasureOptional.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        //given
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        //when
        String expectedDescription = "Cup";

        //then
        assertEquals(expectedDescription, unitOfMeasureOptional.get().getDescription());
    }
}