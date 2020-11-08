package com.co4gsl.martianrobots.command;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.direction.NorthDirection;
import com.co4gsl.martianrobots.direction.WestDirection;
import com.co4gsl.martianrobots.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpinLeftCommandTest extends BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        direction = new NorthDirection();
        robot = new Robot(marsLand, coordinates, direction);
        command = new SpinLeftCommand();
    }

    @Test
    public void whenExecutedRobotDirectionToWest() throws Exception {
        command.execute(robot);
        assertEquals(WestDirection.class, robot.getCurrentDirection().getClass());
    }
}