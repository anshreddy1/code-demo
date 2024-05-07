/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains custom methods for MyBST
 */
import java.util.ArrayList;

/**
 * This class consits of all the methods for MyBST.
 */
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * Returns the size of the BST
     * @return the size of the BST
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the BST
     * @param key the key of the element
     * @param value the value of the element
     * @return the value of the replaced value if key is the same.
     */
    public V insert(K key, V value) {
        
        if (key == null){
            throw new NullPointerException();
        }
        
        if (size == 0){
            MyBSTNode<K, V> new_node = new MyBSTNode<K, V>(key, value, null);
            this.root = new_node;
            size++;
            return null;
        }

        MyBSTNode<K, V> curr_node = root;
        while (true){
            if (curr_node.getKey().compareTo(key) == 0){
                V return_value = curr_node.getValue();
                curr_node.value = value;
                return return_value;
            }
            
            if (curr_node.getKey().compareTo(key) >0){
                if (curr_node.getLeft() == null){
                    MyBSTNode<K, V> new_node = 
                        new MyBSTNode<K, V>(key, value, curr_node);
                    curr_node.left = new_node;
                    size++;
                    return null;
                }
                curr_node = curr_node.getLeft();
            }
            else{
                if (curr_node.getRight() == null){
                    MyBSTNode<K, V> new_node = 
                        new MyBSTNode<K, V>(key, value, curr_node);
                    curr_node.right = new_node;
                    size++;
                    return null;
                }
                curr_node = curr_node.getRight();
            }
        }
        
    }

    /**
     * Searches for a certain element with a particular key
     * @param key the key being searched for
     * @return the value with the argument key.
     */
    public V search(K key) {
        // TODO
        MyBSTNode<K,V> curr_node = root;
        if (key == null){
            return null;
        }
        while (curr_node != null){
            if (curr_node.getKey().compareTo(key) == 0){
                return curr_node.getValue();
            }
            if (curr_node.getKey().compareTo(key) > 0){
                curr_node = curr_node.getLeft();
            }
            else {
                curr_node = curr_node.getRight();
            }
        }
        return null;
    }

    /**
     * Removes a certain element with a particular key
     * @param key the key of the element being removed
     * @return the value fo the element being removed.
     */
    public V remove(K key) {
        // TODO
        V return_value = this.search(key);
        if (return_value == null){
            return null;
        }
        MyBSTNode<K,V> curr_node = root;
    
        while (curr_node != null){
            if (curr_node.getKey().compareTo(key) == 0){
                if (curr_node.getLeft() == null && 
                    curr_node.getRight() == null){
                    if (curr_node.getParent() == null) {
                        root = null;
                    } else if (curr_node.getParent().left == curr_node) {
                        curr_node.getParent().left = null;
                    } else {
                        curr_node.getParent().right = null;
                    }
                    size--;
                    return return_value;
                }

                else if (curr_node.left != null && curr_node.right != null){
                    MyBSTNode<K,V> successor = curr_node.successor();
                    if (curr_node.getKey().compareTo(key) == 0){
                        this.root = successor;
                    }
                    if (successor.getParent() != null && 
                        successor.getParent().getRight() == successor){
                        successor.getParent().right = null;
                    }
                    else if (successor.getParent() != null && 
                        successor.getParent().getLeft() == successor) {
                        successor.getParent().left = null;
                    }
                    successor.right = curr_node.getRight();
                    successor.left = curr_node.getLeft();
                    successor.parent = curr_node.getParent();
                    curr_node.getRight().parent = successor;
                    curr_node.getLeft().parent = successor;
            
                    if (curr_node.getParent() != null && 
                        curr_node.getParent().getRight() == curr_node){
                        curr_node.getParent().right = successor;
                    }
                    else if (curr_node.getParent() != null && 
                        curr_node.getParent().getLeft() == curr_node) {
                        curr_node.getParent().left = successor;
                    }

                    curr_node = null;
                    size--;
                    return return_value;
                }
                else {
                    if (curr_node.getLeft() != null){
                        if (curr_node.getParent() == null) {
                            root = curr_node.getLeft();
                        } else if (curr_node.getParent().left == curr_node) {
                            curr_node.getParent().left = curr_node.getLeft();
                        } else {
                            curr_node.getParent().right = curr_node.getLeft();
                        }
                        curr_node.getLeft().parent = curr_node.getParent();
                        size--;
                        return return_value;
                    }
                    else{
                        if (curr_node.getParent() == null) {
                            root = curr_node.getRight();
                        } else if (curr_node.getParent().left == curr_node) {
                            curr_node.getParent().left = curr_node.getRight();
                        } else {
                            curr_node.getParent().right = curr_node.getRight();
                        }
                        curr_node.getLeft().parent = curr_node.getParent();
                        size--;
                        return return_value;
                    }
                }
            }
            else if (curr_node.getKey().compareTo(key) >0){
                curr_node = curr_node.getLeft();
            }
            else{
                curr_node = curr_node.getRight();
            }
            
        }
        return null;
    }

    /**
     * Generates an arraylist populated with elements from the BST
     * in inorder traversal.
     * @return the arraylist generated.
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K,V>> inorder_list = new ArrayList<>();
        MyBSTNode<K,V> next = root;
        if (size == 0 || root == null) {
            return inorder_list;
        }
        while(next.getLeft() != null) {
            next = next.getLeft();
        }
        while(next != null) {
            inorder_list.add(next);
            next = next.successor();
        }
        return inorder_list;
    }

    /**
     * Generates a a new BST that is an exact copy of the exisitng one.
     * @return the new copied BST.
     */
    public MyBST<K, V> copy() {
        MyBST<K, V> newTree = new MyBST<>();
        if (root != null) {
            newTree.root = copyHelper(root, null, newTree);
            newTree.size = size;
        }
        return newTree;
    }
    
    /**
     * This is a helper method for the copy method.
     * @param node The node being copied
     * @param parent the parent of the node being copied.
     * @param newTree the new copied BST.
     * @return Return the Node copied.
     */
    private MyBSTNode<K, V> copyHelper(MyBSTNode<K, V> node, 
        MyBSTNode<K, V> parent, MyBST<K, V> newTree) {
        if (node == null) {
            return null;
        }
    
        MyBSTNode<K, V> newNode = new MyBSTNode<>(node.getKey(), 
            node.getValue(), node.getParent());
        newNode.parent = parent;
        newNode.left = copyHelper(node.left, newNode, newTree);
        newNode.right = copyHelper(node.right, newNode, newTree);
    
        return newNode;
    }

    /**
     * This is the subclass for specific nodes of the BST.
     */
    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        //instance variables.
        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Finds the successor node
         * @return the successor node.
         */
        public MyBSTNode<K, V> successor() {
            if (this.getRight() == null){
                MyBSTNode<K, V> parent = this.getParent();
                MyBSTNode<K, V> current = this;
                while (parent != null && parent.getRight() == current){
                    current = parent;
                    parent = parent.parent;
                }
                return parent;
            }
            
            MyBSTNode<K, V> successor_node = this.getRight();
            while (successor_node.getLeft() !=null){
                successor_node = successor_node.getLeft();
            }
            return successor_node;
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }
}
