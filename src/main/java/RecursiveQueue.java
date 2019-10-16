import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Recursive implementation of the Queue. deQueue, head and isEmpty have O(1) complexity.
 * EnQueue has O(n) and we might get a StackOverflowException if we have too many invocations.
 * TODO think how to get rid of recursion
 *
 * @param <T>
 * @author leonid.ivashchenko
 */
public class RecursiveQueue<T> implements Queue<T> {

    /**
     * Front element.
     */
    private T element;

    /**
     * Back queue.
     */
    private Queue<T> back;

    /**
     * Creates an empty queue.
     */
    public RecursiveQueue() {
        element = null;
        back = null;
    }

    /**
     * Creates a queue out of the given collection.
     *
     * @param collection init collection
     */
    public RecursiveQueue(Collection<T> collection) {
        if (collection.isEmpty()) {
            element = null;
            back = null;
        }

        int i = 0;
        back = new RecursiveQueue<>();
        for (T item : collection) {
            if (i == 0) {
                element = item;
            } else {
                back = back.enQueue(item);
            }

            i++;
        }
    }

    /**
     * Creates a queue with the given front element and back queue.
     *
     * @param element front element
     * @param back queue
     */
    private RecursiveQueue(T element, Queue<T> back) {
        this.element = element;
        this.back = back;
    }

    @Override
    public Queue<T> enQueue(T t) {
        if (isEmpty()) {
            return new RecursiveQueue<>(t, null);
        }

        if (back == null) {
            Queue<T> back = new RecursiveQueue<>();

            return new RecursiveQueue<>(element, back.enQueue(t));
        }

        return new RecursiveQueue<>(element, back.enQueue(t));
    }

    @Override
    public Queue<T> deQueue() {
        if (back != null) {
            return back;
        }

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return new RecursiveQueue<>();
    }

    @Override
    public T head() {
        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    @Override
    public boolean isEmpty() {
        return element == null && back == null;
    }
}
