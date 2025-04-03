package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String id;
    private final String phone;
    private final String email;
    private final String project;
    private final String progress;
    private final String log;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
            @JsonProperty("id") String id,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("project") String project,
            @JsonProperty("progress") String progress,
            @JsonProperty("log") String log,
            @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.project = project;
        this.progress = progress;
        this.log = log;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        id = source.getId().id;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        project = source.getProject().value;
        progress = source.getProgress().value;
        log = source.getLog().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (project == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Project.class.getSimpleName()));
        }
        if (!Project.isValidProject(project)) {
            throw new IllegalValueException(Project.MESSAGE_CONSTRAINTS);
        }
        final Project modelProject = new Project(project);

        if (progress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Progress.class.getSimpleName()));
        }
        if (!Progress.isValidProgress(progress)) {
            throw new IllegalValueException(Progress.MESSAGE_CONSTRAINTS);
        }
        final Progress modelProgress = new Progress(progress);

        if (log == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Log.class.getSimpleName()));
        }
        final Log modelLog = new Log(log);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelId, modelPhone, modelEmail, modelProject, modelProgress, modelLog, modelTags);
    }

}
