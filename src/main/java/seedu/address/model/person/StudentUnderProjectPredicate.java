package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s {@code Project} matches the keyword given.
 */
public class StudentUnderProjectPredicate implements Predicate<Person> {

    private final String keyword;

    public StudentUnderProjectPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instaceof handles nulls
        if (!(other instanceof StudentUnderProjectPredicate)) {
            return false;
        }

        StudentUnderProjectPredicate otherStudentUnderProjectPredicate = (StudentUnderProjectPredicate) other;
        return keyword.equals(otherStudentUnderProjectPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Project", keyword).toString();
    }
}
