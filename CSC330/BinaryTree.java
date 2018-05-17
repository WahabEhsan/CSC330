
/**
 * Java implementation of BinaryTree
 *
 * @author WahabEhsan
 */
class BinaryTree {

    //Private fields 
    private Node root;

    //Default Constructor
    public BinaryTree() {
        this.root = null;
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
        //if left and right are empty then insert in left
        if (newRoot.left == null && newRoot.right == null) {
            newRoot.left = insertEncap(newRoot.left, val);
            //if left is not empty but right is then insert in right
        } else if (newRoot.left != null && newRoot.right == null) {
            newRoot.right = insertEncap(newRoot.right, val);
            //else insert in the left child
        } else {
            newRoot.left = insertEncap(newRoot.left, val);
        }
        return newRoot;
    }
    /**
     * Tests the BinaryTree
     * @param args none
     */
    public static void main(String [] args){
    
       BinaryTree BT = new BinaryTree();
        
       BT.insert(45);
       BT.insert(30);
       BT.insert(12);
       BT.insert(89);
       
       BT.inorder();   
    }
}
