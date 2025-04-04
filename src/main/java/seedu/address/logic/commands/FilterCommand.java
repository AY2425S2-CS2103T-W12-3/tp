package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Filters and list all students in Prof-iler whose project contains all the given keywords
 * or whose tag contains any of the given keyword.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters for the students under the "
            + "specified project or tags based on the keywords (case-insensitive) "
            + "and displays them as a list with index numbers. \n"
            + "Parameters: \n"
            + "By Project: " + PREFIX_PROJECT + "KEYWORD [MORE KEYWORDS] \n"
            + "By Tags: " + PREFIX_TAG + "KEYWORD [MORE KEYWORDS] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PROJECT + " Prof-iler \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "W12" + " CS2103T";
    public static final String MESSAGE_ONLY_PROJECT_OR_TAG =
            "Should only have one of " + PREFIX_PROJECT + " or " + PREFIX_TAG;

    private final Predicate<Person> predicate;

    public FilterCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_FILTERED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instaceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
