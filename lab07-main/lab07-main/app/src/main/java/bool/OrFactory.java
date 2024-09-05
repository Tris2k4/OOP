package bool;

import org.json.JSONObject;

public class OrFactory implements NodeFactoryStrategy {
    @Override
    public BooleanNode createNode(JSONObject jsonObject) {
        OrComposite or = new OrComposite();
        or.add(NodeFactory.createNode(jsonObject.getJSONObject("subnode1")));
        or.add(NodeFactory.createNode(jsonObject.getJSONObject("subnode2")));
        return or;
    }
}
