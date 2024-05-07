class HoorayList<E> implements CSE11List<E> {

    /* The initial size of elements */
    static final int INIT_ELEM_LEN = 10;

    /* The step size for elements expansion */
    static final int EXPANSION_STEP = 10;

    /*
     * The underlying data structure. The reason this array is of type
     * Object rather than the generic E is because Java does not allow
     * creating generic arrays.
     */
    private Object[] elements;

    /**
     * The size of the array list. This is different from the *capacity*
     * of the list, which is the length of the underlying array `elements`.
     */
    private int size;

    public HoorayList() {
        elements = new Object[INIT_ELEM_LEN];
    }

    public int size() {
        return size;    // TODO
    }

    private void expand() {
        Object[] elements2 = new Object[elements.length + EXPANSION_STEP];
        for (int i =0; i<elements.length; i++){
            elements2[i] = elements[i];
        }
        elements = elements2;
        return;      // TODO
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index<0 || index>=size()){
            throw new IndexOutOfBoundsException(index);
        }
        return (E)elements[index]; // TODO
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index<0 || index>=size()){
            throw new IndexOutOfBoundsException(index);
        }
        Object prev_elem = elements[index];
        elements[index] = element;
        return (E)prev_elem; // TODO
    }

    public void add(E e) {
        if (size == elements.length){
            expand();
        }
        elements[size] = e;
        size++;
        return;      // TODO
    }

    public void add(int index, E element) {
        if (index<0 || index>size()){
            throw new IndexOutOfBoundsException(index);
        }
        Object[] els = new Object[elements.length];
        
        if (size() == els.length){
            expand();
        }
        
        for(int i = index+1; i<els.length; i++){
            els[i] = elements[i-1]; 
        }
        for (int j = 0; j<index; j++){
            els[j] = elements[j];
        }
        els[index] = element;
        elements = els;
        size++;
        return;      // TODO
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException(index);
        }
        E e = (E)elements[index];
        Object[] els = new Object[elements.length];

        for(int i = index+1; i<els.length; i++){
            els[i-1] = elements[i]; 
        }
        for (int j = 0; j<index; j++){
            els[j] = elements[j];
        }
        elements = els;
        size--;
        return e;      // TODO
    }
    

    public void clear() {
        int x = size();
        for (int i = 0; i<x; i++){
            elements[i] = null;
        }
        size = 0;
        return;      // TODO
    }

    public int indexOf(Object o) {
        for (int i = 0; i<size(); i++){
            if (elements[i].equals(o)){
                return i;
            }
        }
        return -1;    // TODO
    }

    public boolean isEmpty() {
        if (size() == 0){
            return true;
        }
        return false; // TODO
    }

    public boolean contains(Object o) {
        if (indexOf(o) != -1){
            return true;
        }
        return false; // TODO
    }

    public CSE11List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0){
            throw new IndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > size){
            throw new IndexOutOfBoundsException(toIndex);
        }
        if (fromIndex > toIndex){
            throw new IllegalArgumentException();
        }
        CSE11List<E> sublist = new HoorayList<>();
        for (int i = fromIndex; i < toIndex; i++){
            sublist.add((E)elements[i]);
        }
        return sublist; // TODO
    }
}
