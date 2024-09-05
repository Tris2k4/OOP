package bool;

import org.json.JSONObject;

public class NotFactory implements NodeFactoryStrategy {
    @Override
    public BooleanNode createNode(JSONObject jsonObject) {
        NotComposite not = new NotComposite();
        not.add(NodeFactory.createNode(jsonObject.getJSONObject("subnode")));
        return not;
    }
}
