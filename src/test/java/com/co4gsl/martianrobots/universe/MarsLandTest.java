package com.co4gsl.martianrobots.universe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarsLandTest {
    private MarsLand marsland;

    @BeforeEach
    void setup() {
        //Given
        marsland = new MarsLand(5, 3);
    }

    @Test
    public void coordinatePositionWithinBoundsIsIdentified() {
        //When
        Coordinates coordinates = new Coordinates(5, 0);

        //Then
        Assertions.assertTrue(marsland.hasWithinBounds(coordinates));
    }

    @Test
    public void coordinatePositionXCoordinateOutsideBoundsIsIdentified() {
        //When
        Coordinates coordinates = new Coordinates(6, 0);

        //Then
        Assertions.assertFalse(marsland.hasWithinBounds(coordinates));
    }

    @Test
    public void coordinatePositionNegativeXCoordinateOutsideBoundsIsIdentified() {
        //When
        Coordinates coordinates = new Coordinates(-1, 0);

        //Then
        Assertions.assertFalse(marsland.hasWithinBounds(coordinates));
    }

    @Test
    public void coordinatePositionYCoordinateOutsideBoundsIsIdentified() {
        //When
        Coordinates coordinates = new Coordinates(0, 6);

        //Then
        Assertions.assertFalse(marsland.hasWithinBounds(coordinates));
    }

    @Test
    public void coordinatePositionNegativeYCoordinateOutsideBoundsIsIdentified() {
        //When
        Coordinates coordinates = new Coordinates(0, -1);

        //Then
        Assertions.assertFalse(marsland.hasWithinBounds(coordinates));
    }
}