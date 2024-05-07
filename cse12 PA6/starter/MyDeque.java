/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains all the methods for the MyDeque class.
 */

/**
 * This class has all the MyDeque methods.
 */
public class MyDeque<E> implements DequeInterface<E>{
    //Instance Variables
    Object[] data;
    int size;
    int rear;
    int front;

    //Magic numbers
    private static final int ZERO_INT = 0;
    private static final int DEFAULT_EXPANSION = 10;
    private static final int ONE_INT = 1;
    private static final int DOUBLE_INT = 2;

    /**
     * This is the consturcotr of the class
     * @param initialCapacity the required capacity of the deque.
     */
    public MyDeque(int initialCapacity){
        if (initialCapacity < ZERO_INT){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = ZERO_INT;
        front = ZERO_INT;
        rear = ZERO_INT;
    }

    /**
     * Returns the number of non-null elements of the deque.
     * @return the size
     */
    public int size(){
        return size;
    }

    /**
     * Expands the size of the deque.
     */
    public void expandCapacity(){
        if (data.length == ZERO_INT){ 
            data = new Object[DEFAULT_EXPANSION]; 
            return;
        }
        int current_length = data.length;
        Object[] temporary_data = new Object[DOUBLE_INT*current_length];
        for (int i = ZERO_INT; i < data.length; i++){
            if (front+i <= data.length-ONE_INT){
                temporary_data[i] = data[front+i];
            }
            else {
                temporary_data[i] = data[Math.abs(i-front+ONE_INT)];
            }
        }
        data = temporary_data;
        front = ZERO_INT;
        if (size-ONE_INT <ZERO_INT){
            rear = ZERO_INT;
        }
        else{
            rear = size-ONE_INT;
        }
    }

    /**
     * Adds an element to the front of the deque
     * @param element element to be added
     */
    public void addFirst(E element){
        if (element == null){
            throw new NullPointerException();
        }
        if (size == data.length){
            this.expandCapacity();
        }
        Object temp_data_value = data[front]; 
        int new_front_index = front;
        
        while (temp_data_value != null){
            if (new_front_index-ONE_INT >= ZERO_INT){
                new_front_index--;
            }
            else {
                new_front_index = data.length-ONE_INT;
            }
            temp_data_value = data[new_front_index];
        }
        data[new_front_index] = element;
        front = new_front_index;
        size++;
        return;
    }

    /**
     * Adds an element to the rear end of the deque.
     * @param element is the element to be added.
     */
    public void addLast(E element){
        if (element == null){
            throw new NullPointerException();
        }
        if (size == data.length){
            this.expandCapacity();
        }
        Object temp_data_value = data[rear];
        int new_rear_index = rear;
        while (temp_data_value != null){
            if (new_rear_index+ONE_INT <= data.length-ONE_INT){
                new_rear_index++;
            }
            else {
                new_rear_index = ZERO_INT;
            }
            temp_data_value = data[new_rear_index];
        }
        data[new_rear_index] = element;
        rear = new_rear_index;
        size++;
        return;
    }

    /**
     * Removes the first element of the deque.
     * @return the element removed.
     */
    public E removeFirst(){
        if (size == ZERO_INT){
            return null;
        }
        E return_value = (E)data[front]; 
        data[front] = null;
        if (size != ONE_INT){
            if ((front+ONE_INT) < data.length){
                front++;
            }
            else{
                front = ZERO_INT;
            }
        }
        size--;
        
        return return_value;
    }

    /**
     * Removes the last element of the deque
     * @return the element removed.
     */
    public E removeLast(){
        if (size == ZERO_INT){
            return null;
        }
        E return_value = (E)data[rear];
        data[rear] = null;
        if (size != ONE_INT){
            if ((rear-ONE_INT) >= ZERO_INT){
                rear--;
            }
            else{
                rear = data.length-ONE_INT;
            }
        }
        size--;
        return return_value;
    }

    /**
     * returns the element at the front of the deque
     * @return the element at the front of deque.
     */
    public E peekFirst(){
        if (size == ZERO_INT){
            return null;
        }
        return (E) data[front];
    }

    public E peekLast(){
        if (size == ZERO_INT){
            return null;
        }
        return (E) data[rear];
    }
}
