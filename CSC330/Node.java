/**
 * Class for the Node Object.
 * 
 * @author WahabEhsan
 */
class Node {
    
    //Fields
    int value;
    Node left;
    Node right;
    
    /**
     * Constructor
     * @param value the value to be put in the Node
     */
    public Node(int value){
        this.value = value;
        left = null;
        right = null;
    }
}
