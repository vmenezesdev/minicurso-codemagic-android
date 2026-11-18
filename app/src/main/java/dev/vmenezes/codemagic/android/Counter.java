package dev.vmenezes.codemagic.android;

/** Regra do contador, testável sem Android ou emulador. */
public final class Counter {
    private int value;

    public Counter(int initialValue) {
        value = initialValue;
    }

    public int getValue() {
        return value;
    }

    public void increment() {
        value += 1;
    }

    public void reset() {
        value = 0;
    }
}
