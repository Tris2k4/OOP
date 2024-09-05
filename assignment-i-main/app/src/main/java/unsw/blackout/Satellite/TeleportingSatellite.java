package unsw.blackout.Satellite;

import unsw.utils.Angle;

public class TeleportingSatellite extends Satellite {
    private static final int MAX_RANGE = 200000;
    private static final int SPEED = 1000;
    private static final int BANDWIDTH_IN = 15;
    private static final int BANDWIDTH_OUT = 10;
    public TeleportingSatellite(double height, Angle position, String satelliteId, String type) {
        super(height, position, satelliteId, type, MAX_RANGE, SPEED, BANDWIDTH_IN, BANDWIDTH_OUT);
        this.setDirection(false);
        this.setMaxFiles(Integer.MAX_VALUE);
        this.setMaxStorage(200);
        this.setAvailableStorage(200);
    }

    @Override
    public void moveToNextPos() {
        double angularVelocityDegrees = Angle.fromRadians(this.getSpeed() / this.getHeight()).toDegrees();

        // Move anti-clockwise if position is greater than 180 degrees until it reaches 180 degrees
        if (this.getPosition().toDegrees() >= 180) {
            if (this.getPosition().toDegrees() - angularVelocityDegrees < 180) {
                this.setPosition(Angle.fromDegrees(180));
            } else {
                this.setPosition(this.getPosition().add(Angle.fromDegrees(angularVelocityDegrees)));
            }
        } else {
            // Regular movement logic with teleportation
            if (!this.getDirection()) {
                if (this.getPosition().toDegrees() + angularVelocityDegrees >= 180) {
                    this.setPosition(Angle.fromDegrees(0));
                    this.setDirection(true); // Change direction to clockwise
                } else {
                    this.setPosition(this.getPosition().add(Angle.fromDegrees(angularVelocityDegrees)));
                }
            } else {
                if (this.getPosition().toDegrees() - angularVelocityDegrees <= 0) {
                    this.setPosition(Angle.fromDegrees(0));
                    this.setDirection(false); // Change direction to anticlockwise
                } else {
                    this.setPosition(this.getPosition().subtract(Angle.fromDegrees(angularVelocityDegrees)));
                }
            }
        }
    }
}
