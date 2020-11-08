package com.co4gsl.martianrobots.command;

import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.robot.Robot;

public class MoveForwardCommand implements ICommand {

    @Override
    public void execute(Robot robot) throws RobotOutOfBoundsException {
        robot.moveForward();
    }
}
