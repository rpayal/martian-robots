package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.robot.Robot;

public interface IDirection {

    IDirection spinRight();

    IDirection spinLeft();

    void moveForward(Robot robot);

    void moveBackward(Robot robot);
}
