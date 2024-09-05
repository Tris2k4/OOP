package bool;

import org.json.JSONObject;

public interface NodeFactoryStrategy {
    BooleanNode createNode(JSONObject jsonObject);
}
