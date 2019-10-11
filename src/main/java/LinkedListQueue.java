import java.util.Collection;
import java.util.LinkedList;

/**
 * Linked container immutable queue implementation. The simplest but not the most efficient.
 *
 * @param <T> elements type
 * @author leonid.ivashchenko
 */
public class LinkedListQueue<T> implements Queue<T> {

    /**
     * Inner container.
     */
    private LinkedList<T> container = new LinkedList<>();

    /**
     * Public constructor, creates an empty queue.
     */
    public LinkedListQueue() {
    }

    /**
     * Public constructor, creates a queue out of the given collection.
     * Copies all the elements into the inner storage.
     * Order of the elements depends on Collection implementation.
     *
     * @param collection initial collection
     */
    public LinkedListQueue(Collection<T> collection) {
        container.addAll(collection);
    }

    @Override
    public Queue<T> enQueue(T t) {
        LinkedListQueue<T> newQueue = new LinkedListQueue<>(container);
        newQueue.container.addLast(t);

        return newQueue;
    }

    @Override
    public Queue<T> deQueue() {
        LinkedListQueue<T> newQueue = new LinkedListQueue<>(container);
        newQueue.container.removeFirst();

        return newQueue;
    }

    @Override
    public T head() {
        return container.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }
}
