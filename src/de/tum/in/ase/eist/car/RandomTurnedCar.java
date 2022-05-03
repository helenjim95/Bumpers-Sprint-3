package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;


public class RandomTurnedCar extends Car {

    private static final String RANDOM_TURNED_CAR_IMAGE_FILE = "VW_Kaefer.gif";

    private static final int MIN_SPEED_RANDOM_TURNED_CAR = 3;
    private static final int MAX_SPEED_RANDOM_TURNED_CAR = 5;
    private int speed;
    private boolean crunched;

    private Point2D position;
    private Dimension2D size = new Dimension2D(DEFAULT_CAR_WIDTH, DEFAULT_CAR_HEIGHT);
    /**
     * The direction as degree within a circle, a value between 0 (inclusive) and
     * 360 (exclusive).
     */
    private int direction;

    public RandomTurnedCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_RANDOM_TURNED_CAR);
        setMaxSpeed(MAX_SPEED_RANDOM_TURNED_CAR);
        setRandomSpeed();
        setIconLocation(RANDOM_TURNED_CAR_IMAGE_FILE);
    }


//  random turn direction
    public void drive(Dimension2D gameBoardSize) {
        if (this.crunched) {
            return;
        }
        double maxX = gameBoardSize.getWidth();
        double maxY = gameBoardSize.getHeight();
        // calculate delta between old coordinates and new ones based on speed and
        // direction
        double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
        double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
        double newX = this.position.getX() + deltaX;
        double newY = this.position.getY() + deltaY;

        // calculate position in case the boarder of the game board has been reached
        if (newX < 0) {
            newX = -newX;
            this.direction = MAX_ANGLE - this.direction;
        } else if (newX + this.size.getWidth() > maxX) {
            newX = 2 * maxX - newX - 2 * this.size.getWidth();
            this.direction = MAX_ANGLE - this.direction;
        }

        if (newY < 0) {
            newY = -newY;
            this.direction = HALF_ANGLE - this.direction;
            if (this.direction < 0) {
                this.direction = MAX_ANGLE + this.direction;
            }
        } else if (newY + this.size.getHeight() > maxY) {
            newY = 2 * maxY - newY - 2 * this.size.getHeight();
            this.direction = HALF_ANGLE - this.direction;
            if (this.direction < 0) {
                this.direction = MAX_ANGLE + this.direction;
            }
        }
        // set coordinates
        setRandomDirection();
        this.position = new Point2D(newX, newY);
    }
}
