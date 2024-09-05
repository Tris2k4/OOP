package metrics.Button;

import java.util.List;

public class AllPoints implements Button {
    @Override
    public List<Double> draw(List<Double> data) {
        return data;
    }
}
