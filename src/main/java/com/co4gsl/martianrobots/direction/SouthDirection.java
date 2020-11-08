package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.robot.Robot;
import com.co4gsl.martianrobots.universe.Coordinates;

public class SouthDirection implements IDirection {

    @Override
    public IDirection spinRight() {
        return new WestDirection();
    }

    @Override
    public IDirection spinLeft() {
        return new EastDirection();
    }

    @Override
    public void moveForward(Robot robot) {
        Coordinates currentCoordinate = robot.getCurrentCoordinates();
        currentCoordinate.setYCoordinate(currentCoordinate.getYCoordinate() - 1);
        robot.setCurrentCoordinates(currentCoordinate);
    }

    @Override
    public void moveBackward(Robot robot) {
        Coordinates currentCoordinate = robot.getCurrentCoordinates();
        currentCoordinate.setYCoordinate(currentCoordinate.getYCoordinate() + 1);
        robot.setCurrentCoordinates(currentCoordinate);
    }
}
