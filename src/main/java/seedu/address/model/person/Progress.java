package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's progress a project in Prof-iler.
 * Guarantees: immutable; is valid as declared in {@link #isValidProgress(int)}
 */

public class Progress {
    public static final String MESSAGE_CONSTRAINTS =
            "Progress should be a whole number between 0 and 100";
    public static final String VALIDATION_REGEX = "^(100|[1-9]?[0-9])$";

    public final String value;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress A valid progress.
     */
    public Progress(String progress) {
        requireNonNull(progress);
        checkArgument(isValidProgress(progress), MESSAGE_CONSTRAINTS);
        this.value = progress;
    }

    /**
     * Constructs a {@code Progress} with default value 0
     */
    public Progress() {
        this.value = "0";
    }


    /**
     * Returns true if a given String is a valid progress.
     */
    public static boolean isValidProgress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns value of progress.
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
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
        return value.equals(otherProgress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
