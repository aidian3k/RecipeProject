package aidian3k.springframework.springproject.recipeproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        //given
        Long expected = 4L;

        //when
        category.setId(4L);

        //then
        assertEquals(expected, category.getId());
    }

    @Test
    void getDescription() {
        //given
        String expected = "JunitTest";

        //when
        category.setDescription("JunitTest");

        //then
        assertEquals(expected, category.getDescription());
    }
}