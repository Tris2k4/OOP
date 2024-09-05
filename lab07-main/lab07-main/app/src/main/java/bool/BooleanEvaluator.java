package bool;


public class BooleanEvaluator {

    public static boolean evaluate(BooleanNode expression) {
        // Return the expression evaluated
        return expression.evaluate();
    }

    public static String prettyPrint(BooleanNode expression) {
        // Pretty print the expression
        return expression.prettyPrint();
    }

    public static void main(String[] args) {
        // BooleanEvaluator.evaluate(...)
        // BooleanEvaluator.evaluate(...)
        OrComposite or = new OrComposite();
        NotComposite not = new NotComposite();
        AndComposite and = new AndComposite();
        OrComposite or2 = new OrComposite();

        Leaf leaf1 = new Leaf(true);
        Leaf leaf2 = new Leaf(false);
        Leaf leaf3 = new Leaf(true);
        Leaf leaf4 = new Leaf(false);

        or.add(leaf1);
        or.add(not);
        not.add(and);
        and.add(leaf2);
        and.add(or2);
        or2.add(leaf3);
        or2.add(leaf4);

        Boolean result = BooleanEvaluator.evaluate(or);
        String prettyResult = BooleanEvaluator.prettyPrint(or);
        System.out.println(result);
        System.out.println(prettyResult);

        OrComposite or3 = new OrComposite();
        NotComposite not2 = new NotComposite();

        or3.add(leaf2);
        or3.add(not2);
        not2.add(leaf2);
        System.out.println(BooleanEvaluator.prettyPrint(or3) + ' ' + BooleanEvaluator.evaluate(or3));

    }

}
