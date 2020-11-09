package com.co4gsl.martianrobots.universe;

import java.util.ArrayList;
import java.util.List;

public class MarsLand {

    private Coordinates topRightCoordinates = new Coordinates(0, 0);
    private Coordinates bottomLeftCoordinates = new Coordinates(0, 0);

    private List<Coordinates> scentedCoordinates = new ArrayList<>();

    public MarsLand(final int topRightXCoordinate, final int topRightYCoordinate) {
        this.topRightCoordinates = this.topRightCoordinates.newCoordinatesFor(topRightXCoordinate, topRightYCoordinate);
    }

    public boolean hasWithinBounds(final Coordinates coordinates) {
        return this.bottomLeftCoordinates.hasOutsideBounds(coordinates) && this.topRightCoordinates.hasWithinBounds(coordinates);
    }

    public void dropScent(Coordinates coordinatesToSprayScent) {
        scentedCoordinates.add(coordinatesToSprayScent);
    }

    public List<Coordinates> getScentedCoordinates() {
        return scentedCoordinates;
    }
}
