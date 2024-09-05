package trafficlight;

import java.util.HashMap;
import java.util.Map;

public class StateFactory {
    private final Map<String, State> stateMap = new HashMap<>();

    public StateFactory(TrafficLight trafficLight) {
        stateMap.put("Red light", new RedLight(trafficLight));
        stateMap.put("Green light", new GreenLight(trafficLight));
        stateMap.put("Yellow light", new YellowLight(trafficLight));
        stateMap.put("Pedestrian light", new Pedestrian(trafficLight));
    }

    public State getState(String stateName) {
        return stateMap.getOrDefault(stateName, null);
    }
}
