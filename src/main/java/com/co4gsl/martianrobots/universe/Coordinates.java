package com.co4gsl.martianrobots.universe;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;

    public Coordinates newCoordinatesFor(final int xCoordinateStepValue, final int yCoordinateStepValue) {
        return new Coordinates(this.xCoordinate + xCoordinateStepValue, this.yCoordinate + yCoordinateStepValue);
    }

    @Override
    public String toString() {
        return xCoordinate + " " + yCoordinate;
    }

    public boolean hasWithinBounds(final Coordinates coordinates) {
        return isXCoordinateWithinBounds(coordinates.xCoordinate) && isYCoordinateWithinBounds(coordinates.yCoordinate);
    }

    public boolean hasOutsideBounds(final Coordinates coordinates) {
        return isXCoordinateOutsideBounds(coordinates.xCoordinate) && isYCoordinateOutsideBounds(coordinates.yCoordinate);
    }

    private boolean isXCoordinateOutsideBounds(final int xCoordinate) {
        return xCoordinate >= this.xCoordinate;
    }

    private boolean isYCoordinateOutsideBounds(final int yCoordinate) {
        return yCoordinate >= this.yCoordinate;
    }

    private boolean isYCoordinateWithinBounds(final int yCoordinate) {
        return yCoordinate <= this.yCoordinate;
    }

    private boolean isXCoordinateWithinBounds(final int xCoordinate) {
        return xCoordinate <= this.xCoordinate;
    }
}
