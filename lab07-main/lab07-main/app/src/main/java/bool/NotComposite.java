package bool;

import java.util.ArrayList;

public class NotComposite implements BooleanNode {
    private ArrayList<BooleanNode> children = new ArrayList<>();
    public NotComposite() {
        super();
    }

    @Override
    public boolean evaluate() {
        return !children.get(0).evaluate();
    }

    @Override
    public String prettyPrint() {
        return "(NOT " + children.get(0).prettyPrint() + ')';
    }

    public boolean add(BooleanNode child) {
        children.add(child);
        return true;
    }

    public boolean remove(BooleanNode child) {
        children.add(child);
        return true;
    }
}
