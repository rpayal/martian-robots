package com.co4gsl.martianrobots.robot;

import com.co4gsl.martianrobots.direction.IDirection;
import com.co4gsl.martianrobots.exception.RobotOutOfBoundsException;
import com.co4gsl.martianrobots.universe.Coordinates;
import com.co4gsl.martianrobots.universe.MarsLand;
import lombok.*;

import java.util.Optional;

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

    public String currentPosition() {
        String checkIfLost = this.isRobotLost() ? " " + POSITION_LOST : "";
        return getCurrentCoordinates().getXCoordinate() + " "
                + getCurrentCoordinates().getYCoordinate() + " "
                + Character.toString(getCurrentDirection().getClass().getSimpleName().charAt(0))
                + checkIfLost;
    }

    private void checkPosition() throws RobotOutOfBoundsException {
        if (! getMarsland().hasWithinBounds(getCurrentCoordinates())) {
            getCurrentDirection().moveBackward(this);
            this.setRobotLost(true);
//            marsland.dropScent(this.currentCoordinates);
            throw new RobotOutOfBoundsException();
        }
    }
}