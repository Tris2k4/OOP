package unsw.blackout.Satellite;

import unsw.utils.Angle;

public class RelaySatellite extends Satellite {
    private static final double LOWER_BOUND = 140;
    private static final double UPPER_BOUND = 190;
    private static final double THRESHOLD_ANGLE = 345;
    private static final int MAX_RANGE = 300000;
    private static final int SPEED = 1500;
    private static final int BANDWIDTH_IN = Integer.MAX_VALUE;
    private static final int BANDWIDTH_OUT = Integer.MAX_VALUE;

    public RelaySatellite(double height, Angle position, String satelliteId, String type) {
        super(height, position, satelliteId, type, MAX_RANGE, SPEED, BANDWIDTH_IN, BANDWIDTH_OUT);
        double positionDegree = position.toDegrees();
        boolean direction = ((positionDegree > LOWER_BOUND && positionDegree < UPPER_BOUND)
                || (positionDegree > UPPER_BOUND && positionDegree < THRESHOLD_ANGLE));
        this.setDirection(direction);
    }

    @Override
    public void moveToNextPos() {
        double angularVelocityAngle = Angle.fromRadians(this.getSpeed() / this.getHeight()).toDegrees();
        if (this.getDirection()) {
            if (this.getPosition().toDegrees() < LOWER_BOUND) {
                this.setDirection(false);
                this.setPosition(getPosition().add(Angle.fromDegrees(angularVelocityAngle)));
            } else {
                this.setPosition(getPosition().subtract(Angle.fromDegrees(angularVelocityAngle)));
            }
        } else {
            if (this.getPosition().toDegrees() > UPPER_BOUND && this.getPosition().toDegrees() < THRESHOLD_ANGLE) {
                this.setDirection(true);
                this.setPosition(this.getPosition().subtract(Angle.fromDegrees(angularVelocityAngle)));
            } else {
                this.setPosition(this.getPosition().add(Angle.fromDegrees(angularVelocityAngle)));
            }
        }
    }
}
