import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap based implementation of the Queue interface. Complexity for deQueue, head, isEmpty methods is O(1).
 * For enQueue it's O(1) in the best case and O(n) in the worst case. Worst case appears if we wanna enqueue a few elements
 * into the same queue object, so we end up with several different queues.
 *
 * ATTENTION There are several troubles about this implementation.
 * One is as it doesn't remove deQueued elements from the map sooner or later we might run out of memory.
 * so TODO come up with links counting for every entry in the map
 *
 * @param <T> items type
 * @author leonid.ivashchenko
 */
public class HashMapQueue<T> implements Queue<T> {

    /**
     * Inner storage.
     */
    private Map<Long, T> map;

    /**
     * Head index.
     */
    private Long head = 0L;

    /**
     * Tail index.
     */
    private Long tail = 0L;

    /**
     * EnQueued flag.
     */
    private Boolean enQueued = false;

    /**
     * Creates an empty queue.
     */
    public HashMapQueue() {
        map = new ConcurrentHashMap<>();
    }

    /**
     * Creates a pre-filled queue.
     *
     * @param collection init collection
     */
    public HashMapQueue(Collection<T> collection) {
        map = new ConcurrentHashMap<>();

        for (T item : collection) {
            map.put(tail, item);
            tail++;
        }
    }

    /**
     * Closed constructor.
     *
     * @param map new inner storage
     * @param head index
     * @param tail index
     */
    private HashMapQueue(Map<Long, T> map, Long head, Long tail) {
        this.map = map;
        this.head = head;
        this.tail = tail;
    }

    @Override
    public synchronized Queue<T> enQueue(T t) {
        Map<Long, T> newMap = null;
        if (!enQueued) {
            map.put(tail, t);
            newMap = map;
        } else {
            newMap = new ConcurrentHashMap<>(map);
            newMap.put(tail, t);
        }

        //TODO we don't have to copy the whole map, if head > 0 then we can get rid of the first element in the new map
        enQueued = true;
        return new HashMapQueue<>(newMap, head, tail + 1);
    }

    @Override
    public Queue<T> deQueue() {
        return new HashMapQueue<>(map, head + 1, tail);
    }

    @Override
    public T head() {
        T item = map.get(head);

        if (item == null) {
            throw new NoSuchElementException();
        } else {
            return item;
        }
    }

    @Override
    public boolean isEmpty() {
        return head.equals(tail);
    }
}
