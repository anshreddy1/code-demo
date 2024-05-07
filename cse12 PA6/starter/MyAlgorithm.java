/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains all the methods for the MyAlgorithm class.
 */

/**
 * This class contains an algorithm utilizing a stack or queue. 
 */
public class MyAlgorithm {
    /**
     * Finds the average difference between consecutive elements in the
     * monotonically increasing subsequence starting from the first element in
     * the array
     * 
     * @param arr the array containing the monotonic subsequence
     * @throws NullPointerException if the specified array is null
     * @return the average difference between elements in the subsequence
     */
    
     private static final int ZERO_INT = 0;
     private static final int ONE_INT = 0;
     private static final double ZERO_DOUBLE = 0.0;
    public static double avgDiffMonotonicIncreasing(int[] arr) { 
        if (arr == null){
            throw new NullPointerException();
        }
        if (arr.length <= ONE_INT) {
            return ZERO_INT; 
        }
        MyStack<Integer> newStack = new MyStack<>(arr.length);
        
        for (int i = ZERO_INT; i < arr.length; i++) {
            if (newStack.empty() || arr[i] > newStack.peek()) {
                newStack.push(arr[i]);
            }
        }
        if (newStack.size() == ZERO_INT || newStack.size() == ONE_INT){
            return ZERO_DOUBLE;
        }
        double sum_of_differences = ZERO_DOUBLE;
        double stack_size = newStack.size();
        while (newStack.size() > ONE_INT){
            sum_of_differences = sum_of_differences + (newStack.pop()-newStack.peek());
        }
        return sum_of_differences/(stack_size-ONE_INT);

    }
}
