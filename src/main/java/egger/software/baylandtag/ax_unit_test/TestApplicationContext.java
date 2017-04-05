package egger.software.baylandtag.ax_unit_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class TestApplicationContext extends Application {
    /**
     * The lock that guarantees that only one JavaFX thread will be started.
     */
    private static final ReentrantLock LOCK = new ReentrantLock();

    /**
     * indicates whether the application is already running.
     */
    private static final CountDownLatch applicationRunning = new CountDownLatch(1);

    @Override
    public void start(final Stage stage) {
        applicationRunning.countDown();
    }

    public static void launchApplication() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TestApplicationContext.launchFromHere();
                } catch (IllegalStateException ex) {
                    System.out.println("Warning: ApplicationContext already running. Not starting a new one!");
                    applicationRunning.countDown();
                }
            }
        });
        thread.start();

        applicationRunning.await();
    }

    private static void launchFromHere() {
        Application.launch();
    }

    public static boolean isApplicationRunning() {
        return applicationRunning.getCount() <= 0;
    }

    /**
     * Runs the given action within a JavaFX application context. Creates an application context if none has been created yet.
     *
     * @param <TRet> The type of the return value
     * @param runnable the Runnable whose run method will be executed on the JavaFX Application Thread
     * @throws java.lang.InterruptedException
     */
    public static <TRet> void runInJavaFXThread(Runnable runnable) throws InterruptedException {
        LOCK.lock();
        try {
            if (!isApplicationRunning())
                launchApplication();
        } finally {
            LOCK.unlock();
        }

        Platform.runLater(runnable);
    }
}
