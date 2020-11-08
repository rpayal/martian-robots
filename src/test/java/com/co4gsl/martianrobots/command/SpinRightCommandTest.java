package com.co4gsl.martianrobots.command;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.direction.EastDirection;
import com.co4gsl.martianrobots.direction.NorthDirection;
import com.co4gsl.martianrobots.direction.WestDirection;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpinRightCommandTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new NorthDirection();
        robot = new Robot(marsLand, coordinates, direction);
        command = new SpinRightCommand();
    }

    @Test
    public void whenExecutedRobotDirectionToEast() throws Exception {
        command.execute(robot);
        assertEquals(EastDirection.class, robot.getCurrentDirection().getClass());
    }
}