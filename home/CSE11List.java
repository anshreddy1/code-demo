




public interface CSE11List<E> {
    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in the list.
     */
    int size();

    /**
     * Returns the element at the specified position in the list.
     * 
     * @param index index of the element to return
     * @return the element at the specified position in the list.
     * @exception IndexOutofBoundsException if the index is out of range (index < 0
     *                                      || index >= size())
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list
     * with the specified element.
     * 
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @exception IndexOutofBoundsException if the index is out of range (index < 0
     *                                      || index >= size())
     */
    E set(int index, E element);

    /**
     * Appends the specified element to the end of the list.
     * 
     * @param e The element to be added.
     */
    void add(E e);

    /**
     * Inserts the specified element at the specified position in the list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     * 
     * @param index   index at which the specified element is to be inserted.
     * @param element element to be inserted
     * @exception IndexOutofBoundsException if the index is out of range (index < 0
     *                                      || index > size())
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in the list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * 
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @exception IndexOutofBoundsException if the index is out of range (index < 0
     *                                      || index >= size())
     */
    E remove(int index);

    /**
     * Removes all of the elements from this list. The list will be empty
     * after this call returns.
     */
    void clear();

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     * 
     * @param o element to search for
     * @return the index of the first occurrence of the specified element
     *         in this list, or -1 if this list does not contain the element.
     */
    int indexOf(Object o);

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element.
     * 
     * @param o element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns a new list consisting of a portion of this list between the
     * specified fromIndex (inclusive), and toIndex (exclusive). If fromIndex
     * and the returned list is empty. The elements in the sublist and the
     * original list refer to the same objects, so modifications to objects
     * stored in this list will also be reflected in objects in the sublist.
     * 
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a new list made up of the specified range within this list
     * @exception IndexOutOfBoundsException if an endpoint index value is out of
     *                                      range (fromIndex < 0 || toIndex > size)
     * @exception IllegalArgumentException  if the endpoint indices are out of order
     *                                      (fromIndex > toIndex)
     */
    public CSE11List<E> subList(int fromIndex, int toIndex);
}
