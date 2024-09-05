package bool;

import org.json.JSONObject;

public class AndFactory implements NodeFactoryStrategy {
    @Override
    public BooleanNode createNode(JSONObject jsonObject) {
        AndComposite and = new AndComposite();
        and.add(NodeFactory.createNode(jsonObject.getJSONObject("subnode1")));
        and.add(NodeFactory.createNode(jsonObject.getJSONObject("subnode2")));
        return and;
    }
}
