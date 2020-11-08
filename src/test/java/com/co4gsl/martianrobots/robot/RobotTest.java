package com.co4gsl.martianrobots.robot;

import com.co4gsl.martianrobots.BaseTest;
import com.co4gsl.martianrobots.command.ICommand;
import com.co4gsl.martianrobots.command.MoveForwardCommand;
import com.co4gsl.martianrobots.command.SpinLeftCommand;
import com.co4gsl.martianrobots.command.SpinRightCommand;
import com.co4gsl.martianrobots.direction.EastDirection;
import com.co4gsl.martianrobots.direction.NorthDirection;
import com.co4gsl.martianrobots.direction.SouthDirection;
import com.co4gsl.martianrobots.direction.WestDirection;
import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.universe.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        robot = new Robot(marsLand, new Coordinates(1, 1), new EastDirection());
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

        /*
            0 3 W
            LLFFFLLFL
            Output : 2 3 S
         */
        Robot robot1 = new Robot(marsLand, new Coordinates(0, 3), new WestDirection());
        try {
            robot1.spinLeft();
            robot1.spinLeft();
            robot1.moveForward();
            robot1.moveForward();
            robot1.moveForward();
            robot1.spinLeft();
            robot1.spinLeft();
            robot1.moveForward();
            robot1.spinLeft();
        } catch (RobotOutOfBoundsException e) {
        }
        assertEquals("2 3 S", robot1.currentPosition());
    }

    @Test
    public void whenRequestedRobotCurrentPositionWithStatusLOST() {
        /*
            3 2 N
            FRRFLLFFRRFLL
            Output : 3 3 N LOST
         */
        Robot robot = new Robot(marsLand, new Coordinates(3, 2), new NorthDirection());
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

    @Test
    public void whenRequestedRoverCanExecuteCommandAsAList() throws Exception {
        /*
            0 3 W
            LLFFFLFLFL
            Output : 3 3 N LOST
         */
        robot = new Robot(marsLand, new Coordinates(0, 3), new WestDirection());
        List<ICommand> commandArrayList = new ArrayList<>();
        commandArrayList.add(new SpinLeftCommand());
        commandArrayList.add(new SpinLeftCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new SpinLeftCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new SpinLeftCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new SpinLeftCommand());
        try {
            robot.executeCommandList(commandArrayList);
        } catch (RobotOutOfBoundsException e){
        }

        assertEquals("3 3 N " + robot.POSITION_LOST, robot.currentPosition());
    }
}