package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRESS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ProgressCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Progress;

/**
 * Parses input arguments and creates a new ProgressCommand object
 */
public class ProgressCommandParser implements Parser<ProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ProgressCommand
     * and returns a ProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProgressCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PROGRESS);

        if (argMultimap.getValue(PREFIX_PROGRESS).isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PROGRESS);
        Progress progress = ParserUtil.parseProgress(
                argMultimap.getValue(PREFIX_PROGRESS).get());

        return new ProgressCommand(index, progress);
    }
}
