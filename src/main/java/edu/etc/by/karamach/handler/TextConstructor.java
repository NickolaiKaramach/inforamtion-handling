package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Text parser
 *
 * @author Nickolai Karamach
 */
public class TextConstructor implements InformationConstructor {
    private InformationConstructor nextHandler;
    private static final Logger logger = LogManager.getLogger("default");

    private static final int SPACES_TO_TAB = 4;

    private static final String TAB_REGEX = "(\t)";
    private static final String NEW_LINE_REGEX = "(\n)";
    private static final String OR_REGEX = "|";
    private static final String SPACE_REGEX = "(\\s)";
    private static final String SPACE_TAB_REGEX = "(" + SPACE_REGEX + "{" + SPACES_TO_TAB + "})";
    private static final String SPACE_OR_TAB_REGEX = "(" + TAB_REGEX + OR_REGEX + SPACE_TAB_REGEX + ")";

    public TextConstructor(InformationConstructor nextHandler) {
        setNext(nextHandler);
    }

    public TextConstructor() {
    }

    /**
     * Setting next handler
     *
     * <p>
     * If the next handler will be provided,
     * Constructor will use it to construct text.
     * </p>
     *
     * @param nextHandler handler, logically next to text constructor
     */
    public void setNext(InformationConstructor nextHandler) {
        logger.trace("New nextHandler: " +
                nextHandler.getClass().getSimpleName() +
                " , to " + getClass().getSimpleName());

        this.nextHandler = nextHandler;
    }

    /**
     * Construct: create new object from String
     *
     * <p>
     * If constructor has next handler,
     * it will use it to divine text to parts
     * </p>
     *
     * @param text string to create a text from it
     * @return New abstract syntax object represented as text
     */
    public Text construct(String text) {

        Text newText = new Text();
        logger.trace("Creating new empty text syntax object");

        if (nextHandler != null) {

            String[] parts = parseTextToParts(text);
            logger.debug("Successfully parsed text to parts");

            for (String part : parts) {

                newText.add(nextHandler.construct(part));
                logger.trace("Successfully added part to text");

            }
        } else {

            newText.setValue(text);
            logger.trace("Current text(" + newText.print() + ") is the leaf item");

        }

        return newText;
    }

    /**
     * Parsing: parse text to parts
     *
     * @param stringText used as a text string representation
     * @return array of parts
     */
    private String[] parseTextToParts(String stringText) {

        stringText = stringText.trim();
        return stringText.split(NEW_LINE_REGEX + SPACE_OR_TAB_REGEX);
    }
}
