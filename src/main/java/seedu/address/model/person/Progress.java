package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidProgress(int)}
 */

public class Progress {
    public static final String MESSAGE_CONSTRAINTS =
            "Progress should be a number between 0 and 100";

    public final int value;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress A valid progress.
     */

    public Progress(int progress) {
        requireNonNull(progress);
        checkArgument(isValidProgress(progress), MESSAGE_CONSTRAINTS);
        this.value = progress;
    }

    /**
     * Constructs a {@code Progress} with default value 0
     */
    public Progress() {
        this.value = 0;
    }


    /**
     * Returns true if a given int is a valid progress.
     */
    public static boolean isValidProgress(int test) {
        return test >= 0 && test <= 100;
    }

    /**
     * Returns value of progress.
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Progress)) {
            return false;
        }

        Progress otherProgress = (Progress) other;
        return value == otherProgress.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }


}
