package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.robot.Robot;
import com.co4gsl.martianrobots.universe.Coordinates;

public class WestDirection implements IDirection {

    @Override
    public IDirection spinRight() {
        return new NorthDirection();
    }

    @Override
    public IDirection spinLeft() {
        return new SouthDirection();
    }

    @Override
    public void moveForward(Robot robot) {
        Coordinates currentCoordinate = robot.getCurrentCoordinates();
        currentCoordinate.setXCoordinate(currentCoordinate.getXCoordinate() - 1);
        robot.setCurrentCoordinates(currentCoordinate);
    }

    @Override
    public void moveBackward(Robot robot) {
        Coordinates currentCoordinate = robot.getCurrentCoordinates();
        currentCoordinate.setXCoordinate(currentCoordinate.getXCoordinate() + 1);
        robot.setCurrentCoordinates(currentCoordinate);
    }
}
