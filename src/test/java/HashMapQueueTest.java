import java.util.Collection;

/**
 * An implementation of QueueTest for HashMapQueue.
 *
 * @see HashMapQueue
 * @see QueueTest
 * @author leonid.ivashchenko
 */
public class HashMapQueueTest extends QueueTest {

    @Override
    protected <T> Queue<T> createQueue() {
        return new HashMapQueue<>();
    }

    @Override
    protected  <T> Queue<T> createQueue(Collection<T> collection) {
        return new HashMapQueue<>(collection);
    }
}
