package seedu.address.model.anakindeck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_ANSWER_A;
import static seedu.address.testutil.AnakinTypicalCards.CARD_A;
import static seedu.address.testutil.AnakinTypicalCards.CARD_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.anakindeck.anakinexceptions.CardNotFoundException;
import seedu.address.model.anakindeck.anakinexceptions.DuplicateCardException;
import seedu.address.testutil.AnakinCardBuilder;

public class UniqueCardListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AnakinUniqueCardList uniqueCardList = new AnakinUniqueCardList();

    @Test
    public void contains_nullCard_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.contains(null);
    }

    @Test
    public void contains_cardNotInList_returnsFalse() {
        assertFalse(uniqueCardList.contains(CARD_A));
    }

    @Test
    public void contains_CardInList_returnsTrue() {
        uniqueCardList.add(CARD_A);
        assertTrue(uniqueCardList.contains(CARD_A));
    }

    @Test
    public void contains_cardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCardList.add(CARD_A);
        AnakinCard editedCard_A = new AnakinCardBuilder(CARD_A).withAnswer(VALID_ANSWER_A).build();
        assertTrue(uniqueCardList.contains(editedCard_A));
    }

    @Test
    public void add_nullCard_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.add(null);
    }

    @Test
    public void add_duplicateCard_throwsDuplicateCardException() {
        uniqueCardList.add(CARD_A);
        thrown.expect(DuplicateCardException.class);
        uniqueCardList.add(CARD_A);
    }

    @Test
    public void setCard_nullTargetCard_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.setCard(null, CARD_A);
    }

    @Test
    public void setCard_nullEditedCard_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.setCard(CARD_A, null);
    }

    @Test
    public void setCard_targetCardNotInList_throwsCardNotFoundException() {
        thrown.expect(CardNotFoundException.class);
        uniqueCardList.setCard(CARD_B, CARD_B);
    }

    @Test
    public void setCard_editedCardIsSameCard_success() {
        uniqueCardList.add(CARD_A);
        uniqueCardList.setCard(CARD_A, CARD_A);
        AnakinUniqueCardList expectedUniqueCardList = new AnakinUniqueCardList();
        expectedUniqueCardList.add(CARD_A);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasSameIdentity_success() {
        uniqueCardList.add(CARD_A);
        AnakinCard editedCard_A = new AnakinCardBuilder(CARD_A).withAnswer(VALID_ANSWER_A).build();
        uniqueCardList.setCard(CARD_A, editedCard_A);
        AnakinUniqueCardList expectedUniqueCardList = new AnakinUniqueCardList();
        expectedUniqueCardList.add(editedCard_A);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasDifferentIdentity_success() {
        uniqueCardList.add(CARD_A);
        uniqueCardList.setCard(CARD_A, CARD_B);
        AnakinUniqueCardList expectedUniqueCardList = new AnakinUniqueCardList();
        expectedUniqueCardList.add(CARD_B);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasNonUniqueIdentity_throwsDuplicateCardException() {
        uniqueCardList.add(CARD_A);
        uniqueCardList.add(CARD_B);
        thrown.expect(DuplicateCardException.class);
        uniqueCardList.setCard(CARD_A, CARD_B);
    }

    @Test
    public void remove_nullCard_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.remove(null);
    }

    @Test
    public void remove_cardDoesNotExist_throwsCardNotFoundException() {
        thrown.expect(CardNotFoundException.class);
        uniqueCardList.remove(CARD_A);
    }

    @Test
    public void remove_existingCard_removesCard() {
        uniqueCardList.add(CARD_A);
        uniqueCardList.remove(CARD_A);
        AnakinUniqueCardList expectedUniqueCardList = new AnakinUniqueCardList();
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_nullUniqueCardList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.setCards((AnakinUniqueCardList) null);
    }

    @Test
    public void setCards_uniqueCardList_replacesOwnListWithProvidedUniqueCardList() {
        uniqueCardList.add(CARD_A);
        AnakinUniqueCardList expectedUniqueCardList = new AnakinUniqueCardList();
        expectedUniqueCardList.add(CARD_B);
        uniqueCardList.setCards(expectedUniqueCardList);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueCardList.setCards((List<AnakinCard>) null);
    }

    @Test
    public void setCards_list_replacesOwnListWithProvidedList() {
        uniqueCardList.add(CARD_A);
        List<AnakinCard> cardList = Collections.singletonList(CARD_B);
        uniqueCardList.setCards(cardList);
        AnakinUniqueCardList expectedUniqueCardList = new AnakinUniqueCardList();
        expectedUniqueCardList.add(CARD_B);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_listWithDuplicateCards_throwsDuplicateCardException() {
        List<AnakinCard> listWithDuplicateCards = Arrays.asList(CARD_A, CARD_A);
        thrown.expect(DuplicateCardException.class);
        uniqueCardList.setCards(listWithDuplicateCards);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueCardList.asUnmodifiableObservableList().remove(0);
    }
}