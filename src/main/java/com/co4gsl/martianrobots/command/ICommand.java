package com.co4gsl.martianrobots.command;

import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.robot.Robot;

public interface ICommand {

    void execute(final Robot robot) throws RobotOutOfBoundsException;
}
