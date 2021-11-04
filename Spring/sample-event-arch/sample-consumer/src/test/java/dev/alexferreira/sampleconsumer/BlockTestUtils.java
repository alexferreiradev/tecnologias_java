package dev.alexferreira.sampleconsumer;

public final class BlockTestUtils {

    public static void when(Runnable runnable) throws Exception {
        runnable.run();
    }
}
