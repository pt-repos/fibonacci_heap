import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordCounter {

    public static void main(String[] args) {

        Hashtable<String, Node> table = new Hashtable<>();
        MaxFibonacciHeap heap = new MaxFibonacciHeap();

        BufferedWriter writer = null;

        try {
            File outputFile = new File("../output.txt");
            writer = new BufferedWriter(new FileWriter(outputFile));

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            Pattern key_pattern = Pattern.compile("([$])([a-zA-Z]+)(\\s)(\\d+)");
            Pattern count_pattern = Pattern.compile("(\\d+)");

            String s = reader.readLine();

            while (true) {
                Matcher key_matcher = key_pattern.matcher(s);
                Matcher count_matcher = count_pattern.matcher(s);

                if (key_matcher.matches()) {
                    String keyword = key_matcher.group(2);
                    int priority = Integer.parseInt(key_matcher.group(4));

                    if (!table.containsKey(keyword)) {
                        Node node = new Node(keyword, priority);
                        heap.insert(node);
                        table.put(keyword, node);
                    } else {
                        Node node = table.get(keyword);
                        heap.increaseKey(node, priority);
                    }
                } else if (count_matcher.find()) {
                    int count = Integer.parseInt(count_matcher.group(1));
                    List<Node> nodesList = getTopKeywords(heap, table, count);

                    for (Node node : nodesList) {
                        writer.write(node.getKeyword() + ", ");
                    }
                    writer.newLine();
                } else if ("stop".equalsIgnoreCase(s)) {
                    break;
                }
                s = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Node> getTopKeywords(MaxFibonacciHeap heap, Hashtable table, int count) {
        List<Node> nodesList = new ArrayList<>();

        //collect top keywords from fibonacci heap
        for (int index = 0; index < count; index++) {
            Node node = heap.removeMax();
            nodesList.add(node);
            table.remove(node.getKeyword());
        }

        //reinsert the keywords in the heap
        for (Node node : nodesList) {
            heap.insert(node);
            table.put(node.getKeyword(), node);
        }

        return nodesList;
    }
}
