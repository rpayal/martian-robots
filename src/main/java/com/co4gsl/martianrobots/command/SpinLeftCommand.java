package com.co4gsl.martianrobots.command;

import com.co4gsl.martianrobots.robot.Robot;

public class SpinLeftCommand implements ICommand {

    @Override
    public void execute(Robot robot) {
        robot.spinLeft();
    }
}
