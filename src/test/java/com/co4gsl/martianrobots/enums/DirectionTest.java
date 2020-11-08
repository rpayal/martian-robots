package com.co4gsl.martianrobots.enums;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.direction.EastDirection;
import com.co4gsl.martianrobots.direction.NorthDirection;
import com.co4gsl.martianrobots.direction.SouthDirection;
import com.co4gsl.martianrobots.direction.WestDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectionTest extends BaseTest {

    @Test
    void should() {
        assertTrue(Direction.valueOf("N").getDirection().getClass().isInstance(new NorthDirection()));
        assertTrue(Direction.valueOf("E").getDirection().getClass().isInstance(new EastDirection()));
        assertTrue(Direction.valueOf("S").getDirection().getClass().isInstance(new SouthDirection()));
        assertTrue(Direction.valueOf("W").getDirection().getClass().isInstance(new WestDirection()));
    }
}