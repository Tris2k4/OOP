package metrics.Button;

import java.util.ArrayList;
import java.util.List;

public class Average implements Button {
    @Override
    public List<Double> draw(List<Double> data) {
        List<Double> newList = new ArrayList<>();
        double sum  = 0;
        int count = 0;

        for (int i = 0; i < data.size(); i++) {
            if (count % 5 == 0) {
                newList.add(sum / 5);
                sum = 0;
            }
            sum += data.get(i);
            count++;
        }
        return newList;
    }
}
