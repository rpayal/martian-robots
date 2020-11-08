package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EastDirectionTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new EastDirection();
        robot = new Robot(marsLand, coordinates, direction);
    }

    @Test
    public void whenTurnsRightNewDirectionIsSouth() throws Exception {
        assertEquals(SouthDirection.class, direction.spinRight().getClass());
    }

    @Test
    public void whenTurnsLeftNewDirectionIsNorth() throws Exception {
        assertEquals(NorthDirection.class, direction.spinLeft().getClass());
    }

    @Test
    public void whenMovesForwardCoordinateXIncrements() throws Exception {
        int expected = coordinates.getXCoordinate() + 1;
        direction.moveForward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getXCoordinate());
    }

    @Test
    public void whenMovesBackCoordinateXDecrements() throws Exception {
        int expected = coordinates.getXCoordinate() - 1;
        direction.moveBackward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getXCoordinate());
    }
}