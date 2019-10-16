import java.util.Collection;

/**
 * An implementation of QueueTest for RecursiveQueue.
 *
 * @see HashMapQueue
 * @see QueueTest
 * @author leonid.ivashchenko
 */
public class RecursiveQueueTest extends QueueTest {

    @Override
    protected <T> Queue<T> createQueue() {
        return new RecursiveQueue<>();
    }

    @Override
    protected <T> Queue<T> createQueue(Collection<T> collection) {
        return new RecursiveQueue<>(collection);
    }
}
