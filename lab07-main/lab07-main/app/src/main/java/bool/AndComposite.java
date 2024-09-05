package bool;

import java.util.ArrayList;

public class AndComposite implements BooleanNode {
    private ArrayList<BooleanNode> children = new ArrayList<>();
    public AndComposite() {
        super();
    }

    @Override
    public boolean evaluate() {
        return children.get(0).evaluate() && children.get(1).evaluate();
    }

    @Override
    public String prettyPrint() {
        return "(AND " + children.get(0).prettyPrint() + ' ' + children.get(1).prettyPrint() + ')';
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
