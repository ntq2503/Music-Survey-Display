package prj5;

import java.util.Iterator;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers & Jackson Todd (m0ri3) Quan Nguyen (ntq2503)
/**
 * Linked list ADT used to store objects of song and student.
 * 
 * 
 * @author Jackson Todd (m0ri3)
 * @version 2018.11.11
 * @param <E>
 *            linked list is generic type
 */
public interface LinkedListADT<E> {
    // ----------------------------------------------------------
    /**
     * Replaces the node at the current index
     * 
     * @precondition index cannot be larger than list size
     * @see add(index, newData)
     *      Does actual adding of node
     * @see remove(index)
     *      Passes to remove helper to remove node
     * @param index
     *            index to be replaced
     * @param newData
     *            new data to be inserted at index
     * @return
     *         data that was replaced
     * @throws IndexOutOfBoundsException
     */
    public E replace(int index, E newData);


    // ----------------------------------------------------------
    /**
     * Adds generic type to linked list
     * Adds to front of linked list.
     * 
     * @param e
     *            Generic type to be added.
     * @return
     *         returns false if <E>e is null
     *         returns true if Node is added.
     */
    public boolean add(E e);


    // ----------------------------------------------------------
    /**
     * Adds node after given index number.
     * 
     * @precondition index cannot be larger than list size
     * 
     * @param index
     *            index to look for for add
     * @param e
     *            Generic type to ads.
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, E e);


    // ----------------------------------------------------------
    /**
     * resets the entire list to default constructor
     * 
     */
    public void clear();


    // ----------------------------------------------------------
    /**
     * Sees if the linked list contains the element
     * Uses helper method @see findNode
     * 
     * @return true list contains element
     * 
     * @param e
     *            Generic type to search for.
     */
    public boolean contains(E e);


    // ----------------------------------------------------------
    /**
     * Returns the node at a given index
     * 
     * @precondition index not larger than size of list
     * @param index
     *            the index spot to look at in the list
     * @return
     *         return node if node at index
     *         return null if index larger than list
     * 
     */
    public E get(int index);


    // ----------------------------------------------------------
    /**
     * Searches for index of given generic type within node data
     * 
     * @param e
     *            Generic type to search for
     * @return
     *         returns index of data looked for
     *         returns -1 if not found
     */
    public int indexOf(E e);


    // ----------------------------------------------------------
    /**
     * checks if list is empty
     * 
     * @return true if is empty
     */
    public boolean isEmpty();


    // ----------------------------------------------------------
    /**
     * Object Creation for new iterator.
     * 
     * @return the iterator object
     */
    public Iterator<E> iterator();


    // ----------------------------------------------------------
    /**
     * Removes data type e at location index
     * uses findNode to find data location
     * 
     * @precondition index cannot be larger than list size
     * @param index
     *            index to remove
     * @return
     *         false if node is null or does not exist
     *         true if node is removed
     * @throws IndexOutOfBoundException
     */
    public E remove(int index);


    // ----------------------------------------------------------
    /**
     * Default remove to remove front of list
     * 
     * @return true if removed
     * 
     */
    public boolean remove();


    // ----------------------------------------------------------
    /**
     * Removes data type e after finding location
     * uses findNode to find data location
     * 
     * @param e
     *            Generic data type to search for
     * @return
     *         false if node is null or does not exist
     *         true if node is removed
     */
    public boolean remove(E e);


    // ----------------------------------------------------------
    /**
     * getter for list size
     * 
     * @return
     *         size field for list
     */
    public int size();


    // ----------------------------------------------------------
    /**
     * Puts node data into an array.
     * 
     * @return
     *         returns array with node data.
     */
    public Object[] toArray();


    // ----------------------------------------------------------
    /**
     * Puts node data into string format
     * String format ex: [1, 2, 3]
     * 
     * @return
     *         returns string of data
     */
    public String toString();
}
