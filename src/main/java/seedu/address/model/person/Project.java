package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's project in prof-iler.
 * Guarantees: immutable; is valid as declared in {@link #isValidProject(String)}
 */
public class Project {

    public static final String MESSAGE_CONSTRAINTS = "Projects can take any values, and it should not be blank";

    /*
     * The first character of the project must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Project}.
     *
     * @param project A valid project.
     */
    public Project(String project) {
        requireNonNull(project);
        checkArgument(isValidProject(project), MESSAGE_CONSTRAINTS);
        value = project;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidProject(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return value.equals(otherProject.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
