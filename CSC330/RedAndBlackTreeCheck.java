
/**
 * Java Implementation of BinarySearchTree and checks if RedBlackTree
 *
 * @author WahabEhsan
 */
class BinarySearchTreeRB {

    //Private fields
    private NodeRB root;

    //Default Constructor
    public BinarySearchTreeRB() {
        root = null;
    }

    /**
     * When called, makes the tree empty by setting root to null.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Tells whether the tree empty by checking if null.
     *
     * @return boolean value if Empty or not
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Runs the inorderEncap method for inorder Traversal of the tree.
     */
    public void inorder() {
        inorderEncap(root);
    }

    /**
     * This method is encapsulated by inorder() because it can be called without
     * parameters. Prints the Inorder Traversal of the tree.
     *
     * @param newRoot the current root
     */
    public void inorderEncap(NodeRB newRoot) {
        if (newRoot != null) {
            inorderEncap(newRoot.left);
            System.out.print(newRoot.value + " ");
            inorderEncap(newRoot.right);
        }
    }

    /**
     * Inserts value to the tree by running the insertEncap method
     *
     * @param val the value to be entered in the tree
     * @param color the color of node
     */
    public void insert(int val, String color) {
        root = insertEncap(root, val, color);
    }

    /**
     * THis method is encapsulated by insert() because it has an extra parameter
     * to run with. Inserts the value to the tree
     *
     * @param newRoot Current parent or regular root
     * @param val the value to be stored in the root
     * @param color the color of node
     * @return The new value or the same value of the Node
     */
    public NodeRB insertEncap(NodeRB newRoot, int val, String color) {

        if (newRoot == null) {
            newRoot = new NodeRB(val, color);
            return newRoot;
        }
        //if value given is greater than the root value then insert right
        if (val > newRoot.value) {
            newRoot.right = insertEncap(newRoot.right, val, color);
            //if value given is less than the root value then insert left
        } else if (val < newRoot.value) {
            newRoot.left = insertEncap(newRoot.left, val, color);
        }
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }
    
    /**
     * Checks if node is red
     * @param node check if red node
     * @return true if red
     */
    public boolean isRed(NodeRB node){
        if(node == null){
            return false;
        }
        return "red".equals(node.color.color);
    }
    
    /**
     * checks height
     * @param node height at this node
     * @return the integer value of height of node
     */
    public int getHeight(NodeRB node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    /**
     * Checks if root black then runs encap
     * @return if red black tree or not
     */
    public boolean isRedAndBlack() {
        if ((!"black".equals(root.color.color))) {
            return false;
        }
        return isRedAndBlackEncap(root);
    }
    
    /**
     * Checks if two red nodes exist consecutavly 
     * @param newRoot the node at which the checking is going on
     * @return  if red black tree or not
     */
    public boolean isRedAndBlackEncap(NodeRB newRoot) {
    
            if (newRoot == null){
                return true;
            }
            if (isRed(newRoot)) {
                if(isRed(newRoot.right) || isRed(newRoot.left)){
                return false;
                } 
            } else {
                return isRedAndBlackEncap(newRoot.left) &&
                isRedAndBlackEncap(newRoot.right);
                
            }
        
        return true;
    }
    
    
}

//NodeRB is same as Node, in order to not get duplicate error had to change name
class NodeRB {

    //Fields
    int value;
    int height;
    NodeRB left;
    NodeRB right;
    Color color;

    /**
     * Constructor
     *
     * @param value the value to be put in the Node
     */
    public NodeRB(int value, String color) {
        this.value = value;
        this.height = 1;
        this.color = new Color(color);
        left = null;
        right = null;
    }
}

class Color {

    String color;

    public Color(String color) {
        this.color = color;

    }

    @Override
    public String toString() {
        return "" + color;
    }

}

public class RedAndBlackTreeCheck {

    /**
     * Tests the BinarySearchTree
     *
     * @param args none
     */
    public static void main(String[] args) {

        BinarySearchTreeRB BST1 = new BinarySearchTreeRB();
        
        BST1.insert(30, "black");
        BST1.insert(20, "black");
        BST1.insert(40, "black");
        BST1.insert(10, "red");
        BST1.insert(25, "red");
        BST1.insert(50, "red");
        
        System.out.println(BST1.isRedAndBlack());
        
        BinarySearchTreeRB BST2 = new BinarySearchTreeRB();
        
        BST2.insert(30, "black");
        BST2.insert(20, "black");
        BST2.insert(40, "black");
        BST2.insert(10, "red");
        BST2.insert(25, "red");
        BST2.insert(50, "red");
        BST2.insert(60, "red");
               
        System.out.println(BST2.isRedAndBlack());

    }
}
