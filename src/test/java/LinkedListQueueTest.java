import java.util.Collection;

/**
 * An implementation of QueueTest for LinkedListQueue.
 *
 * @see LinkedListQueue
 * @see QueueTest
 * @author leonid.ivashchenko
 */
public class LinkedListQueueTest extends QueueTest {

    @Override
    protected <T> Queue<T> createQueue() {
        return new LinkedListQueue<>();
    }

    @Override
    protected  <T> Queue<T> createQueue(Collection<T> collection) {
        return new LinkedListQueue<>(collection);
    }
}
