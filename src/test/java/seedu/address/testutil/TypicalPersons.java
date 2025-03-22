package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROGRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROGRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withId("A0284716F").withProject("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withProgress("50").withPhone("94351253").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withId("A9472937S").withProject("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withProgress("100").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withId("A0285516F")
            .withPhone("95352563").withEmail("heinz@example.com").withProject("wall street").withProgress("40").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withId("A0128588N")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withProject("10th street").withProgress("68").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withId("A0778839G")
            .withPhone("9482224").withEmail("werner@example.com")
            .withProject("michegan ave").withProgress("32").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withId("A0284736H")
            .withPhone("9482427").withEmail("lydia@example.com").withProgress("22").withProject("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withId("A0028152D")
            .withPhone("9482442").withProgress("10").withEmail("anna@example.com").withProject("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withId("A0012212D").withPhone("8482424").withEmail("stefan@example.com")
            .withProject("little india").withProgress("30").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withId("A7283937J").withProgress("0")
            .withPhone("8482131").withEmail("hans@example.com").withProject("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withId(VALID_ID_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withProject(VALID_PROJECT_AMY)
            .withProgress(VALID_PROGRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withProject(VALID_PROJECT_BOB)
            .withProgress(VALID_PROGRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
