public class Tree {

    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() {
        this(null);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String toString() {
        return "Tree["+root+"]";
    }

}
