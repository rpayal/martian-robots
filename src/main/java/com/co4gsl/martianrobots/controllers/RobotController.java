package com.co4gsl.martianrobots.controllers;

import com.co4gsl.martianrobots.command.ICommand;
import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.robot.Robot;
import com.co4gsl.martianrobots.universe.MarsLand;
import com.co4gsl.martianrobots.utils.CommandUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

        List<String> inputFileAsList = CommandUtil.readFromInputStream(RobotController.class.getResourceAsStream("/commands.txt"));
        landingDetails.append("Sample Input\n");
        for (String line : inputFileAsList)
            landingDetails.append(line + "\n");

        MarsLand marsLand = CommandUtil.getMarsLandFromInput(inputFileAsList.get(0));
        // First line for defining MarsLand
        List<String> commandInputs = inputFileAsList.subList(1, inputFileAsList.size());

        landingDetails.append("\nOutput\n");
        for (int i = 0; i < commandInputs.size(); i += 2) {
            Robot robot = CommandUtil.createRobotFromInput(commandInputs.get(i), marsLand);
            List<ICommand> commands = CommandUtil.getCommandListFromInput(commandInputs.get(i + 1));
            try {
                robot.executeCommandList(commands);
            } catch (RobotOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
            landingDetails.append(robot.currentPosition() + "\n");
        }

        return landingDetails.toString();
    }
}
