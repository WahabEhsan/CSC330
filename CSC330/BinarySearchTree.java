
/**
 * Java Implementation of BinarySearchTree
 * 
 * @author WahabEhsan
 */
class BinarySearchTree {

    //Private fields
    private Node root;

    //Default Constructor
    public BinarySearchTree() {
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
    public void inorderEncap(Node newRoot) {
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
     */
    public void insert(int val) {
        root = insertEncap(root, val);
    }

    /**
     * THis method is encapsulated by insert() because it has an extra parameter
     * to run with. Inserts the value to the tree
     *
     * @param newRoot Current parent or regular root
     * @param val the value to be stored in the root
     * @return The new value or the same value of the Node
     */
    public Node insertEncap(Node newRoot, int val) {

        if (newRoot == null) {
            newRoot = new Node(val);
            return newRoot;
        }
        //if value given is greater than the root value then insert right
        if (val > newRoot.value) {
            newRoot.right = insertEncap(newRoot.right, val);
            //if value given is less than the root value then insert left
        } else if (val < newRoot.value) {
            newRoot.left = insertEncap(newRoot.left, val);
        }
        return newRoot;
    }
    /**
     * Tests the BinarySearchTree
     * @param args none
     */
    public static void main(String [] args){
    
       BinarySearchTree BST = new BinarySearchTree();
        
       BST.insert(45);
       BST.insert(30);
       BST.insert(12);
       BST.insert(89);
       
       BST.inorder();   
    }
}
