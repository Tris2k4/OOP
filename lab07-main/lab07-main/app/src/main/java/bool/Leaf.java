package bool;

public class Leaf implements BooleanNode {
    private boolean expression;


    public Leaf(boolean expression) {
        super();
        this.expression = expression;
    }

    @Override
    public boolean evaluate() {
        return expression;
    }

    @Override
    public String prettyPrint() {
        return expression ? "true" : "false";
    }

    public boolean isExpression() {
        return expression;
    }

    public void setExpression(boolean expression) {
        this.expression = expression;
    }
}
