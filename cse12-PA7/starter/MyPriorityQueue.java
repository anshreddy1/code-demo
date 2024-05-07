/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains all the methods of the MyPriorityQueue class
 */

//Imported 
import java.util.Collection;

/**
 * This class consists of the methods that implement the min-heap into a
 * priority queue.
 */
public class MyPriorityQueue<E extends Comparable<E>> {
    //Instance Variable
    protected MyMinHeap<E> heap;

    /**
     * This is the constructor for the class without arguments.
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * This is the constructor for the class with arguments.
     * @param collection is the collection with the data.
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    /**
     * This method inserts an element into the priority queue.
     * @param element is the element being inserted.
     */
    public void push(E element){
        if (element == null){
            throw new NullPointerException();
        }
        heap.insert(element);
    }

    /**
     * This method returns the element at the top of the priority queue.
     * @return the element at the top.
     */
    public E peek(){
        if (heap == null){
            return null;
        }
        return heap.getMin();
    }

    /**
     * This method removes and returns the element at the
     * top of the priority queue.
     * @return the element at the top.
     */
    public E pop(){
        if (heap == null){
            return null;
        }
        return heap.remove();
    }

    /**
     * Returns the size of the priority queue.
     * @return the size of the priority queue.
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * This method fully clears the priority queue completely.
     */
    public void clear(){
        heap.clear();
    }

}
