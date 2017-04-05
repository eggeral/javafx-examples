package egger.software.baylandtag.ax_unit_test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class JavaFxJUnit4ClassRunner extends BlockJUnit4ClassRunner {
	public JavaFxJUnit4ClassRunner(final Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
		try {
			CountDownLatch childCompleted = new CountDownLatch(1);
			TestApplicationContext.runInJavaFXThread(() -> {
				JavaFxJUnit4ClassRunner.super.runChild(method, notifier);
				childCompleted.countDown();
			});
			assertTrue("Test needed more than 30 seconds to execute", childCompleted.await(30, TimeUnit.SECONDS));
		} catch (Exception ex) {
			fail(ex.toString());
		}
	}
}