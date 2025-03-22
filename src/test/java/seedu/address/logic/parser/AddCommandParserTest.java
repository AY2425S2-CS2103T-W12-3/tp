package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_IDA;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_IDA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROGRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_IDA;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_IDA;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PROGRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROGRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_IDA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.IDA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Progress;
import seedu.address.model.person.Project;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @SuppressWarnings("checkstyle:OperatorWrap")
    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + PROJECT_DESC_BOB
                        + PROGRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_FRIEND;
        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple ids
        assertParseFailure(parser, ID_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple projects
        assertParseFailure(parser, PROJECT_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PROJECT));

        // multiple progress
        assertParseFailure(parser, PROGRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PROGRESS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + ID_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + PROJECT_DESC_AMY + PROGRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ID, PREFIX_PROJECT, PREFIX_EMAIL,
                        PREFIX_PHONE, PREFIX_PROGRESS));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid id
        assertParseFailure(parser, INVALID_ID_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid project
        assertParseFailure(parser, INVALID_PROJECT_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PROJECT));

        // invalid progress
        assertParseFailure(parser, INVALID_PROGRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PROGRESS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid id
        assertParseFailure(parser, validExpectedPersonString + INVALID_ID_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid project
        assertParseFailure(parser, validExpectedPersonString + INVALID_PROJECT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PROJECT));

        // invalid progress
        assertParseFailure(parser, validExpectedPersonString + INVALID_PROGRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PROGRESS));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ID_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + PROJECT_DESC_AMY + PROGRESS_DESC_AMY, new AddCommand(expectedPerson));

        // progress omitted
        Person expectedIdaPerson = new PersonBuilder(IDA).withTags().build();
        assertParseSuccess(parser, NAME_DESC_IDA + ID_DESC_IDA + PHONE_DESC_IDA + EMAIL_DESC_IDA
                + PROJECT_DESC_IDA, new AddCommand(expectedIdaPerson));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB, expectedMessage);

        // missing id prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ID_BOB + EMAIL_DESC_BOB + PROJECT_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + PROJECT_DESC_BOB, expectedMessage);

        // missing project prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_PROJECT_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ID_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_PROJECT_BOB, expectedMessage);
    }

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ID_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Id.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid project
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_PROJECT_DESC + PROGRESS_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Project.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_PROJECT_DESC + PROGRESS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid progress
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_DESC_BOB + INVALID_PROGRESS_DESC + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Progress.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + PROJECT_DESC_BOB + PROGRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
