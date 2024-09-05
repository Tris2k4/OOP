package metrics.Button;

import java.util.ArrayList;
import java.util.List;

public class Sum implements Button {
    @Override
    public List<Double> draw(List<Double> data) {
        List<Double> newList = new ArrayList<Double>();

        double sum = 0;
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (count == 5) {
                newList.add(sum);
                sum = 0;
                count = 0;
            }
            sum += data.get(i);
            count++;
        }
        return newList;
    }
}
