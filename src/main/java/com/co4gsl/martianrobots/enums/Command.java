package com.co4gsl.martianrobots.enums;

import com.co4gsl.martianrobots.command.ICommand;
import com.co4gsl.martianrobots.command.MoveForwardCommand;
import com.co4gsl.martianrobots.command.SpinLeftCommand;
import com.co4gsl.martianrobots.command.SpinRightCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {

    R(new SpinRightCommand()),
    L(new SpinLeftCommand()),
    F(new MoveForwardCommand());

    private final ICommand command;
}
