package com.epam.telenettv.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ConditionTimeoutException;

public class AwaitilityHelper {

	public static void await(long timeoutSec, Callable<Boolean> action, long pollInterval, String message) {
        await(timeoutSec, action, pollInterval, true, message);
    }
	
	public static void await(long timeoutSec, Callable<Boolean> action, long pollInterval, boolean ignoreExceptions, String message) {
        final ConditionFactory conditionFactory = getConditionFactory(timeoutSec, pollInterval);
        if (ignoreExceptions) {
            conditionFactory.ignoreExceptions();
        }
        
        try {
            conditionFactory.until(action);
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(e.getMessage()+ "\n" + message);
        }
    }
	
	private static ConditionFactory getConditionFactory(long timeoutSec, long pollInterval) {
        return Awaitility.await().given()
                .atMost(timeoutSec, TimeUnit.SECONDS)
                .pollInSameThread()
                .pollInterval(pollInterval, TimeUnit.SECONDS)
                .pollDelay(0L, TimeUnit.SECONDS);
    }
}
