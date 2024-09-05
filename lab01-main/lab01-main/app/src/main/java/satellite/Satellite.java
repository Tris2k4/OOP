package satellite;

public class Satellite {
    /**
     * Constructor for Satellite
     * @param name
     * @param height
     * @param velocity
     */

    private String name;
    private int height;
    private double position;
    private double velocity;

    public Satellite(String name, int height, double position, double velocity) {
        this.name = name;
        this.height = height;
        this.position = position;
        this.velocity = velocity;
    }

    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for position (degrees)
     */
    public double getPositionDegrees() {
        return Math.toDegrees(position);
    }

    /**
     * Getter for position (radians)
     */
    public double getPositionRadians() {
        return Math.toRadians(position);
    }

    /**
     * Returns the linear velocity (metres per second) of the satellite
     */
    public double getLinearVelocity() {
        return height * getAngularVelocity();
    }

    /**
     * Returns the angular velocity (radians per second) of the satellite
     */
    public double getAngularVelocity() {
        return velocity / height;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Setter for velocity
     * @param velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Setter for position
     * @param position
     */
    public void setPosition(double position) {
        this.position = position;
    }

    /**
     * Calculates the distance travelled by the satellite in the given time
     * @param time (seconds)
     * @return distance in metres
     */
    public double distance(double time) {
        return getLinearVelocity() * time * 60;
    }

    public static void main(String[] args) {
        // Add your code
        Satellite satelliteA = new Satellite("Satellite A", 10000, 122, 55);
        Satellite satelliteB = new Satellite("Satellite B", 5438, 0, 234);
        Satellite satelliteC = new Satellite("Satellite C", 9029, 284, 0);
        System.out.println("I am " + satelliteA.name + " at position " + satelliteA.position + " degrees, "
                + satelliteA.height + " km above the centre of the earth and moving at a velocity of "
                + satelliteA.velocity + " metres per second");
        satelliteA.setHeight(9999);
        satelliteB.setPosition(45);
        satelliteC.setVelocity(36.5);
        System.out.println(satelliteA.getPositionRadians());
        System.out.println(satelliteB.getAngularVelocity());
        System.out.println(satelliteC.distance(2));
    }

}
