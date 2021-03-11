package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FlashBack;
import seedu.address.model.ReadOnlyFlashBack;
import seedu.address.model.flashcard.Flashcard;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableFlashBack {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedFlashcard> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFlashBack(@JsonProperty("persons") List<JsonAdaptedFlashcard> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableFlashBack(ReadOnlyFlashBack source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedFlashcard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashBack toModelType() throws IllegalValueException {
        FlashBack flashBack = new FlashBack();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : persons) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (flashBack.hasPerson(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            flashBack.addPerson(flashcard);
        }
        return flashBack;
    }

}
