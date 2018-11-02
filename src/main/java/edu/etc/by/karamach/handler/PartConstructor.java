package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Part parser
 *
 * @author Nickolai Karamach
 */
public class PartConstructor implements InformationConstructor {
    private static final Logger logger = LogManager.getLogger("default");

    private static final String QUESTION_REGEX = "\\?";
    private static final String EXCLAMATION_REGEX = "!";
    private static final String SENTENCE_END_MARK = "*end*";
    private static final String DOTS_REGEX = "((\\.){3})";
    private static final String SINGLE_DOT_REGEX = "(\\.)(([^*.])|($))";
    private static final String SENTENCE_END_MARK_REGEX = "\\*end\\*(\\s*)";
    private InformationConstructor nextHandler;

    public PartConstructor() {
    }

    /**
     * Setting next handler
     *
     * <p>
     * If the next handler will be provided,
     * Constructor will use it to construct part.
     * </p>
     *
     * @param nextHandler handler, logically next to part constructor
     */

    public PartConstructor(InformationConstructor nextHandler) {
        setNext(nextHandler);
    }

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
     * it will use it to divine part to sentence
     * </p>
     *
     * @param part string to create a part from it
     * @return New abstract syntax object represented as part
     */
    public Part construct(String part) {
        Part newPart = new Part();
        logger.trace("Creating new empty part syntax object");

        if (nextHandler != null) {

            String[] sentences = parsePartToSentence(part);
            logger.debug("Successfully parsed part to sentences");

            for (String sentence : sentences) {

                newPart.add(nextHandler.construct(sentence));
                logger.trace("Successfully added sentence to part");

            }

        } else {

            newPart.setValue(part);
            logger.trace("Current part(" + newPart.print() + ") is the leaf item");

        }

        return newPart;
    }

    /**
     * Parsing: parse part to sentences
     *
     * @param part used as a part string representation
     * @return array of sentences
     */
    private String[] parsePartToSentence(String part) {

        part = part.replaceAll(QUESTION_REGEX, " ?" + SENTENCE_END_MARK);
        part = part.replaceAll(EXCLAMATION_REGEX, " !" + SENTENCE_END_MARK);
        part = part.replaceAll(DOTS_REGEX, " ..." + SENTENCE_END_MARK);
        part = part.replaceAll(SINGLE_DOT_REGEX, " ." + SENTENCE_END_MARK + " ");

        return part.split(SENTENCE_END_MARK_REGEX);
    }
}
