package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProgressTest {

    @Test
    public void constructor_void_setsProgressToZero() {
        Progress progress = new Progress();
        assertEquals("0", progress.getValue());
    }

    @Test
    public void constructor_invalidProgress_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Progress(" "));
        assertThrows(IllegalArgumentException.class, () -> new Progress("abc"));
        assertThrows(IllegalArgumentException.class, () -> new Progress("123"));
        assertThrows(IllegalArgumentException.class, () -> new Progress("123ab45"));
    }

    @Test
    public void isValidProgress() {
        // valid addresses
        assertTrue(Progress.isValidProgress("32"));
        assertTrue(Progress.isValidProgress("100"));
        assertTrue(Progress.isValidProgress("0"));

    }

    @Test
    public void equals() {
        Progress progress = new Progress("75");

        // same values -> returns true
        assertTrue(progress.equals(new Progress("75")));

        // same object -> returns true
        assertTrue(progress.equals(progress));

        // null -> returns false
        assertFalse(progress.equals(null));

        // different types -> returns false
        assertFalse(progress.equals("hi"));

        // different values -> returns false
        assertFalse(progress.equals(new Progress("76")));
    }

}

