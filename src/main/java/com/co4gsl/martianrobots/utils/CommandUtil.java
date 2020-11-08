package com.co4gsl.martianrobots.utils;

import com.co4gsl.martianrobots.command.ICommand;
import com.co4gsl.martianrobots.direction.IDirection;
import com.co4gsl.martianrobots.enums.Command;
import com.co4gsl.martianrobots.enums.Direction;
import com.co4gsl.martianrobots.robot.Robot;
import com.co4gsl.martianrobots.universe.Coordinates;
import com.co4gsl.martianrobots.universe.MarsLand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandUtil {

    public static List<String> readFromInputStream(InputStream inputStream) throws IOException {
        List<String> inputsFromFile = new ArrayList<>();
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputsFromFile.add(line);
            }
        }
        return inputsFromFile;
    }

    public static MarsLand getMarsLandFromInput(String marsLandInputSize) {
        String[] inputArray = marsLandInputSize.split(" ");
        int topRightXCoordinate = Integer.parseInt(inputArray[0]);
        int topRightYCoordinate = Integer.parseInt(inputArray[1]);
        return new MarsLand(topRightXCoordinate, topRightYCoordinate);
    }

    public static Robot createRobotFromInput(String positionInput, MarsLand marsLand) {
        String[] inputArray = positionInput.split(" ");
        Coordinates landingCoordinates = new Coordinates(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]));
        IDirection robotDirectionOnLanding = Direction.valueOf(inputArray[2]).getDirection();
        return new Robot(marsLand, landingCoordinates, robotDirectionOnLanding);
    }

    public static List<ICommand> getCommandListFromInput(String command) {
        char[] inputArray = command.toCharArray();
        List<ICommand> commandArrayList = new ArrayList<>();

        for (char character : inputArray) {
            ICommand currentCommand = Command.valueOf(Character.toString(character)).getCommand();
            commandArrayList.add(currentCommand);
        }

        return commandArrayList;
    }
}
