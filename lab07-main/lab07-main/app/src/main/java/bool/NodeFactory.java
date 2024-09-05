package bool;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NodeFactory {
    private static final Map<String, NodeFactoryStrategy> FACTORIES = new HashMap<>();

    static {
        FACTORIES.put("and", new AndFactory());
        FACTORIES.put("or", new OrFactory());
        FACTORIES.put("not", new NotFactory());
        FACTORIES.put("value", new LeafFactory());
    }

    public static BooleanNode createNode(JSONObject jsonObject) {
        String nodeType = jsonObject.getString("node");
        NodeFactoryStrategy factory = FACTORIES.get(nodeType);
        return factory.createNode(jsonObject);
    }

    public static void main(String[] args) {
        // Stringified JSON input given
        JSONObject obj = new JSONObject(
                "{\"node\":\"and\",\"subnode1\":{\"node\":\"or\",\"subnode1\":{\"node\":\"value\",\"value\":true},"
                        + "\"subnode2\":{\"node\":\"value\",\"value\":false}},"
                        + "\"subnode2\":{\"node\":\"value\",\"value\":true}}");

        BooleanNode rootNode = NodeFactory.createNode(obj);
        System.out.println(rootNode.prettyPrint()); // Output: (AND (OR true false) true)
        System.out.println(rootNode.evaluate()); // Output: true
    }
}
