package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of Prof-iler data
 */
public interface ReadOnlyProfiler {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
