package de.uniwue.jpp.testing.util;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Assertions;

import java.util.Collection;

public class TestUtils {

    public static void assertThrowsWithMessage(Class <? extends Exception> expectedType, Executable executable, String exceptionMessage, String message) {
        Exception exception = Assertions.assertThrows(expectedType, executable, message);

        if (exception.getMessage() == null) {
            throw new AssertionError(message + " ==> Exception was thrown but message was null");
        }
        if (!exception.getMessage().equals(exceptionMessage)) {
            throw new AssertionError(message + " ==> Incorrect exception message ==> expected: <" + exceptionMessage + "> but was: <" + exception.getMessage() + ">");
        }
    }

    public static <T> void assertCollectionsEquals(Collection <T> expected, Collection <T> actual, String message) {
        boolean value = false;

        if ((expected == null ^ actual == null)) {
            value = true;
        } else if (expected != null) {
            if (expected.size() != actual.size()) {
                value = true;
            } else if (!actual.containsAll(expected)) {
                value = true;
            }
        }
        if (value) {
            throw new AssertionError(message);
        }
    }

    public static void assertCollectionIsUnmodifiable(Collection <?> collection, String message) {
        if (collection == null) {
            throw new AssertionError("Collection is null");
        }
        try {
            collection.add(null);
        } catch (Throwable throwable) {
            return;
        }
        collection.remove(null);
        throw new AssertionError(message);
    }
}
