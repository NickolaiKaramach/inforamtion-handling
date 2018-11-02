package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.Sentence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Sentence parser
 *
 * @author Nickolai Karamach
 */

public class SentenceConstructor implements InformationConstructor {
    private static final Logger logger = LogManager.getLogger("default");
    private InformationConstructor nextHandler;

    private static final String COMMA_REGEX = ",";
    private static final String SEMI_COLON_REGEX = ";";
    private static final String COLON_REGEX = ":";
    private static final String QUOTES_REGEX = "\"";
    private static final String BRACE_OPEN_REGEX = "\\(";
    private static final String BRACE_CLOSE_REGEX = "\\)";
    private static final String ONE_OR_MORE_SPACES_REGEX = "\\s+";


    public SentenceConstructor() {
    }

    /**
     * Setting next handler
     *
     * <p>
     * If the next handler will be provided,
     * Constructor will use it to construct sentence.
     * </p>
     *
     * @param nextHandler handler, logically next to sentence constructor
     */
    public SentenceConstructor(InformationConstructor nextHandler) {
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
     * it will use it to divine sentence to lexemes
     * </p>
     *
     * @param sentence string to create a sentence from it
     * @return New abstract syntax object represented as sentence
     */
    public Sentence construct(String sentence) {
        //TODO: QUESTION: Do we need to create new obj with STRING value, or it's enough to keep info about children?

        Sentence newSentence = new Sentence();
        logger.trace("Creating new empty sentence syntax object");

        if (nextHandler != null) {

            String[] lexemes = parseSentenceToLexemes(sentence);
            logger.debug("Successfully parsed sentence to lexemes");

            for (String currentLexeme : lexemes) {

                newSentence.add(nextHandler.construct(currentLexeme));
                logger.trace("Successfully added lexeme to sentence");

            }

        } else {

            newSentence.setValue(sentence);
            logger.trace("Current sentence(" + newSentence.print() + ") is the leaf item");

        }

        return newSentence;
    }

    /**
     * Parsing: parse sentence to lexemes
     *
     * @param sentence used as a sentence string representation
     * @return array of lexemes
     */
    private String[] parseSentenceToLexemes(String sentence) {
        //TODO: QUESTION: Is it a literal - "?" - do we need to extract it to final static?

        sentence = sentence.replaceAll(COMMA_REGEX, " , ");
        sentence = sentence.replaceAll(SEMI_COLON_REGEX, " ; ");
        sentence = sentence.replaceAll(COLON_REGEX, " : ");
        sentence = sentence.replaceAll(QUOTES_REGEX, " \" ");
        sentence = sentence.replaceAll(BRACE_OPEN_REGEX, " ( ");
        sentence = sentence.replaceAll(BRACE_CLOSE_REGEX, " ) ");
        sentence = sentence.trim();

        return sentence.split(ONE_OR_MORE_SPACES_REGEX);
    }
}
