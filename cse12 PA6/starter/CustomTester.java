/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains all the tests for the MyQueue, MyStack, MyDeque, 
 * and MyAlgorithm classes.
 */

 import org.junit.Test;
 import static org.junit.Assert.*;
 
 /**
  * Custom tests for MyDeque, MyQueue, and MyStack.
  */
 public class CustomTester {
     /**
      * Helper method to initialize all instance variables of MyDeque
      * @param deque The deque to initialize
      * @param data The data array
      * @param size The value for size
      * @param front The value for front
      * @param rear The value for rear
      */
     static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                           int front, int rear) {
         deque.data = data;
         deque.size = size;
         deque.front = front;
         deque.rear = rear;
     }
 
     /** 
      * Test the Deque constructor, for bad implementation
      */
     @Test 
     public void testDequeConstructor() {
        boolean exception_test = false;
        try{
            MyDeque failDeque = new MyDeque<>(-20);
        }
        catch (IllegalArgumentException e){
            exception_test = true;
        }
        assertEquals(true, exception_test);
     }
 
     /** 
      * Test expandCapacity with no capacity 
      */
     @Test
     public void testExpandCapacityEmpty() {
         MyDeque deque_expand = new MyDeque<>(0);
         deque_expand.expandCapacity();
         Integer[] finalOrdering = {null, null, null, null, null, 
             null, null, null, null, null};
         assertArrayEquals(finalOrdering, deque_expand.data);
         assertEquals(0, deque_expand.size());
         assertEquals(0, deque_expand.front);
         assertEquals(0, deque_expand.rear);
     }
 
     /** 
      * Test expandCapacity with no capacity 
      */
     @Test
     public void testExpandCapacityNull() {
         MyDeque expand = new MyDeque<>(5);
         expand.expandCapacity();
         Integer[] finalOrdering = {null, null, null, null, null, 
             null, null, null, null, null};
         assertArrayEquals(finalOrdering, expand.data);
         assertEquals(0, expand.size());
         assertEquals(0, expand.front);
         assertEquals(0, expand.rear);
     }
 
     /** 
      * Test expandCapacity with front not at 0 
      */
     @Test
     public void testExpandCapacity() {
         MyDeque<Integer> deque = new MyDeque<>(5);
         Integer[] orig = {3, 4, 5, 1, 2};
         Integer[] finalOrdering = {1, 2, 3, 4, 5, null, null, null,
             null, null};
         initDeque(deque, orig, 5, 3, 2);
 
         deque.expandCapacity();
         assertEquals("Capacity should have doubled", 10, deque.data.length);
         assertEquals("Size should not have changed", 5, deque.size);
         assertEquals("Front should be 0", 0, deque.front);
         assertEquals("Rear should be 3", 4, deque.rear);
         assertArrayEquals(deque.data, finalOrdering);
     }
 
     /**
      * Test addFirst with invalid element
      */
     @Test (expected = NullPointerException.class)
     public void testAddFirstNull() {
         MyDeque<Integer> deque = new MyDeque<>(10);
         deque.addFirst(null);
     }
 
     /**
      * Test addFirst to deque at full capacity
      */
     @Test
     public void testAddFirstFullCapacity() {
         MyDeque<Integer> deque = new MyDeque<>(3);
         Integer[] orig = {4, 5, 6};
         Integer[] finalOrdering = {4, 5, 6, null, null, 3};
         initDeque(deque, orig, 3, 0, 2);
         deque.addFirst(3);
 
         assertEquals(6, deque.data.length);
         assertEquals(4, deque.size);
         assertEquals(5, deque.front);
         assertEquals(2, deque.rear);
         assertArrayEquals(finalOrdering, deque.data);
     }
 
     /**
      * Test addLast with invalid element
      */
     @Test (expected = NullPointerException.class)
     public void testAddLastNull() {
         MyDeque<Integer> deque = new MyDeque<>(10);
         deque.addLast(null);
     }
 
     /**
      * Test addLast to deque at full capacity
      */
     @Test
     public void testAddLastFullCapacity() {
         MyDeque<Integer> deque = new MyDeque<>(3);
         Integer[] orig = {4, 5, 6};
         Integer[] finalOrdering = {4, 5, 6, 3, null, null};
         initDeque(deque, orig, 3, 0, 2);
         deque.addLast(3);
 
         assertEquals(6, deque.data.length);
         assertEquals(4, deque.size);
         assertEquals(0, deque.front);
         assertEquals(3, deque.rear);
         assertArrayEquals(finalOrdering, deque.data);
     }
 
    /**
     * Test removeFirst from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {3, 4, 1, null, 2};
        Integer[] finalOrdering = {3, 4, 1, null, null};
        initDeque(deque, orig, 4, 4, 2);
        assertEquals(2, deque.removeFirst().intValue());
        assertEquals(5, deque.data.length);
        assertEquals(3, deque.size);
        assertEquals(0, deque.front);
        assertEquals(2, deque.rear);
        assertArrayEquals(finalOrdering, deque.data);
        assertEquals(3, deque.removeFirst().intValue());
        assertEquals(4, deque.removeFirst().intValue());
        assertEquals(1, deque.removeFirst().intValue());
        assertEquals(0, deque.size());
        assertEquals(2, deque.front);
        assertEquals(2, deque.rear);
    }

    /**
     * Test removeFirst when size is null
     */
    @Test
    public void testRemoveFirstNull() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertEquals(null, deque.removeFirst());
    }

    /**
     * Test removeLast from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {3, 4, 1, null, 2};
        Integer[] finalOrdering = {3, 4, null, null, 2};
        initDeque(deque, orig, 4, 4, 2);
        assertEquals(1, deque.removeLast().intValue());
        assertEquals(5, deque.data.length);
        assertEquals(3, deque.size);
        assertEquals(4, deque.front);
        assertEquals(1, deque.rear);
        assertArrayEquals(finalOrdering, deque.data);

        assertEquals(4, deque.removeLast().intValue());
        assertEquals(3, deque.removeLast().intValue());
        assertEquals(2, deque.removeLast().intValue());
        assertEquals(0, deque.size());
        assertEquals(4, deque.front);
        assertEquals(4, deque.rear);
    }

    /**
     * Test removeLast when size is null
     */
    @Test
    public void testRemoveLastNull() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertEquals(null, deque.removeLast());
    }

    /**
     * Test peekLast for empty deque
     */
    @Test
    public void testPeekLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertEquals(null, deque.peekLast());
    }

    /**
     * Test peekLast for empty deque
     */
    @Test
    public void testPeekFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertEquals(null, deque.peekFirst());
    }

    // ------------ Stack ---------------

    /**
     * Tests various methods for MyStack
     */
    @Test
    public void testStack() {
        MyStack<Integer> stack = new MyStack<>(5);
        Integer[] orig = {1, 2, 3, null, null};
        Integer[] finalOriginal = {1, 2, 3, null, 0};
        initDeque(stack.theStack, orig, 3, 0, 2);

        stack.push(0);
        assertEquals(0, stack.peek().intValue());
        assertArrayEquals(finalOriginal, stack.theStack.data);
        assertEquals(4, stack.size());

        stack.push(4);
        assertEquals(4, stack.peek().intValue());
        assertArrayEquals(new Integer[] {1, 2, 3, 4, 0}, stack.theStack.data);
        assertEquals(5, stack.size());

        assertEquals(4, stack.pop().intValue());
        assertEquals(0, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
        assertEquals(2, stack.pop().intValue());
        assertEquals(3, stack.pop().intValue());

        assertTrue(stack.empty());
    }

    /**
     * Tests various methods for MyStack
     */

    @Test
    public void testStack2() {
        MyStack<Integer> stack = new MyStack<>(5);
        Integer[] orig = {null, null, null, null, null};
        initDeque(stack.theStack, orig, 0, 0, 0);
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(5, stack.theStack.size());
        assertEquals(4, stack.pop().intValue());
        assertEquals(3, stack.pop().intValue());
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
        assertEquals(0, stack.pop().intValue());

        assertEquals(0, stack.theStack.size());
        assertTrue(stack.empty());
    }

    // ------------ Queue ---------------

    /**
     * Tests various methods for MyQueue
     */
    @Test
    public void testQueue() {
        MyQueue<Integer> queue = new MyQueue<>(5);
        Integer[] orig = {1, 2, 3, null, null};
        Integer[] finalOriginal = {1, 2, 3, null, 0};
        initDeque(queue.theQueue, orig, 3, 0, 2);

        queue.enqueue(0);
        assertEquals(3, queue.peek().intValue());
        assertArrayEquals(finalOriginal, queue.theQueue.data);
        assertEquals(4, queue.size());

        queue.dequeue();
        assertEquals(2, queue.peek().intValue());
        assertArrayEquals(new Integer[] {1, 2, null, null, 0},
        queue.theQueue.data);
        assertEquals(3, queue.size());

        queue.dequeue();
        assertEquals(1, queue.peek().intValue());
        assertArrayEquals(new Integer[] {1, null, null, null, 0},
        queue.theQueue.data);
        assertEquals(2, queue.size());

        queue.enqueue(2);
        assertEquals(1, queue.peek().intValue());
        assertArrayEquals(new Integer[] {1, null, null, 2, 0},
        queue.theQueue.data);
        assertEquals(3, queue.size());

        assertFalse(queue.empty());
    }

    /**
     * Tests for the Myalgorithm class's methods.
     */
    @Test
    public void testAlgorithm() {
        int[] arr = new int[]{1, 2, 3};
        double EPSILON = 1.0E-4;
        assertEquals("Subsequence should not change from arr", 1, 
            MyAlgorithm.avgDiffMonotonicIncreasing(arr), EPSILON);
        int[] arr1 = new int[]{4, 1, 3, 5, 7};
        assertEquals("Subsequence should not change from arr", 1.5, 
            MyAlgorithm.avgDiffMonotonicIncreasing(arr1), EPSILON);
        int[] arr3 = new int[]{4, 3, 2, 1, 4, 3, 3, 3};
        assertEquals("Subsequence should not change from arr", 0, 
            MyAlgorithm.avgDiffMonotonicIncreasing(arr3), EPSILON);
        int[] arr4 = new int[]{1};
        assertEquals("Subsequence should not change from arr", 0, 
            MyAlgorithm.avgDiffMonotonicIncreasing(arr4), EPSILON);
        int[] arr5 = new int[]{};
        assertEquals("Subsequence should not change from arr", 0, 
            MyAlgorithm.avgDiffMonotonicIncreasing(arr5), EPSILON);
        
        int[] arr6 = new int[]{1,2,3,4,5};
        assertEquals("Subsequence should not change from arr", 1, 
            MyAlgorithm.avgDiffMonotonicIncreasing(arr6), EPSILON);
    }
}
 

         
