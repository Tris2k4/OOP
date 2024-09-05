package bool;

import org.json.JSONObject;

public class LeafFactory implements NodeFactoryStrategy {
    @Override
    public BooleanNode createNode(JSONObject jsonObject) {
        return new Leaf(jsonObject.getBoolean("value"));
    }
}
