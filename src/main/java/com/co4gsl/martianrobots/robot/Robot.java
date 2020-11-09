package com.co4gsl.martianrobots.robot;

import com.co4gsl.martianrobots.command.ICommand;
import com.co4gsl.martianrobots.direction.IDirection;
import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.universe.Coordinates;
import com.co4gsl.martianrobots.universe.MarsLand;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class Robot {
    public final String POSITION_LOST = "LOST";

    @NonNull
    private MarsLand marsland;
    @NonNull
    private Coordinates currentCoordinates;
    @NonNull
    private IDirection currentDirection;

    private boolean robotLost;

    public void spinRight() {
        currentDirection = getCurrentDirection().spinRight();
    }

    public void spinLeft() {
        currentDirection = getCurrentDirection().spinLeft();
    }

    public void moveForward() throws RobotOutOfBoundsException {
        getCurrentDirection().moveForward(this);
        checkPosition();
    }

    public void executeCommandList(List<ICommand> commands) throws RobotOutOfBoundsException {
        for (ICommand command : commands)
            command.execute(this);
    }

    public String currentPosition() {
        String checkIfLost = this.isRobotLost() ? " " + POSITION_LOST : "";
        return getCurrentCoordinates().getXCoordinate() + " "
                + getCurrentCoordinates().getYCoordinate() + " "
                + getCurrentDirection().getClass().getSimpleName().charAt(0)
                + checkIfLost;
    }

    private void checkPosition() throws RobotOutOfBoundsException {
        if (! getMarsland().hasWithinBounds(getCurrentCoordinates())) {
            this.getCurrentDirection().moveBackward(this);
            if (this.marsland.getScentedCoordinates().contains(this.getCurrentCoordinates())) {
                System.out.println("MarsLand coordinate {"+ this.currentCoordinates +"} is already scented by previous robot.");
            } else {
                this.setRobotLost(true);
                marsland.dropScent(this.currentCoordinates);
                throw new RobotOutOfBoundsException("Robot {" + System.identityHashCode(this) + "} Jumped of the MarsLand");
            }
        }
    }
}
