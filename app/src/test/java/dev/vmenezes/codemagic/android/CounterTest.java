package dev.vmenezes.codemagic.android;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class CounterTest {
    @Test
    public void startsAtZero() {
        assertEquals(0, new Counter(0).getValue());
    }

    @Test
    public void incrementsExactlyOnePerClick() {
        Counter counter = new Counter(0);
        counter.increment();
        assertEquals(1, counter.getValue());
        counter.increment();
        assertEquals(2, counter.getValue());
    }

    @Test
    public void resetsAfterClicks() {
        Counter counter = new Counter(0);
        counter.increment();
        counter.increment();
        counter.reset();
        assertEquals(0, counter.getValue());
    }

    @Test
    public void continuesFromRestoredValue() {
        Counter counter = new Counter(7);
        counter.increment();
        assertEquals(8, counter.getValue());
    }
}
