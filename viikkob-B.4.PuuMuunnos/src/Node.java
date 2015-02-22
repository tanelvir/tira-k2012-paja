public class Node {

    private Node left; 
    private Node right;
    private int key;   

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public Node(int key) {
        this.key = key;
        // left ja right ovat null
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }

    public int getKey() {
        return key;
    }

    public String toString() {
        String l, r;
        
        if (left == null)
            l = "null";
        else
            l = left.toString();

        if (right == null)
            r = "null";
        else
            r = right.toString();

        return "Node["+key+", "+l+", "+r+"]";
    }

}
