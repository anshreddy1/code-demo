/*
 * Name: Sree Ansh Reddy Kalakoti
 * Email: skalakoti@ucsd.edu
 * PID: A17485151
 * 
 * This files contains custom test cases for MyBST
 */

 import org.junit.Before;
 import org.junit.Test;
 
 import java.util.ArrayList;
 
 import static org.junit.Assert.*;
 
 
 /**
  * The tester class containing all the tests
  */
 public class CustomTester {
     MyBST<Integer, Integer> tree;
 
     /**
      * Sets up the necessary nodes of the BST
      */
     @Before
     public void setup() {
         MyBST.MyBSTNode<Integer, Integer> root =
                 new MyBST.MyBSTNode<>(5, 1, null);
         MyBST.MyBSTNode<Integer, Integer> three =
                 new MyBST.MyBSTNode<>(3, 1, root);
         MyBST.MyBSTNode<Integer, Integer> seven =
                 new MyBST.MyBSTNode<>(7, 1, root);
         MyBST.MyBSTNode<Integer, Integer> one =
                 new MyBST.MyBSTNode<>(1, 2, three);
         MyBST.MyBSTNode<Integer, Integer> four =
                 new MyBST.MyBSTNode<>(4, 30, three);
         MyBST.MyBSTNode<Integer, Integer> six =
                 new MyBST.MyBSTNode<>(6, 50, seven);
 
         this.tree = new MyBST<>();
         this.tree.root = root;
         root.setLeft(three);
         root.setRight(seven);
         three.setLeft(one);
         three.setRight(four);
         seven.setLeft(six);
         tree.size = 6;
     }
 
     /**
      * Tests if the successor of the last node on the right is null
      */
     @Test
     public void testSuccessorLast() {
         MyBST.MyBSTNode<Integer, Integer> last = tree.root.getRight();
         assertSame(null, last.successor());
     }
 
     /**
      * Tests the successor of a random node in the middle
      */
     @Test
     public void testSuccessorMiddle() {
         MyBST.MyBSTNode<Integer, Integer> useNode = 
                 tree.root.getLeft().getLeft();
         assertSame(tree.root.getLeft(), useNode.successor());
     }
 
     @Test
     public void testSuccessorRoot() {
         MyBST<Integer, Integer> empty = new MyBST<>();
         empty.insert(2, 10);
         assertNull(empty.root.successor());
     }
 
     /**
      * Tests the insert method with a pre-existing key
      */
     @Test
     public void testInsertReplace() {
         MyBST.MyBSTNode<Integer, Integer> root = tree.root;
         tree.insert(6, 20);
         assertEquals(6, root.getRight().getLeft().getKey().intValue());
         assertSame(root.getRight(), root.getRight().getLeft().getParent());
         assertEquals("size of tree", 6, tree.size);
         assertEquals(20, root.getRight().getLeft().getValue().intValue());
     }
 
     /**
      * Tests the insert method when the BST is empty
      */
     @Test
     public void testInsertEmpty() {
         MyBST<Integer, Integer> empty = new MyBST<>();
         empty.root = null;
         empty.insert(1, 22);
         MyBST.MyBSTNode<Integer, Integer> root = empty.root;
         assertEquals(1, root.getKey().intValue());
         assertSame(null, root.getRight());
         assertSame(null, root.getLeft());
         assertEquals(1, empty.size());
         assertEquals(22, root.getValue().intValue());
     }
 
     /**
      * Tests the basic implementation of insert method
      */
     @Test
     public void testInsertMiddle() {
         MyBST.MyBSTNode<Integer, Integer> root = tree.root;
         tree.insert(2, 40);
         assertEquals(2,
                 root.getLeft().getLeft().getRight().getKey().intValue());
         assertSame(root.getLeft().getLeft(),
                 root.getLeft().getLeft().getRight().getParent());
         assertEquals("size of tree", 7, tree.size);
         assertEquals(40,
                 root.getLeft().getLeft().getRight().getValue().intValue());
     }
 
     /**
      * Tries to insert a node with a null key
      */
     @Test (expected = NullPointerException.class)
     public void testInsertNull() {
         tree.insert(null, null);
     }
 
     /**
      * Tries to remove a node with null key
      */
     @Test
     public void testRemoveNull() {
         assertNull(tree.remove(null));
         assertEquals(6, tree.size());
     }
 
     /**
      * Tests the remove method on a leaf
      */
     @Test
     public void testRemoveLeaf() {
         MyBST.MyBSTNode<Integer, Integer> root = tree.root;
         assertEquals(50, tree.remove(6).intValue());
         assertEquals(5, tree.size());
         assertEquals(null, root.getRight().getLeft());
         assertEquals(7, root.getRight().getKey().intValue());
         assertEquals(3, root.getLeft().getKey().intValue());
     }
 
     /**
      * Tests the remove method on an invalid key
      */
     @Test
     public void testRemoveNothing() {
         MyBST.MyBSTNode<Integer, Integer> root = tree.root;
         assertEquals(null, tree.remove(9));
         assertEquals(6, tree.size());
         assertEquals(5, root.getKey().intValue());
     }
 
     /**
      * Tests the remove method on the root
      */
     @Test
     public void testRemoveRoot() {
         assertEquals(1, tree.remove(5).intValue());
         assertEquals(5, tree.size());
         assertEquals(6, tree.root.getKey().intValue());
         assertEquals(7, tree.root.getRight().getKey().intValue());
         assertEquals(null, tree.root.getRight().getLeft());
         assertEquals(3, tree.root.getLeft().getKey().intValue());
     }
 
     /**
      * Tests the remove method when there is just one child
      */
     @Test
     public void testRemoveSingleChild() {
         MyBST.MyBSTNode<Integer, Integer> root = tree.root;
         assertEquals(1, tree.remove(7).intValue());
         assertEquals(6, root.getRight().getKey().intValue());
         assertEquals(5, tree.size());
         assertNull(root.getRight().getLeft());
     }
 
     /**
      * Tests the remove method is repeated until tree is empty
      */
     @Test
     public void testRemoveAll() {
         assertEquals(1, tree.remove(7).intValue());
         assertEquals(50, tree.remove(6).intValue());
         assertEquals(1, tree.remove(5).intValue());
         assertEquals(30, tree.remove(4).intValue());
         assertEquals(1, tree.remove(3).intValue());
         assertEquals(2, tree.remove(1).intValue());
         assertNull(tree.root);
         assertEquals(0, tree.size());
         assertNull(tree.search(1));
     }
 
     /**
      * Tests the inorder method's basic implementation
      */
     @Test
     public void testInorder() {
         //tree.remove(3);
         MyBST.MyBSTNode<Integer, Integer> root = tree.root;
         ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                 new ArrayList<>();
         expectedRes.add(root.getLeft().getLeft());
         expectedRes.add(root.getLeft());
         expectedRes.add(root.getLeft().getRight());
         expectedRes.add(root);
         expectedRes.add(root.getRight().getLeft());
         expectedRes.add(root.getRight());
         assertEquals(expectedRes, tree.inorder());
     }
 
     /**
      * Tests the inorder method when the tree is empty
      */
     @Test
     public void testInorderEmpty() {
         ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                 new ArrayList<>();
         MyBST<Integer, Integer> empty = new MyBST<>();
         assertNotEquals(null, empty.inorder());
         assertEquals(expectedRes, empty.inorder());
     }
 
     /**
      * Tests the search method with a null key
      */
     @Test
     public void testSearchNull() {
         assertNull(tree.search(null));
     }
 
     /**
      * Tests the search method with an invalid key
      */
     @Test
     public void testSearchInvalid() {
         assertNull(tree.search(10));
     }
 
     /**
      * Tests the search method's basic implementation
      */
     @Test
     public void testSearchMiddle() {
         assertEquals(1, tree.search(7).intValue());
     }
 }
