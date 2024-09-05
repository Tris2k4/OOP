package trafficlight;

public class RedLight implements State {
    private TrafficLight trafficLight;

    public RedLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    public void changeCount(int numOfCars, int numOfPedestrians) {
        int trafficDemand = numOfCars + numOfPedestrians;
        int count = trafficDemand < 10 ? 10 : 6;
        trafficLight.setCount(count);
    }

    @Override
    public void transition(int numOfCars, int numOfPedestrians) {
        if (numOfPedestrians > 0) {
            trafficLight.setState(new Pedestrian(trafficLight));
        } else {
            trafficLight.setState(new GreenLight(trafficLight));
        }
    }

    @Override
    public String reportState() {
        return "Red light";
    }

    @Override
    public int getInitialCount() {
        return 6;
    }
}
