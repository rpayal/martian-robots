package com.co4gsl.martianrobots.robot;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.direction.EastDirection;
import com.co4gsl.martianrobots.direction.NorthDirection;
import com.co4gsl.martianrobots.direction.WestDirection;
import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.universe.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new NorthDirection();
        robot = new Robot(marsLand, coordinates, direction);
    }

    @Test
    public void whenRequestedRobotSpinRight() throws Exception {
        robot.spinRight();
        assertEquals(EastDirection.class, robot.getCurrentDirection().getClass());
    }

    @Test
    public void whenRequestedRobotSpinLeft() throws Exception {
        robot.spinLeft();
        assertEquals(WestDirection.class, robot.getCurrentDirection().getClass());
    }

    @Test
    public void whenRequestedRobotMoveForward() throws Exception {
        int expected = robot.getCurrentCoordinates().getYCoordinate() + 1;
        robot.moveForward();
        assertEquals(expected, robot.getCurrentCoordinates().getYCoordinate());
    }

    @Test
    public void whenRequestedRobotCurrentPosition() throws Exception {
        assertEquals("2 2 N", robot.currentPosition());

        /*
            1 1 E
            RFRFRFRF
            Output : 1 1 E
         */
        Coordinates coordinates = new Coordinates(1, 1);
        robot = new Robot(marsLand, coordinates, new EastDirection());
        try {
            robot.spinRight();
            robot.moveForward();
            robot.spinRight();
            robot.moveForward();
            robot.spinRight();
            robot.moveForward();
            robot.spinRight();
            robot.moveForward();
        } catch (RobotOutOfBoundsException e) {
        }
        assertEquals("1 1 E", robot.currentPosition());
    }

    @Test
    public void whenRequestedRobotCurrentPositionWithStatusLOST() throws Exception {
        /*
            3 2 N
            FRRFLLFFRRFLL
            Output : 3 3 N LOST
         */
        Coordinates coordinates = new Coordinates(3, 2);
        robot = new Robot(marsLand, coordinates, direction);
        try {
            robot.moveForward();
            robot.spinRight();
            robot.spinRight();
            robot.moveForward();
            robot.spinLeft();
            robot.spinLeft();
            robot.moveForward();
            robot.moveForward();
            robot.spinRight();
            robot.spinRight();
            robot.moveForward();
            robot.spinLeft();
            robot.spinLeft();
        } catch (RobotOutOfBoundsException e){

        }

        assertEquals("3 3 N " + robot.POSITION_LOST, robot.currentPosition());
    }
}