import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Generic tests for immutable queues which implement Queue interface.
 * To test a particular implementation just inherit from this class and override createQueue methods.
 * Basically we just need to test enQueue and deQueue methods assuming isEmpty and head methods are working fine.
 * If they aren't, both tests will fail.
 *
 * @see Queue
 * @author leonid.ivashchenko
 */
public abstract class QueueTest {

    /**
     * Tests enQueue method.
     */
    @Test
    public void testEnQueue() {
        Queue<Long> queue1 = createQueue();
        Queue<Long> queue2 = queue1.enQueue(1L);

        assertTrue(queue1.isEmpty());
        assertFalse(queue2.isEmpty());
        assertEquals(new Long(1L), queue2.head());

        Queue<Long> queue3 = queue2.enQueue(2L);
        Queue<Long> queue4 = queue2.enQueue(3L);

        assertEquals(new Long(1L), queue3.head());
        assertEquals(new Long(1L), queue4.head());
        assertEquals(new Long(1L), queue2.head());

        Queue<Long> queue5 = queue3.deQueue();
        Queue<Long> queue6 = queue4.deQueue();

        assertEquals(new Long(2L), queue5.head());
        assertEquals(new Long(3L), queue6.head());
        assertEquals(new Long(1L), queue3.head());
        assertEquals(new Long(1L), queue2.head());

        Queue<Long> queue7 = queue3.enQueue(3L).enQueue(4L);
        queue7 = queue7.deQueue();
        assertEquals(new Long(2L), queue7.head());
        queue7 = queue7.deQueue();
        assertEquals(new Long(3L), queue7.head());
        queue7 = queue7.deQueue();
        assertEquals(new Long(4L), queue7.head());
        queue7 = queue7.deQueue();
        assertTrue(queue7.isEmpty());
    }

    /**
     * Tests deQueue method.
     */
    @Test(expected = NoSuchElementException.class)
    public void testDeQueue() {
        Queue<Long> queue1 = createQueue(Arrays.asList(1L, 2L, 3L));

        assertFalse(queue1.isEmpty());
        assertEquals(new Long(1L), queue1.head());

        Queue<Long> queue2 = queue1.deQueue();

        assertFalse(queue2.isEmpty());
        assertFalse(queue1.isEmpty());
        assertEquals(new Long(1L), queue1.head());
        assertEquals(new Long(2L), queue2.head());

        Queue<Long> queue3 = queue2.deQueue();
        Queue<Long> queue4 = queue2.deQueue();

        assertFalse(queue4.isEmpty());
        assertFalse(queue3.isEmpty());
        assertFalse(queue2.isEmpty());
        assertFalse(queue1.isEmpty());
        assertEquals(new Long(1L), queue1.head());
        assertEquals(new Long(2L), queue2.head());
        assertEquals(new Long(3L), queue3.head());
        assertEquals(new Long(3L), queue4.head());

        Queue<Long> queue5 = queue3.deQueue();

        assertTrue(queue5.isEmpty());
        queue5.head();
    }

    /**
     * Creates an empty queue.
     *
     * @param <T> elements type
     * @return new instance of an empty queue
     */
    protected abstract <T> Queue<T> createQueue();

    /**
     * Creates a queue filled with Collection's elements.
     * The order will depend on particular Collection implementation.
     *
     * @param collection of initial elements
     * @return new instance of an empty queue
     */
    protected abstract <T> Queue<T> createQueue(Collection<T> collection);
}
