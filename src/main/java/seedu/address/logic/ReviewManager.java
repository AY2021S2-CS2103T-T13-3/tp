package seedu.address.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;

public class ReviewManager {
    private final ArrayList<Flashcard> shuffleDeckOfCards;
    private final Logic logic;
    private int currentIndex;

    /**
     * Creates a {@code ReviewManager} to handle Review Mode with specified logic;
     */
    public ReviewManager(Logic logic) {
        this.logic = logic;
        ObservableList<Flashcard> originalDeckOfCards = logic.getFilteredPersonList();
        this.shuffleDeckOfCards = new ArrayList<>(originalDeckOfCards);
        Collections.shuffle(shuffleDeckOfCards, new Random());
        this.currentIndex = 0;
    }

    /**
     * Gets the size of the list of flashcards.
     */
    public int getFlashcardDeckSize() {
        return shuffleDeckOfCards.size();
    }

    /**
     * Increase the current index by 1.
     */
    public void incrementCurrentIndex() {
        currentIndex++;
    }

    /**
     * Decreases the current index by 1.
     */
    public void decrementCurrentIndex() {
        currentIndex--;
    }

    /**
     * Checks if there is a next flashcard after the current flashcard.
     */
    public boolean hasNextFlashcard() {
        return currentIndex < shuffleDeckOfCards.size() - 1;
    }

    /**
     * Checks if there is a previous flashcard after the current flashcard.
     */
    public boolean hasPreviousFlashcard() {
        return currentIndex > 0;
    }

    /**
     * Gets the flashcard corresponds to the current index.
     */
    public Flashcard getCurrentFlashcard() {
        return shuffleDeckOfCards.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
