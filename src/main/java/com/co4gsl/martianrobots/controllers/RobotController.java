package com.co4gsl.martianrobots.controllers;

import com.co4gsl.martianrobots.command.ICommand;
import com.co4gsl.martianrobots.direction.IDirection;
import com.co4gsl.martianrobots.enums.Command;
import com.co4gsl.martianrobots.enums.Direction;
import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.robot.Robot;
import com.co4gsl.martianrobots.universe.Coordinates;
import com.co4gsl.martianrobots.universe.MarsLand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RobotController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to martian-robots Application!!";
    }

    @RequestMapping(value="/land-robots", produces = MediaType.TEXT_PLAIN_VALUE)
    public String landRobots() throws IOException {
        StringBuilder landingDetails = new StringBuilder();

        List<String> inputFileAsList = readFromInputStream(RobotController.class.getResourceAsStream("/commands.txt"));
        landingDetails.append("Sample Input\n");
        for (String line : inputFileAsList)
            landingDetails.append(line + "\n");

        MarsLand marsLand = getMarsLandFromInput(inputFileAsList.get(0));
        // First line for defining MarsLand
        List<String> commandInputs = inputFileAsList.subList(1, inputFileAsList.size());

        landingDetails.append("\nOutput\n");
        for (int i = 0; i < commandInputs.size(); i += 2) {
            Robot robot = createRobotFromInput(commandInputs.get(i), marsLand);
            List<ICommand> commands = getCommandListFromInput(commandInputs.get(i + 1));
            try {
                robot.executeCommandList(commands);
            } catch (RobotOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
            landingDetails.append(robot.currentPosition() + "\n");
        }

        return landingDetails.toString();
    }

    private List<String> readFromInputStream(InputStream inputStream) throws IOException {
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

    private MarsLand getMarsLandFromInput(String marsLandInputSize) {
        String[] inputArray = marsLandInputSize.split(" ");
        int topRightXCoordinate = Integer.parseInt(inputArray[0]);
        int topRightYCoordinate = Integer.parseInt(inputArray[1]);
        return new MarsLand(topRightXCoordinate, topRightYCoordinate);
    }

    private Robot createRobotFromInput(String positionInput, MarsLand marsLand) {
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
