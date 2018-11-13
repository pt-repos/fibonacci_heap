public class MaxFibonacciHeap {

    private Node maxNode;

    public MaxFibonacciHeap() {
    }

    public MaxFibonacciHeap(Node maxNode) {
        this.maxNode = maxNode;
    }

    public Node getMaxNode() {
        return maxNode;
    }

    public void setMaxNode(Node maxNode) {
        this.maxNode = maxNode;
    }

    /**
     * Inserts a new node to the Max Fibonacci Heap.
     *
     * @param newNode
     */
    public void insert(Node newNode) {
        if (maxNode == null) {
            maxNode = newNode;
        } else {
            newNode.setLeft(maxNode);
            newNode.setRight(maxNode.getRight());
            newNode.getRight().setLeft(newNode);
            maxNode.setRight(newNode);

            if (newNode.getPriority() > maxNode.getPriority()) {
                maxNode = newNode;
            }
        }
    }

    /**
     * Removes the maxNode from Max Fibonacci Heap.
     *
     * @return maxNode which was removed from the heap.
     */
    public Node removeMax() {

        Node max = maxNode;
        int degree = maxNode.getDegree();

        while (degree > 0) {
            Node child = maxNode.getChild();
            child.getLeft().setRight(child.getRight());
            child.getRight().setLeft(child.getLeft());
//            removeFromSiblingsList(child);
            child.setParent(null);

            if (degree != 1) {
                maxNode.setChild(child.getRight());
            } else {
                maxNode.setChild(null);
            }

            insert(child);
            degree--;
        }

        maxNode.getLeft().setRight(maxNode.getRight());
        maxNode.getRight().setLeft(maxNode.getLeft());

        maxNode = findTopLevelMax();
        combine();

        return max;
    }

    /**
     * Increases the priority of the given node.
     *
     * @param node     Node which is to be updated.
     * @param priority amount to increase the node's priority by.
     */
    public void increaseKey(Node node, int priority) {

        node.setPriority(node.getPriority() + priority);

        if (!maxNode.equals(node)) {
            if (null != node.getParent() && node.getPriority() > node.getParent().getPriority()) {
                cascadingCut(node);
                maxNode = findTopLevelMax();
            }
        }
    }

    /**
     * Finds Node with highest priority in the top level siblings list.
     *
     * @return Node with the maximum priority.
     */
    private Node findTopLevelMax() {
        Node head = maxNode.getRight().getRight();
        Node max = maxNode.getRight();
        Node startingNode = max;
        while (head != startingNode) {
            if (head.getPriority() > max.getPriority()) {
                max = head;
            }
            head = head.getRight();
        }
        return max;
    }

    /**
     * Pairwise combines Nodes with equal degrees in the top level siblings list.
     */
    private void combine() {
        Node[] degreeTable = new Node[25];
        Node head = maxNode;

        do {
            int index = head.getDegree();

            if (null == degreeTable[index]) {
                degreeTable[index] = head;
                head = head.getRight();
            } else {
                Node node = meld(head, degreeTable);
                head = node.getRight();
            }
        } while (head != maxNode);
    }

    /**
     * Melds two nodes with equal degrees.
     * Results in the node with higher priority as the parent.
     * Returns the parent node.
     *
     * @param node1         Node to be melded.
     * @param degreeTable   Array of Nodes indexed by their respective degrees.
     * @return Parent Node after recursive meld operation.
     */
    private Node meld(Node node1, Node[] degreeTable) {

        Node greater;
        Node smaller;
        Node node2 = degreeTable[node1.getDegree()];
        degreeTable[node1.getDegree()] = null;

        if (node1.getPriority() > node2.getPriority()) {
            greater = node1;
            smaller = node2;
        } else {
            smaller = node1;
            greater = node2;
        }

        smaller.setParent(greater);
        smaller.setChildCut(false);

        smaller.getLeft().setRight(smaller.getRight());
        smaller.getRight().setLeft(smaller.getLeft());

        greater.setDegree(greater.getDegree() + 1);

        if (null != greater.getChild()) {
            Node child = greater.getChild();

            smaller.setLeft(child.getLeft());
            smaller.setRight(child);

            // TODO: 11/9/2018 rectify child pointers.
            child.getLeft().setRight(smaller);
            child.setLeft(smaller);
        } else {
            greater.setChild(smaller);
            smaller.setLeft(smaller);
            smaller.setRight(smaller);
        }

        if (null != degreeTable[greater.getDegree()]) {
            return meld(greater, degreeTable);
        }

        degreeTable[greater.getDegree()] = greater;
        return greater;
    }

    /**
     * Removes the node from it's siblings list and inserts into the top level list.
     * Operation is performed recursively on node's parent until childCut value false is encountered.
     *
     * @param node Node to be cut.
     */
    private void cascadingCut(Node node) {

        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());

        Node parent = node.getParent();

        if (1 != parent.getDegree()) {
            parent.setChild(node.getRight());
        } else {
            parent.setChild(null);
        }

        parent.setDegree(parent.getDegree() - 1);

        node.setRight(maxNode);
        node.setLeft(maxNode.getLeft());
        maxNode.getLeft().setRight(node);
        maxNode.setLeft(node);

        if (parent.isChildCut()) {
            cascadingCut(parent);
        }

        node.setParent(null);
    }

    /**
     *
     */
    public void display() {

        Node head = maxNode;

        do {
            System.out.println(head.getPriority());

            if (null != head.getChild()) {
                displayChildren(head, 1);
            }

            head = head.getRight();
        } while (head != maxNode);
    }

    /**
     * @param node
     * @param level
     */
    private void displayChildren(Node node, int level) {

        Node head = node.getChild();

        do {
            for (int index = level; index > 0; index--) {
                System.out.print("-");
            }
            System.out.print(head.getPriority() + "\n");

            if (null != head.getChild()) {
                displayChildren(head, level + 1);
            }

            head = head.getRight();
        } while (head != node.getChild());

    }
}
