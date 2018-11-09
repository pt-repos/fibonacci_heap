public class Main {

    public static void main(String[] args) {

        MaxFibonacciHeap heap = new MaxFibonacciHeap();

        Node node = new Node("test1", 5);
        heap.insert(node);

        node = new Node("test2", 3);
        heap.insert(node);

        node = new Node("test3", 1);
        heap.insert(node);

        node = new Node("test4", 8);
        heap.insert(node);

        node = new Node("test5", 4);
        heap.insert(node);

        node = new Node("test6", 9);
        heap.insert(node);

        node = new Node("test7", 2);
        heap.insert(node);

        node = new Node("test8", 10);
        heap.insert(node);

        node = new Node("test9", 11);
        heap.insert(node);

        node = new Node("test10", 6);
        heap.insert(node);

        System.out.println("initial:");
        heap.display();
        System.out.println("max node: " + heap.getMaxNode().getPriority());

        for (int index = 0; index < 7; index++) {
            System.out.println("--------------------------");
            heap.removeMax();
            heap.display();
        }
    }
}
