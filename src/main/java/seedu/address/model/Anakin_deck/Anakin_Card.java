package seedu.address.model.Anakin_deck;

import java.util.Objects;

/**
 * Represents a Card inside a Deck.
 */
public class Anakin_Card {

    private final int performance;

    // Identity fields
    private final String question;
    private final String answer;

    public Anakin_Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        performance = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    /**
     * Returns true if 2 cards are the same, or have same identity fields.
     */
    public boolean isSameCard(Anakin_Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard.getQuestion().equals(getQuestion())
                && otherCard.getAnswer().equals(getAnswer());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Anakin_Card)) {
            return false;
        }

        return isSameCard((Anakin_Card) other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(getQuestion())
                .append(" Answer: ")
                .append(getAnswer());
        return builder.toString();
    }
}
