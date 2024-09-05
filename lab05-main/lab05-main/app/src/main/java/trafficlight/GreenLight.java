package trafficlight;

public class GreenLight implements State {
    private TrafficLight trafficLight;

    public GreenLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    public void changeCount(int numOfCars, int numOfPedestrians) {
        int trafficDemand = numOfCars + numOfPedestrians;
        int count = trafficDemand > 100 ? 6 : 4;
        trafficLight.setCount(count);
    }

    @Override
    public void transition(int numOfCars, int numOfPedestrians) {
        trafficLight.setState(new YellowLight(trafficLight));
    }

    @Override
    public String reportState() {
        return "Green light";
    }

    @Override
    public int getInitialCount() {
        return 4;
    }
}
