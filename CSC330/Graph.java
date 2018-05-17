
/**
 * This Program read the IMBD data set and makes a graph and Answers given questions
 *
 * @author WahabEhsan
 */
import java.io.*;
import java.util.*;

public class Graph {

    public int hashkeys;
    public HashMap<String, Integer> map;
    private int V;   // No. of vertices
    private LinkedList<Edge> adj[]; //Adjacency Lists

    // Constructor
    Graph(int v) {
        V = v;
        hashkeys = 0;
        map = new HashMap();
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    /**
     * Test the graph by creating it and then runs the process
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph gr = new Graph(1100);//Size determines the approximate amount of actors
        gr.Processer();
        System.out.println("Shortest path from Bradley Cooper to Vincent Cassel: ");
        System.out.println("");
        gr.DijkstraAlgorithm("Bradley Cooper", "Vincent Cassel");
        System.out.println("");
        System.out.println("The weight between Rooney Mara and Scarlett Johansson:");
        gr.getWeight("Rooney Mara", "Scarlett Johansson");

    }

    /**
     * This method returns the weight
     *
     * @param v the first node in edge
     * @param w the second node in edge
     */
    public void getWeight(String v, String w) {
        Iterator<Edge> i = adj[map.get(v)].listIterator();
        while (i.hasNext()) {
            Edge n = i.next();
            if (n.v.equals(v) && n.w.equals(w)) {
                System.out.println(n.weight);
                break;
            }
        }
    }

    // Function to add an edge into the graph
    /**
     * Adds an edge to the graph
     *
     * @param v the first node in edge
     * @param w the second node in edge
     */
    public void addEdge(String v, String w) {
        adj[map.get(v)].add(new Edge(v, w));
        adj[map.get(w)].add(new Edge(w, v));
    }

    /**
     * Sets eat actor to a hash keys and checks if already exist then adds it to
     * the graph
     *
     * @param actors the arrays passed through with 4 at a time
     */
    public void hashMapping(String[] actors) {
        for (int i = 0; i < actors.length; i++) {
            if (actors[i].startsWith(" ")) {
                actors[i] = actors[i].substring(1);
            }
            if (!map.containsKey(actors[i])) {
                map.put(actors[i], hashkeys++);
            }
        }
        addingToGraph(actors);
    }

    /**
     * Adds the actors to the graph by checking if already connected or not
     *
     * @param actors the arrays passed through with 4 at a time
     */
    public void addingToGraph(String[] actors) {
        if (actors.length != 1) {
            for (int i = 0; i < actors.length - 1; i++) {
                for (int j = i + 1; j < actors.length; j++) {
                    //checks each edge in the list
                    Iterator<Edge> EdgeIter = adj[map.get(actors[i])].iterator();
                    boolean connected = false;
                    while (EdgeIter.hasNext()) {
                        Edge edge = EdgeIter.next();
                        if (edge.w.equals(actors[j])) {
                            edge.weight++;
                            connected = true;
                            break;
                        }
                    }
                    if (!connected) { // if not connected then adds the actor to the graph
                        addEdge(actors[i], actors[j]);
                    }
                }
            }
        }
    }

    // A function used by DFS
    /**
     * Util for the Depth First search algorithm
     *
     * @param v the left node
     * @param w the right node
     * @param weight the total weight so far
     * @param visited if already visited or not
     * @return the total value of weight
     */
    public int DFSUtil(String v, String w, int weight, boolean visited[]) {
        if (v.equals(w)) {
            return 0;
        }
        // Mark the current node as visited and print it
        visited[map.get(v)] = true;
        // Recur for all the vertices adjacent to this vertex
        Iterator<Edge> i = adj[map.get(v)].listIterator();
        while (i.hasNext()) {
            Edge n = i.next();
            if (!visited[map.get(n.w)]) {
                int calc = DFSUtil(n.w, w, n.weight, visited);
                if (calc != -1) {
                    System.out.print("\"" + n.w + "\"" + " coming from with weight of " + n.weight + " ");
                    return n.weight + calc;
                }
            }
        }
        return -1;
    }

    // The function to do DFS traversal. It uses recursive DFSUtil()
    /**
     * The main Depth first search method
     *
     * @param v the First node
     * @param w the second node
     */
    public void DFS(String v, String w) {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[map.size()];
        // Call the recursive helper function to print DFS traversal
        int cost = DFSUtil(v, w, 0, visited);
        if (cost != -1) {
            System.out.print("\"" + v + "\"" + " with a total cost of " + cost);
        }
    }

    /**
     * Attempt on making the Dijkstra Algorithm, similar to DFS
     *
     * @param v First node
     * @param w Second node
     */
    public void DijkstraAlgorithm(String v, String w) {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[map.size()];
        // Call the recursive helper function to print DFS traversal
        int cost = DijkstraAlgorithmUtil(v, w, 0, visited);
        if (cost != -1) {
            System.out.print("\"" + v + "\"" + " with a total cost of " + cost);

        }
    }

    /**
     * DijkstraAlgorithm's util method.
     *
     * @param v First node
     * @param w Second node
     * @param weight the total weight so far
     * @param visited checks if visited or not
     * @return the total weight so far
     */
    public int DijkstraAlgorithmUtil(String v, String w, int weight, boolean[] visited) {

        if (v.equals(w)) {
            return 0;
        }
        // Mark the current node as visited and print it
        visited[map.get(v)] = true;
        // Recur for all the vertices adjacent to this vertex
        Iterator<Edge> i = adj[map.get(v)].listIterator();
        while (i.hasNext()) {
            Edge n = i.next();
            if (!visited[map.get(n.w)]) {
                int calculations = DijkstraAlgorithmUtil(n.w, w, n.weight, visited);
                if (calculations != -1) {
                    System.out.print("\"" + n.w + "\"" + " coming from with weight of " + n.weight + " ");
                    return n.weight + calculations;
                }
            }
        }
        return -1;
    }

    // prints BFSearch traversal from a given source s
    /**
     * Breadth First search
     *
     * @param v First Node
     * @param w Second Node
     */
    public void BFSearch(String v, String w) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
        // Create a queue for BFSearch
        LinkedList<String> queue = new LinkedList<String>();
        // Mark the current node as visited and enqueue it
        visited[map.get(v)] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            v = queue.poll();
            System.out.print(v + " ");
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Edge> i = adj[map.get(v)].listIterator();
            while (i.hasNext()) {
                Edge n = i.next();
                if (!visited[map.get(n.w)]) {
                    visited[map.get(n.w)] = true;
                    queue.add(n.w);
                }
            }
        }
    }

    /**
     * Takes the content from the file and splits the actors from the other data
     * and feed data to methods for hashMap.
     */
    public void Processer() {

        String content = "";
        Scanner input = new Scanner(System.in);
        Scanner fileReader = Reader.getInputScanner(input); //makes filereader scanner
        while (fileReader.hasNextLine()) {
            content = content + fileReader.nextLine();
            String[] split = content.split("\t");//Split based on tab
            if (split.length != 1) {
                String content3 = split[split.length - 1];
                String[] split2 = content3.split(",");//split based on commas
                if (split2.length != 1) {
                    hashMapping(split2);
                    //System.out.println(Arrays.toString(split2));
                }
            }
            content = "";
        }
    }
}
//Edge class 

class Edge {

    //feilds
    int weight;
    String v;
    String w;
    Edge undirectEdge;

    //Constructor
    public Edge(String v, String w) {
        this.v = v;
        this.w = w;
        weight = 1;
    }

    @Override
    public String toString() {
        return v + " to " + w;
    }
}

class Reader {

    /**
     * Returns Scanner for an input file Use a try/catch block to catch and
     * handle any FileNotFoundException's that occur
     *
     * @param console Scanner input
     * @return File is returned after valid file is found
     */
    public static Scanner getInputScanner(Scanner console) {
        Scanner file = null;
        try {
            File f; //makes file object
            do {
                //IMDBDataset.csv
                //"/Users/WahabEhsan/Desktop/CSC330/src/IMDBDataset.tsv"
                System.out.println("Enter File name ->(IMDBDataset.tsv): ");
                String input = console.nextLine();
                f = new File(input);
                if (!f.exists()) { //if file not found, error message appears 
                    System.out.println("File not Found");
                }
            } while (!f.exists()); //runs do-while loop until file found 
            file = new Scanner(f); //passes file into scanner
        } catch (FileNotFoundException ex) { //catches FileNotFoundExceptions
            System.out.print("File not found");
        }
        return file; //returns scanner for file
    }
}
