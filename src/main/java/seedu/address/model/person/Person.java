package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in prof-iler.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Id id;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Project project;
    private final Progress progress;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Id id, Phone phone, Email email, Project project, Progress progress, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, project, progress, tags);
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.project = project;
        this.progress = progress;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Id getId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Project getProject() {
        return project;
    }

    public Progress getProgress() {
        return progress;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && id.equals(otherPerson.id)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && project.equals(otherPerson.project)
                && progress.equals(otherPerson.progress)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, phone, email, project, progress, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("id", id)
                .add("phone", phone)
                .add("email", email)
                .add("project", project)
                .add("progress", progress)
                .add("tags", tags)
                .toString();
    }

}
