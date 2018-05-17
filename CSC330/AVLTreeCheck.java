
/**
 * Java Implementation of BinarySearchTree and Checks if AVLTree
 *
 * @author WahabEhsan
 */
class BinarySearchTreeAVL {

    //Private fields
    private Node root;

    //Default Constructor
    public BinarySearchTreeAVL() {
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
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    /**
     * This Method returns the height of the node
     *
     * @param node the height of the node given
     * @return the integer value of height of node
     */
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * This method returns the balance factor by subtracting the left node
     * height and right node height.
     *
     * @param node the node provided to find balance factor
     * @return the integer value of balance factor of the node
     */
    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Runs the isAVLEncap to check if tree is AVL
     *
     * @return returns true or false if AVL or not
     */
    public boolean isAVL() {
        return isAVLEncap(root);
    }

    /**
     * Encapsulation of isAVL so no parameters required, Searches through tree
     * if AVL
     *
     * @param newRoot the node that is being checked for balance factor
     * @return boolean value if AVL or not
     */
    public boolean isAVLEncap(Node newRoot) {
        while (newRoot != null) {
            int balance = getBalanceFactor(newRoot);
            if (balance > 1 || balance < -1) {
                return false;
            } else {
                isAVLEncap(newRoot.left);
                isAVLEncap(newRoot.right);
                return true;
            }
        }
        return true;
    }
}

class Node {

    //Fields
    int value;
    int height;
    Node left;
    Node right;

    /**
     * Constructor
     *
     * @param value the value to be put in the Node
     */
    public Node(int value) {
        this.value = value;
        this.height = 1;
        left = null;
        right = null;
    }
}

public class AVLTreeCheck {

    /**
     * Tests the BinarySearchTree
     *
     * @param args none
     */
    public static void main(String[] args) {

        BinarySearchTreeAVL BST1 = new BinarySearchTreeAVL();

        BST1.insert(30);
        BST1.insert(20);
        BST1.insert(40);
        BST1.insert(10);
        BST1.insert(25);
        BST1.insert(50);

        System.out.println(BST1.isAVL());

        BinarySearchTreeAVL BST2 = new BinarySearchTreeAVL();

        BST2.insert(30);
        BST2.insert(20);
        BST2.insert(40);
        BST2.insert(10);
        BST2.insert(25);
        BST2.insert(50);
        BST2.insert(70);
        BST2.insert(80);

        System.out.println(BST2.isAVL());
    }

}
