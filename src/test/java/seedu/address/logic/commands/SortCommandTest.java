package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalDecks.DECK_A;
import static seedu.address.testutil.TypicalDecks.DECK_B;

import org.junit.Test;

import seedu.address.model.Anakin;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.AnakinBuilder;

public class SortCommandTest {

    @Test
    public void execute_sortDeckList() {
        Anakin anakin = new AnakinBuilder().withDeck(DECK_B).withDeck(DECK_A).build();
        Anakin expectedAnakin = new AnakinBuilder().withDeck(DECK_A).withDeck(DECK_B).build();
        anakin.sort();
        assertEquals(anakin, expectedAnakin);
    }

    @Test
    public void execute_sortCardList() {
        Deck deck = getTypicalDeck();
        Anakin anakin = new AnakinBuilder().withDeck(deck).build();
        anakin.getIntoDeck(deck);
        anakin.sort();

        Deck expectedDeck = getTypicalDeck();
        expectedDeck.getCards().sort();

        assertEquals(deck, expectedDeck);
    }
}