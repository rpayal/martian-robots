package com.co4gsl.martianrobots.direction;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NorthDirectionTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new NorthDirection();
        robot = new Robot(marsLand, coordinates, direction);
    }

    @Test
    public void whenTurnsRightNewDirectionIsEast() throws Exception {
        assertEquals(EastDirection.class, direction.spinRight().getClass());
    }

    @Test
    public void whenTurnsLeftNewDirectionIsWest() throws Exception {
        assertEquals(WestDirection.class, direction.spinLeft().getClass());
    }

    @Test
    public void whenMovesForwardCoordinateYIncrements() throws Exception {
        int expected = coordinates.getYCoordinate() + 1;
        direction.moveForward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getYCoordinate());
    }

    @Test
    public void whenMovesBackCoordinateYDecrements() throws Exception {
        int expected = coordinates.getYCoordinate() - 1;
        direction.moveBackward(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getYCoordinate());
    }
}