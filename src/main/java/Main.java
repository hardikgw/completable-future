import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        CompletableFuture<String> asyncFuture1 = null;
        CompletableFuture<String> asyncFuture2 = null;

        try {
            asyncFuture1 = future1("Future 1");
            Thread.sleep(15000);
            asyncFuture2 = future2("Future 2");

            Thread.sleep(15000);
            System.out.println("After both future()");
            CompletableFuture<String> message1 = asyncFuture1.thenApplyAsync(s -> s);
            Thread.sleep(15000);
            CompletableFuture<String> message2 = asyncFuture2.thenApplyAsync(s -> s);
            Thread.sleep(15000);

            System.out.println(message1.get());
            System.out.println(message2.get());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static CompletableFuture<String> future1(String message) {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("In Future 1" + message);
                        Thread.sleep(5000);
                        return message;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private static CompletableFuture<String> future2(String message) {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("In Future 2" + message);
                        Thread.sleep(5000);
                        return message;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
