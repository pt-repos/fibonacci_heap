public class MaxFibonacciHeap {

    private Node maxNode;
    private int heapSize;

    public MaxFibonacciHeap() {
    }

    public MaxFibonacciHeap(Node maxNode, int heapSize) {
        this.maxNode = maxNode;
        this.heapSize = heapSize;
    }

    public Node getMaxNode() {
        return maxNode;
    }

    public void setMaxNode(Node maxNode) {
        this.maxNode = maxNode;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }
}
