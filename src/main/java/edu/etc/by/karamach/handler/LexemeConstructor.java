package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Lexeme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lexeme parser
 *
 * @author Nickolai Karamach
 */
public class LexemeConstructor implements InformationConstructor {
    private static final Logger logger = LogManager.getLogger("default");
    private InformationConstructor nextHandler;

    public LexemeConstructor() {
    }

    public LexemeConstructor(InformationConstructor nextHandler) {
        setNext(nextHandler);
    }

    /**
     * Setting next handler
     *
     * <p>
     * If the next handler will be provided,
     * Constructor will use it to construct lexeme.
     * </p>
     *
     * @param nextHandler handler, logically next to lexeme constructor
     */
    @Override
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
     * it will use it to divine lexeme to symbols
     * </p>
     *
     * @param lexeme string to create a lexeme from it
     * @return New abstract syntax object represented as lexeme
     */
    @Override
    public AbstractSyntaxObject construct(String lexeme) {

        Lexeme newLexeme = new Lexeme();
        logger.trace("Creating new empty lexeme syntax object");

        if (nextHandler == null) {

            newLexeme.setValue(lexeme);
            logger.trace("Current lexeme(" + newLexeme.print() + ") is the leaf item");

        } else {
            //parseToSymbols
        }

        return newLexeme;
    }
}
