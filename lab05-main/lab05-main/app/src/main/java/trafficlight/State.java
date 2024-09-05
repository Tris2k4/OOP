package trafficlight;

public interface State {
    public void changeCount(int numOfCars, int numOfPedestrians);
    public void transition(int numOfCars, int numOfPedestrians);
    public String reportState();
    public int getInitialCount();
}
