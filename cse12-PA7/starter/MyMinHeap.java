/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains all the methods of the MyMinHeap class
 */

//Import
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class consists of the MyMinHeap methods which includes
 * all the methods of building a MinHeap.
 */
public class MyMinHeap<E extends Comparable<E>> 
    implements MinHeapInterface<E>{
    //Instance Variable
    protected ArrayList<E> data;

    //Magic numbers
    private static final int ZERO_INT = 0;
    private static final int ONE_INT = 1;
    private static final int TWO_INT = 2;
    /**
     * The constructor for the class without arguments.
     */
    public MyMinHeap(){
        data = new ArrayList<>();
    }

    /**
     * The constructor for the class with an argument collection
     * @param collection is the collection from which data is used.
     */
    public MyMinHeap(Collection<? extends E> collection){
        if (collection == null || collection.contains(null)){
            throw new NullPointerException();
        }
        
        data = new ArrayList<>(collection); 
        for (int i = data.size()-ONE_INT; i >=ZERO_INT; i--){
            this.percolateDown(i);
        }
    }

    /**
     * This method swaps the elements in two indexes.
     * @param from one of the indexes
     * @param to the other index
     */

    protected void swap(int from, int to){ 
        E temp_data_swapped = data.get(from); 
        data.remove(from); 
        data.add(from, data.get(to-ONE_INT)); 
        data.remove(to); 
        data.add(to, temp_data_swapped); 
    }

    /**
     * This method returns the parent index of another argument index.
     * @param index is the index of which parent is returned.
     * @return the index of the parent node.
     */

    protected static int getParentIdx(int index){
        int parent_index;
        if (index % TWO_INT == ZERO_INT) {
            parent_index = (index/TWO_INT) - ONE_INT;
        }
        else {
            parent_index = index/TWO_INT;
        }
        return parent_index;
    }

    /**
     * This method returns the index of left child of a spedified node.
     * @param index the index of the specified node
     * @return the index of the the left child
     */
    protected static int getLeftChildIdx(int index){
        return index*TWO_INT +ONE_INT;
    }

    /**
     * This method returns the index of the right child of a specified node.
     * @param index the index of the specified node.
     * @return the ingex of the right child node.
     */
    protected static int getRightChildIdx(int index){
        return getLeftChildIdx(index) + ONE_INT;
    }

    /**
     * This method returns the index of smaller of the children 
     * of a specified parent.
     * @param index the index of a parent
     * @return the index of the smaller child.
     */
    protected int getMinChildIdx(int index){
        if (getLeftChildIdx(index) >= data.size()){
            return -ONE_INT;
        }
        if (getRightChildIdx(index) >= data.size()){
            return getLeftChildIdx(index);
        }
        int left_child_index = getLeftChildIdx(index);
        int right_child_index = getRightChildIdx(index);
        if (data.get(left_child_index).equals(data.get(right_child_index))){
            return left_child_index;
        }
        if (data.get(left_child_index).compareTo
            (data.get(right_child_index)) < ZERO_INT){
            return left_child_index;
        }
        else{
            return right_child_index;
        }

    }
    
    /**
     * This method rearranges the heap, moving a node up
     * @param index the index of the node being moved.
     */

    protected void percolateUp(int index){
        if (getParentIdx(index)<ZERO_INT){
            return;
        }
        if (data.get(index).compareTo
            (data.get(getParentIdx(index))) < ZERO_INT){
            int parent_index = getParentIdx(index);
            this.swap(getParentIdx(index), index);
            percolateUp(parent_index);
        }
    }

    /**
     * This method rearranges the heap, moving a node down.
     * @param index the index of the node being moved down.
     */
    protected void percolateDown(int index){ 
        if (getMinChildIdx(index) >= data.size() || 
            getMinChildIdx(index) <ZERO_INT){
            return;
        }
        if (data.get(index).compareTo
            (data.get(getMinChildIdx(index))) > ZERO_INT){
            int minChild_index = getMinChildIdx(index);
            this.swap(index, getMinChildIdx(index));
            percolateDown(minChild_index);
        }
    }

    /**
     * This method deletes a node from heap.
     * @param index index of the node being deleted.
     * @return returns the element being deleted.
     */
    protected E deleteIndex(int index){
        E return_element = data.get(index);
        if (index == data.size()-ONE_INT){
            data.remove(index);
            return return_element;
        }
        this.swap(index, data.size()-ONE_INT);
        data.remove(data.size()-ONE_INT);
        if (index != ZERO_INT) {
            percolateUp(index);
        }
        if (index != size()-ONE_INT) {
            percolateDown(index);
        }
        return return_element;
    }

    /**
     * This method inserts an element into the heap.
     * @param element is the elemtn being inserted.
     */
    public void insert(E element){
        if (element.equals(null)){
            throw new NullPointerException();
        }
        data.add(element);
        this.percolateUp(data.size()-ONE_INT);
        return;
    }

    /**
     * This method returns the root of the heap.
     * @return the root of the heap.
     */
    public E getMin(){
        if (data.size() == ZERO_INT){
            return null;
        }
        return data.get(ZERO_INT);
    }

    /**
     * This method removes the root of the heap.
     * @return the root element of the heap.
     */
    public E remove(){
        if (data.size() == ZERO_INT){
            return null;
        }
        return this.deleteIndex(ZERO_INT);
    }

    /**
     * This method returns the size of heap
     * @return the size of heap.
     */
    public int size(){
        return data.size();
    }

    /**
     * This method clears the entire heap.
     */
    public void clear(){
        data.removeAll(data);
    }

}
