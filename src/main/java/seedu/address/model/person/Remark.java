package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's Remark in the Remark book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param Remark A valid Remark.
     */
    public Remark(String Remark) {
        requireNonNull(Remark);
        value = Remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark otherRemark)) {
            return false;
        }

        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
