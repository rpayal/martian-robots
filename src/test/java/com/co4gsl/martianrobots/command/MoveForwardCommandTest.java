package com.co4gsl.martianrobots.command;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.direction.NorthDirection;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveForwardCommandTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new NorthDirection();
        robot = new Robot(marsLand, coordinates, direction);
        command = new MoveForwardCommand();
    }

    @Test
    public void whenExecutedRobotMovesForward() throws Exception {
        int expected = coordinates.getYCoordinate() + 1;
        command.execute(robot);
        assertEquals(expected, robot.getCurrentCoordinates().getYCoordinate());
    }
}