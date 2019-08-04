// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers & Jackson Todd (m0ri3) 9060-29058

package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Linked list used to store objects of song and student.
 * Construction of class starts with Iterator ( @see LinkedListIterator)
 * than Node ( @see Node )
 * than linked list ( @see LinkedList)
 * 
 * 
 * @author Jackson Todd (m0ri3)
 * @version 2018.11.11
 * @param <E>
 *            linked list is generic type
 */

public class LinkedList<E> implements LinkedListADT<E> {

    // =========================Iterator Start=========================

    /**
     * Iterator for linked list
     * 
     * @author Jackson Todd (m0ri3)
     * @version 2018.11.11
     * @param <A>
     */
    private class LinkedListIterator<A> implements Iterator<E> {
        private Node<E> curNode;


        // ----------------------------------------------------------
        /**
         * Creates a new DLListIterator object
         */
        public LinkedListIterator() {
            curNode = head;
        }


        // ----------------------------------------------------------
        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return !curNode.next().equals(tail);
        }


        // ----------------------------------------------------------
        /**
         * Iterates to next node than return value.
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public E next() {
            curNode = curNode.next();
            if (curNode.equals(tail)) {
                throw new NoSuchElementException("No more elements");
            }
            return curNode.data();
        }


        // ----------------------------------------------------------
        /**
         * Removes the last object returned with next() from the list
         *
         * @throws IllegalStateException
         *             if next has not been called yet
         *             and if the element has already been removed
         */
        @Override
        public void remove() {
            Node<E> previous = curNode.prev();
            Node<E> next = curNode.next();
            if (curNode.equals(head) || previous.next().equals(next)) {
                throw new IllegalStateException("The element can't be removed");
            }
            previous.setNext(next);
            next.setPrev(previous);
            size--;
        }
    }
    // =========================Iterator End=========================


    // =========================Node-Start=========================
    /**
     * Private node class to store student and song information.
     * 
     * @author Jackson Todd (m0ri3)
     * @version 2018.11.11
     * @param <T>
     */
    private class Node<T> {
        // Fields----------------------------------------------------
        private Node<T> next;
        private Node<T> prev;
        private T data;


        // ----------------------------------------------------------
        /**
         * Default node constructor
         * sets all to null
         */
        public Node() {
            this(null, null, null);
        }


        // ----------------------------------------------------------
        /**
         * Node constructor
         * 
         * @param prev
         *            sets previous node in list
         * @param next
         *            sets next node in list
         * @param data
         *            data to be stored Generic type
         */
        public Node(Node<T> prev, Node<T> next, T data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }


        // ----------------------------------------------------------
        /**
         * Getter for data.
         * 
         * @return
         *         Generic data type in node
         */
        public T data() {
            return data;
        }


        // ----------------------------------------------------------
        /**
         * Getter for next node
         * 
         * @return
         *         returns next node
         */
        public Node<T> next() {
            return next;
        }


        // ----------------------------------------------------------
        /**
         * Getter for prev node.
         * 
         * @return
         *         return prev node
         */
        public Node<T> prev() {
            return prev;
        }


        // ----------------------------------------------------------
        /**
         * Setter for next node
         * 
         * @param next
         *            next node to set for link
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }


        // ----------------------------------------------------------
        /**
         * Sett for prev node
         * 
         * @param prev
         *            prev node to set for link
         */
        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

    }

    // =========================Node End=========================

    // =====================Linked List Star=====================

    // Fields----------------------------------------------------
    private int size;
    private Node<E> head;
    private Node<E> tail;


    // ----------------------------------------------------------
    /**
     * Default constructor for linked list
     * sets head and tail to a null node
     * sets size of list to 3
     * links head and tail
     */
    public LinkedList() {
        this.size = 0;
        this.head = new Node<E>();
        this.tail = new Node<E>();

        head.setNext(tail);
        tail.setPrev(head);
    }


    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }

        Node<E> newNode = new Node<E>(head, head.next(), e);
        head.next().setPrev(newNode);
        head.setNext(newNode);

        size++;
        return true;
    }


    // ----------------------------------------------------------
    /**
     * To complement interface uses @see add
     * 
     * @param e
     *            Generic type to be added.
     * @return
     *         returns false if <E>e is null
     *         returns true if Node is added.
     */
    public boolean addFirst(E e) {
        return add(e);
    }


    // ----------------------------------------------------------
    /**
     * Adds generic type to linked list
     * Adds to back of linked list.
     * 
     * @param e
     *            Generic type to be added.
     * @return
     *         returns false if <E>e is null
     *         returns true if Node is added.
     */
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }

        Node<E> newNode = new Node<E>(tail.prev(), tail, e);
        tail.prev().setNext(newNode);
        tail.setPrev(newNode);

        size++;
        return true;
    }


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
    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {

        if (index >= size) {
            throw new IndexOutOfBoundsException(
                "Index is larger than list size");
        }
        Node<E> prevNode = head.next();
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next();
        }
        Node<E> nextNode = prevNode.next();
        Node<E> newNode = new Node<E>(prevNode, nextNode, e);

        nextNode.setPrev(newNode);
        prevNode.setNext(newNode);

        size++;
    }


    // ----------------------------------------------------------
    /**
     * resets the entire list to default constructor
     * 
     * @see LinkedList
     */
    @Override
    public void clear() {
        this.size = 0;
        this.head = new Node<E>(null, tail, null);
        this.tail = new Node<E>(head, null, null);
    }


    // ----------------------------------------------------------
    /**
     * Sees if the linked list contains the element
     * Uses helper method @see findNode
     * 
     * @param e
     *            Generic type to search for.
     */
    @Override
    public boolean contains(E e) {
        return findNode(e) != null;
    }


    // ----------------------------------------------------------
    /**
     * Helper method for contains. Searches the list for node
     * 
     * @param e
     *            Generic to look for that was passed in @see contains
     * @return
     *         returns node if generic data found
     *         returns null if no matching node found
     */
    private Node<E> findNode(E e) {
        Node<E> curNode = head.next();
        while (!curNode.equals(tail)) {
            if (curNode.data().equals(e)) {
                return curNode;
            }
            curNode = curNode.next();
        }
        return null;
    }


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
    @Override
    public E get(int index) {
        if (index >= size) {
            return null;
        }

        Node<E> curNode = head.next();
        for (int i = 0; i < index; i++) {
            curNode = curNode.next();
        }

        return curNode.data();
    }


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
    @Override
    public int indexOf(E e) {
        Node<E> curNode = head.next;
        int index = 0;
        while (!curNode.equals(tail)) {
            if (curNode.data().equals(e)) {
                return index;
            }
            curNode = curNode.next();
            index++;
        }
        return -1;
    }


    // ----------------------------------------------------------
    /**
     * checks if list is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    // ----------------------------------------------------------
    /**
     * Object Creation for new iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<E>();
    }


    // ----------------------------------------------------------
    /**
     * Default remove to remove front of list
     * 
     * @see remove(Node<E> node)
     *      removes actual node
     */
    @Override
    public boolean remove() {
        return remove(head.next());
    }


    // ----------------------------------------------------------
    /**
     * Removes data type e after finding location
     * uses findNode to find data location
     * 
     * @see findNode
     * @see remove(Node<E> node)
     *      removes actual node
     * @param e
     *            Generic data type to search for
     * @return
     *         false if node is null or does not exist
     *         true if node is removed
     */
    @Override
    public boolean remove(E e) {
        Node<E> node = findNode(e);
        if (node == null) {
            return false;
        }

        remove(node);

        return true;
    }


    // ----------------------------------------------------------
    /**
     * Removes data type e at location index
     * uses findNode to find data location
     * 
     * @see remove(Node<E> node)
     *      helper method that actually removes node
     * @precondition index cannot be larger than list size
     * @param index
     *            index to remove
     * @return
     *         false if node is null or does not exist
     *         true if node is removed
     * @throws IndexOutOfBoundException
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                "Index is larger than list size");
        }
        Node<E> curNode = head.next();
        for (int i = 0; i < index; i++) {
            curNode = curNode.next();
        }

        remove(curNode);
        return curNode.data();
    }


    // ----------------------------------------------------------
    /**
     * Helper method that does the actual removal of currNode
     * Decrements size of list
     * 
     * @param node
     *            Node to be removed
     * @return
     *         false if list is empty
     *         true if node is removed
     */
    private boolean remove(Node<E> node) {
        if (isEmpty()) {
            return false;
        }
        node.prev().setNext(node.next());
        node.next().setPrev(node.prev());

        size--;
        return true;
    }


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
    @Override
    public E replace(int index, E newData) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException(
                "Index is larger than list size");
        }
        add(index, newData);
        E removedEntry = remove(index);

        return removedEntry;
    }


    // ----------------------------------------------------------
    /**
     * getter for list size
     * 
     * @return
     *         size field for list
     */
    @Override
    public int size() {
        return this.size;
    }


    // ----------------------------------------------------------
    /**
     * Puts node data into an array.
     * 
     * @return
     *         returns array with node data.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node<E> curNode = head;
        for (int i = 0; i < size(); i++) {
            array[i] = curNode.data();
            curNode = curNode.next();
        }

        return array;
    }


    // ----------------------------------------------------------
    /**
     * Puts node data into string format
     * String format ex: [1, 2, 3]
     * 
     * @return
     *         returns string of data
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (!isEmpty()) {
            Node<E> currNode = head.next();
            while (currNode != tail) {
                E element = currNode.data();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append(", ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("]");
        return builder.toString();
    }


    // ----------------------------------------------------------
    /**
     * Checks if the passed Object is a LinkedList that has the
     * same elements in the same order.
     * 
     * @param obj
     *            The Object to check equality with this.
     * @return True if the passed Object and this are equal by value.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        LinkedList<E> cast = (LinkedList<E>)obj;

        if (cast.size() != size) {
            return false;
        }

        Iterator<E> thisIt = this.iterator();
        Iterator<E> castIt = cast.iterator();

        E thisCurrentElement;
        E castCurrentElement;
        while (thisIt.hasNext()) {
            thisCurrentElement = (E)thisIt.next();
            castCurrentElement = (E)castIt.next();
            if (!thisCurrentElement.equals(castCurrentElement)) {
                return false;
            }
        }

        return true;
    }

}
