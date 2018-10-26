import java.util.Objects;

public class Node {

    private String keyword;
    private int count = 0;
    private int degree = 0;
    private Node child;
    private Node parent;
    private Node left;
    private Node right;
    private boolean childCut = false;

    public Node() {
    }

    public Node(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }

    public Node(String keyword, int count, int degree, Node child, Node parent, Node left, Node right, boolean childCut) {
        this.keyword = keyword;
        this.count = count;
        this.degree = degree;
        this.child = child;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.childCut = childCut;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isChildCut() {
        return childCut;
    }

    public void setChildCut(boolean childCut) {
        this.childCut = childCut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(keyword, node.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
