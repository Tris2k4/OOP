package trafficlight;

public class Pedestrian implements State {
    private TrafficLight trafficLight;

    public Pedestrian(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    public void changeCount(int numOfCars, int numOfPedestrians) {
        // Pedestrian light typically does not change count based on traffic.
        trafficLight.setCount(getInitialCount());
    }

    @Override
    public void transition(int numOfCars, int numOfPedestrians) {
        trafficLight.setState(new GreenLight(trafficLight));
    }

    @Override
    public String reportState() {
        return "Pedestrian light";
    }

    @Override
    public int getInitialCount() {
        return 2;
    }
}
