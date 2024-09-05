package trafficlight;

public class YellowLight implements State {
    private TrafficLight trafficLight;

    public YellowLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    public void changeCount(int numOfCars, int numOfPedestrians) {
        // Yellow light typically does not change count based on traffic.
    }

    @Override
    public void transition(int numOfCars, int numOfPedestrians) {
        trafficLight.setState(new RedLight(trafficLight));
    }

    @Override
    public String reportState() {
        return "Yellow light";
    }

    @Override
    public int getInitialCount() {
        return 1;
    }
}

