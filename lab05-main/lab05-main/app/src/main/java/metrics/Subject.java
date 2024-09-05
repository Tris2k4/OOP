package metrics;

public interface Subject {
    public void notifyObservers();
    public void registerObserver(Plot plot);
    public void removeObserver(Plot plot);
}
