package unsw.blackout.Satellite;

import unsw.utils.Angle;

public class StandardSatellite extends Satellite {
    private static final int MAX_RANGE = 150000;
    private static final int SPEED = 2500;
    private static final int BANDWIDTH_IN = 1;
    private static final int BANDWIDTH_OUT = 1;
    public StandardSatellite(double height, Angle position, String satelliteId, String type) {
        super(height, position, satelliteId, type, MAX_RANGE, SPEED, BANDWIDTH_IN, BANDWIDTH_OUT);
        this.setMaxFiles(3);
        this.setMaxStorage(80);
        this.setAvailableStorage(80);
    }

    @Override
    public void moveToNextPos() {
        double moveAngleDegree = getPosition().subtract(Angle.fromRadians(this.getSpeed() / this.getHeight()))
                .toDegrees();
        if (moveAngleDegree < 0) {
            Angle finalAngle = Angle.fromDegrees(360 + moveAngleDegree);
            setPosition(finalAngle);
        } else {
            setPosition(getPosition().subtract(Angle.fromRadians(this.getSpeed() / this.getHeight())));
        }
    }
}
