import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {
    private static final int COUNT_SHOPS = 3;

    public static void main(String[] args) throws InterruptedException {

        final ExecutorService threadPool = Executors.newFixedThreadPool(COUNT_SHOPS);
        LongAdder stat = new LongAdder();

        IntStream.range(100,300).forEach(i -> threadPool.submit(() -> stat.add(i)));
        IntStream.range(100,300).forEach(i -> threadPool.submit(() -> stat.add(i)));
        IntStream.range(100,300).forEach(i -> threadPool.submit(() -> stat.add(i)));
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Выручка " + COUNT_SHOPS + " магазинов: " + stat.sum() + " руб.");
        threadPool.shutdown();
    }
}
