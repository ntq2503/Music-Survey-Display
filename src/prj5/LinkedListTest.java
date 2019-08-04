package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers & Jackson Todd (m0ri3) Quan Nguyen (ntq2503)
/**
 * Linked list used to store objects of song and student.
 * Construction of class starts with Iterator ( @see LinkedListIterator)
 * than Node ( @see Node )
 * than linked list ( @see LinkedList)
 * 
 * 
 * @author Jackson Todd (m0ri3)
 * @version 2018.11.11
 */
public class LinkedListTest extends TestCase {
    // Fields----------------------------------------------------
    private LinkedList<String> list;


    // ----------------------------------------------------------
    /**
     * Default set up before every test case
     */
    public void setUp() {
        list = new LinkedList<String>();
    }


    // ----------------------------------------------------------
    /**
     * Test size() method for LinkedList
     */
    public void testSize() {
        assertEquals(0, list.size());
        list.add("test1");
        assertEquals(1, list.size());
        list.add("test2");
        assertEquals(2, list.size());
        list.remove();
        assertEquals(1, list.size());
        list.remove();
        assertEquals(0, list.size());
    }


    // ----------------------------------------------------------
    /**
     * Test add() and toString() method for LinkedList
     */
    public void testAdd() {
        // empty list to string
        assertEquals(list.toString(), "[]");
        // addint to list
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertEquals("[1, 2, 3, 4]", list.toString());

        list.add(1, "between 3 and 2");
        assertEquals("[1, 2, between 3 and 2, 3, 4]", list.toString());

        assertFalse(list.add(null));
        assertTrue(list.addFirst("0"));
        assertEquals("[0, 1, 2, between 3 and 2, 3, 4]", list.toString());
        assertFalse(list.addLast(null));

        // testing exception
        Exception exception = null;
        try {
            list.add(20, "fail add");
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);

    }


    // ----------------------------------------------------------
    /**
     * Test indexOf() method for LinkedList
     */
    public void testIndexOf() {
        // addint to list
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertEquals(list.indexOf("1"), 0);
        assertEquals(list.indexOf("2"), 1);
        assertEquals(list.indexOf("3"), 2);
        assertEquals(list.indexOf("4"), 3);
        assertEquals(list.indexOf("5"), -1);
    }


    // ----------------------------------------------------------
    /**
     * Test remove() method for LinkedList
     */
    public void testRemove() {
        // remove on empty list
        assertFalse(list.remove());

        // add int to list
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        // remove(e)
        assertTrue(list.remove("4"));
        assertFalse(list.remove("5"));

        // test null
        list.add(null);
        assertFalse(list.remove(null));

        // exception for remove index
        Exception exception = null;
        try {
            list.remove(6);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }


    // ----------------------------------------------------------
    /**
     * Test replace() method for LinkedList
     */
    public void testReplace() {
        // add int to list
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertEquals(list.replace(0, "4"), "1");
        assertEquals("[4, 2, 3, 4]", list.toString());

        Exception exception = null;
        try {
            list.replace(6, "4");
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }


    // ----------------------------------------------------------
    /**
     * Test toArray() method for LinkedList
     */
    public void testToArray() {
        // add int to list
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertTrue(list.toArray() instanceof Object);
    }


    // ----------------------------------------------------------
    /**
     * Test remove() method for LinkedList
     */
    public void testRemoveTwo() {
        // add int to list
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertEquals(list.remove(0), "1");
        assertEquals(list.remove(0), "2");
        assertEquals(list.remove(1), "4");
    }


    // ----------------------------------------------------------
    /**
     * Test addLast() method for LinkedList
     */
    public void testAddLast() {
        list.addLast("4");
        list.addLast("3");
        list.addLast("2");
        list.addLast("1");

        assertEquals("[4, 3, 2, 1]", list.toString());
    }


    // ----------------------------------------------------------
    /**
     * Test get() method for LinkedList
     */
    public void testGet() {
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");
        assertEquals("3", list.get(2));
        assertEquals("1", list.get(0));
    }


    // ----------------------------------------------------------
    /**
     * Test clear() method for LinkedList
     */
    public void testClear() {
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");
        assertEquals(4, list.size());
        assertEquals("1", list.get(0));

        list.clear();
        assertEquals(0, list.size());
        assertNull(list.get(0));
    }


    // ----------------------------------------------------------
    /**
     * Test contains() method for LinkedList
     */
    public void testContains() {
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertTrue(list.contains("3"));
        assertFalse(list.contains("5"));
    }


    // ----------------------------------------------------------
    /**
     * Test isEmpty() method for LinkedList
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("test");
        assertFalse(list.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Test iterator method for LinkedList
     */
    public void testListIterator() {
        Iterator<String> iter = list.iterator();
        assertFalse(iter.hasNext());

        // remove on empty
        Exception exception = null;
        try {
            iter.remove();
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IllegalStateException);
        // add strings
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");

        assertTrue(iter.hasNext());
        // remove at null head
        Exception exception2 = null;
        try {
            iter.remove();
        }
        catch (Exception e) {
            exception2 = e;
        }
        assertNotNull(exception2);
        assertTrue(exception2 instanceof IllegalStateException);

        // iterating
        assertEquals(iter.next(), "1");
        iter.remove();

        // attempt to remove after element already removed
        Exception exception3 = null;
        try {
            iter.remove();
        }
        catch (Exception e) {
            exception3 = e;
        }
        assertNotNull(exception3);
        assertTrue(exception3 instanceof IllegalStateException);

        assertEquals(list.toString(), "[2, 3, 4]");
        assertEquals(iter.next(), "2");
        iter.remove();
        assertEquals(iter.next(), "3");
        iter.remove();
        assertEquals(iter.next(), "4");

        // tail after iterating
        Exception exception4 = null;
        try {
            iter.next();
        }
        catch (Exception e) {
            exception4 = e;
        }
        assertNotNull(exception4);
        assertTrue(exception4 instanceof NoSuchElementException);

    }


    /**
     * test method equals()
     */
    public void testEquals() {
        assertTrue(list.equals(list));
        LinkedList<String> nullList = null;
        assertFalse(list.equals(nullList));
        int n = 0;
        assertFalse(list.equals(n));
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");
        LinkedList<String> list2 = new LinkedList<String>();
        assertFalse(list.equals(list2));
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("2");
        assertFalse(list.equals(list2));
        list.remove();
        list.add("1");
        assertFalse(list.equals(list2));
    }

}
