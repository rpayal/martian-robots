package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WestDirectionTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new WestDirection();
        robot = new Robot(marsLand, coordinates, direction);
    }

    @Test
    public void whenTurnsRightNewDirectionIsNorth() throws Exception {
        assertEquals(direction.spinRight().getClass(), NorthDirection.class);
    }

    @Test
    public void whenTurnsLeftNewDirectionIsSouth() throws Exception {
        assertEquals(direction.spinLeft().getClass(), SouthDirection.class);
    }

    @Test
    public void whenMovesForwardCoordinateXDecrements() throws Exception {
        int expected = coordinates.getXCoordinate() - 1;
        direction.moveForward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getXCoordinate());
    }

    @Test
    public void whenMovesBackCoordinateXIncrements() throws Exception {
        int expected = coordinates.getXCoordinate() + 1;
        direction.moveBackward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getXCoordinate());
    }
}