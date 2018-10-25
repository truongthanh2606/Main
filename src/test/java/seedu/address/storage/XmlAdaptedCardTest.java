package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedCard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.AnakinTypicalCards.CARD_A;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinQuestion;
import seedu.address.testutil.Assert;

public class XmlAdaptedCardTest {
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String VALID_QUESTION = CARD_A.getQuestion().toString();
    private static final String VALID_ANSWER = CARD_A.getAnswer().toString();

    @Test
    public void toModelType_validCardDetails_returnsDeck() throws Exception {
        XmlAdaptedCard card = new XmlAdaptedCard(CARD_A);
        assertEquals(CARD_A, card.toModelType());
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(INVALID_QUESTION, VALID_ANSWER);
        String expectedMessage = AnakinQuestion.MESSAGE_QUESTION_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(null, VALID_ANSWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AnakinQuestion.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(VALID_QUESTION, INVALID_ANSWER);
        String expectedMessage = AnakinAnswer.MESSAGE_ANSWER_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(VALID_QUESTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AnakinAnswer.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }
}