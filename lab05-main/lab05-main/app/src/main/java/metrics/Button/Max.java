package metrics.Button;

import java.util.ArrayList;
import java.util.List;

public class Max implements Button {
    @Override
    public List<Double> draw(List<Double> data) {
        List<Double> newList = new ArrayList<Double>();

        Double maxDouble = -1.0;
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (count % 5 == 0) {
                newList.add(maxDouble);
                maxDouble = -1.0;
            }

            maxDouble = Math.max(maxDouble, data.get(i));
            count++;
        }
        return newList;
    }
}
