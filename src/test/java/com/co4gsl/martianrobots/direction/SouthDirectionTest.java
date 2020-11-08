package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SouthDirectionTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new SouthDirection();
        robot = new Robot(marsLand, coordinates, direction);
    }

    @Test
    public void whenTurnsRightNewDirectionIsWest() throws Exception {
        assertEquals(WestDirection.class, direction.spinRight().getClass());
    }

    @Test
    public void whenTurnsLeftNewDirectionIsEast() throws Exception {
        assertEquals(EastDirection.class, direction.spinLeft().getClass());
    }

    @Test
    public void whenMovesForwardCoordinateYDecrements() throws Exception {
        int expected = coordinates.getYCoordinate() - 1;
        direction.moveForward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getYCoordinate());
    }

    @Test
    public void whenMovesBackCoordinateYIncrements() throws Exception {
        int expected = coordinates.getYCoordinate() + 1;
        direction.moveBackward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getYCoordinate());
    }
}