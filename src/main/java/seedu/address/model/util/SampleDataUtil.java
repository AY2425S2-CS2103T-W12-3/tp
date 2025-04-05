package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Profiler;
import seedu.address.model.ReadOnlyProfiler;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Log;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Progress;
import seedu.address.model.person.Project;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Profiler} with sample data.
 */
public class SampleDataUtil {
    public static final Log EMPTY_LOG = new Log("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Id("A0289384P"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                new Project("Project Orbiting"), new Progress("30"), EMPTY_LOG,
                getTagSet("Y3", "InfoSys")),
            new Person(new Name("Bernice Yu"), new Id("A0214850K"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Project("Project ReX"), new Progress("21"), EMPTY_LOG,
                getTagSet("Y3", "CS2101")),
            new Person(new Name("Charlotte Oliveiro"), new Id("A0250255M"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Project("Prof-iler"), new Progress(), EMPTY_LOG,
                getTagSet("BZA")),
            new Person(new Name("David Li"), new Id("A0280123C"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Project("Prof-iler"), new Progress("11"), EMPTY_LOG,
                getTagSet("Y4", "CS")),
            new Person(new Name("Irfan Ibrahim"), new Id("A0267893F"), new Phone("92492021"),
                new Email("irfan@example.com"), new Project("WealthVault"), new Progress("48"), EMPTY_LOG,
                getTagSet("Y4")),
            new Person(new Name("Roy Balakrishnan"), new Id("A0280012B"), new Phone("92624417"),
                new Email("royb@example.com"), new Project("TAssist"), new Progress("17"), EMPTY_LOG,
                getTagSet("InfoSec"))
        };
    }

    public static ReadOnlyProfiler getSampleProfiler() {
        Profiler samplePrf = new Profiler();
        for (Person samplePerson : getSamplePersons()) {
            samplePrf.addPerson(samplePerson);
        }
        return samplePrf;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
