package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's student ID in prof-iler.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should start with 'A', followed by 7 digits, end with a letter, and it should not be blank";

    /*
     * The student ID must start with 'A', followed by 7 digits, and end with an uppercase alphabet.
     */
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]*";

    public final String id;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid student ID.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return id.equals(otherId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
