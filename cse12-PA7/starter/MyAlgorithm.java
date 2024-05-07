/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains all the methods of the MyAlgorithm class
 */

//IMPORT
import java.util.ArrayList;

/**
 * This class consitst the method for the lastAtom algorithm
 */
public class MyAlgorithm {
    //MAGIC NUMBERS
    private static final int ONE_INT = 1;
    private static final int TWO_INT = 2;
    /**
     * This method finds the atom reactions and returns the final atom value.
     * @param atoms the ArrayList of all atomic values.
     * @return the final atomic value after all reactions.
     */
    public static Integer lastAtom (ArrayList<Integer> atoms){
        if (atoms == null){
            throw new NullPointerException();
        }
        MyPriorityQueue<Integer> lastAtomHeap = new MyPriorityQueue<>(atoms);
        
        while (lastAtomHeap.getLength() > ONE_INT){ 
            Integer first_atom_value = lastAtomHeap.pop(); 
            Integer second_atom_value = lastAtomHeap.pop();
            Integer new_Atom;
            if (first_atom_value.equals(second_atom_value)){ 
                new_Atom = first_atom_value*TWO_INT;
            }
            else{
                new_Atom = Math.abs(second_atom_value-first_atom_value);
            }
            lastAtomHeap.push(new_Atom);
        }
        return lastAtomHeap.pop();
    }
}
