
/**
 * This class creates a Heap representation as a Max Heap and prints sorted data.
 * 
 * @author WahabEhsan
 */
public class HeapSort{
    
    //Feilds 
    int[] heap;
    int currentSize;
    
    private static final int DEFAULT_CAPACITY = 10; //Defualt size of array
    
    /**
     * Constructor for the class
     */
    public HeapSort(){
        this.heap = new int[DEFAULT_CAPACITY];
        currentSize = 0;
        
    }
    
    /**
     * Inserts value to the heap and percolates accordingly
     * @param val value to be inserted
     */
    public void insert(int val){
        if(currentSize == heap.length - 1){
            resize(heap.length * 2);
        }
        
        //Perolate up
        int hole = ++currentSize;
        for( ; hole > 1 && val > heap[hole / 2]; hole /= 2){
            heap[hole] = heap[hole/2];
        }
        heap[hole] = val;
        
    }
    
    /**
     * Resizes the array if small
     * @param newSize
     */
    public void resize(int newSize){
        int[] newArr = new int[newSize];
        for(int i = 0; i < heap.length;i++){
            newArr[i] = heap[i];
        }
    }
    
    /**
     * Checks if empty
     * @return
     */
    public boolean isEmpty(){
        return heap.length == 0;
    }
    
    /**
     * Deletes the max item and runs the percolate Down method
     * @param maxItem the max value 
     * @return the Max value
     */
    public int deleteMax(int maxItem){
        
        maxItem = heap[1];
        heap[1] = heap[currentSize--]; //sets the root to the element at the bottom
        percolateDown(1);
        return maxItem;
    } 
    
    /**
     * Percolates down the tree so its valid maxHeap
     * @param hole the empty node that is being percolated 
     */
    public void percolateDown(int hole){
        int child;
        Comparable tmp = heap[hole];
        
        for( ; hole * 2 <= currentSize; hole = child){
            child = hole * 2;
            if(child !=currentSize && heap[child + 1] > heap[child]){
                child++;
            }
            if(heap[child] > (int) tmp){
                heap[hole] = heap[child];
            } else {
                break;
            }
        }
        heap[hole] = (int) tmp;
    }
    
    /**
     * Prints the Heap in a sorted way.
     */
    public void printHeapSort(){
        int numberOfSizeBeginnging = currentSize;
        for(int i = 0; i < numberOfSizeBeginnging; i++){
            System.out.println(deleteMax(0));
        }
    }
    
    /**
     * Test the HeapSort by inserting given values and printing them.
     * @param args
     */
    public static void main(String [] args){
        HeapSort Heap = new HeapSort();
        
        Heap.insert(45);
        Heap.insert(90);
        Heap.insert(85);
        Heap.insert(62);
        System.out.println("The Sorted Heap : ");
        Heap.printHeapSort();
        
        
    }
}
