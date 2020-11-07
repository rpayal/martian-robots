package com.co4gsl.martianrobots.universe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinatesTest {

    @Test
    public void xCoordinatesAreIncrementedForPositiveValue() {
        //Given
        Coordinates coordinate = new Coordinates(2,3);

        //When
        coordinate = coordinate.newCoordinatesFor(1, 0);

        //Then
        Assertions.assertEquals("3 3", coordinate.toString());
    }

    @Test
    public void xCoordinatesAreDecrementedForNegativeValue() {
        //Given
        Coordinates coordinate = new Coordinates(2,3);

        //When
        coordinate = coordinate.newCoordinatesFor(-1, 0);

        //Then
        Assertions.assertEquals("1 3", coordinate.toString());
    }

    @Test
    public void yCoordinatesAreIncrementedForPositiveValue() {
        //Given
        Coordinates coordinate = new Coordinates(2,3);

        //When
        coordinate = coordinate.newCoordinatesFor(0, 1);

        //Then
        Assertions.assertEquals("2 4", coordinate.toString());
    }

    @Test
    public void yCoordinatesAreDecrementedForNegativeValue() {
        //Given
        Coordinates coordinate = new Coordinates(2,3);

        //When
        coordinate = coordinate.newCoordinatesFor(0, -1);

        //Then
        Assertions.assertEquals("2 2", coordinate.toString());
    }

    @Test
    public void pointWithXCoordinateWithinBoundaryAreIdentified() {
        //Given
        Coordinates coordinate = new Coordinates(5,5);

        //When
        Coordinates internalPoint = new Coordinates(4,5);

        //Then
        Assertions.assertTrue(coordinate.hasWithinBounds(internalPoint));
    }

    @Test
    public void pointWithYCoordinateWithinBoundaryAreIdentified() {
        //Given
        Coordinates coordinate = new Coordinates(5,5);

        //When
        Coordinates internalPoint = new Coordinates(5,4);

        //Then
        Assertions.assertTrue(coordinate.hasWithinBounds(internalPoint));
    }

    @Test
    public void pointsWithXCoordinateOutsideBoundaryAreIdentified() {
        //Given
        Coordinates coordinate = new Coordinates(5,5);

        //When
        Coordinates externalPoint = new Coordinates(8,5);

        //Then
        Assertions.assertTrue(coordinate.hasOutsideBounds(externalPoint));
    }

    @Test
    public void pointsWithYCoordinateOutsideBoundaryAreIdentified() {
        //Given
        Coordinates coordinate = new Coordinates(5,5);

        //When
        Coordinates externalPoint = new Coordinates(5,8);

        //Then
        Assertions.assertTrue(coordinate.hasOutsideBounds(externalPoint));
    }
}