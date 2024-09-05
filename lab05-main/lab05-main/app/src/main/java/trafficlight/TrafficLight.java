package trafficlight;

public class TrafficLight {
    private String id;
    private State state;
    private final StateFactory stateFactory;
    private int count = 0;
    public TrafficLight(String initialState, String id) {
        this.id = id;
        this.stateFactory = new StateFactory(this);
        setState(initialState);
    }

    private void setState(String stateName) {
        state = stateFactory.getState(stateName);
        if (state == null) {
            throw new IllegalArgumentException("Invalid state: " + stateName);
        }
        this.count = state.getInitialCount();
    }

    public void change(int numOfCars, int numOfPedestrians) {
        if (count > 0) {
            count -= 1;
            return;
        }
        state.transition(numOfCars, numOfPedestrians);
        state.changeCount(numOfCars, numOfPedestrians);
    }

    public int timeRemaining() {
        return count;
    }

    public String reportState() {
        return state.reportState();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
