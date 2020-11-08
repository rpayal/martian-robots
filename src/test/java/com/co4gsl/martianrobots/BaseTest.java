package com.co4gsl.martianrobots;

import com.co4gsl.martianrobots.direction.IDirection;
import com.co4gsl.martianrobots.robot.Robot;
import com.co4gsl.martianrobots.universe.Coordinates;
import com.co4gsl.martianrobots.universe.MarsLand;

public abstract class BaseTest {

    protected final MarsLand marsLand = new MarsLand(5, 3);
    protected IDirection direction;
    protected Coordinates coordinates = new Coordinates(2, 2);
    protected Robot robot;
}
