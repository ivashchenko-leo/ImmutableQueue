/**
 * Interface for an immutable queue.
 *
 * @param <T> stored items type
 */
public interface Queue<T> {

    /**
     * Queues the given element and returns a new queue.
     *
     * @param t input item to be queued
     * @return new queue
     */
    public Queue<T> enQueue(T t);

    /**
     * Removes the element at the beginning of the immutable queue, and returns a new queue.
     *
     * @return new queue
     */
    public Queue<T> deQueue();

    /**
     * Returns the first element of the queue.
     *
     * @return the first element
     */
    public T head();

    /**
     * Returns a boolean value whether the queue is empty or not.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty();
}