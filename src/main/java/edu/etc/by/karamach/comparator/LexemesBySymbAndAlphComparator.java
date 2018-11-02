package edu.etc.by.karamach.comparator;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Lexeme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * Comparator of lexemes
 *
 * @author Nickolai Karamach
 */
public class LexemesBySymbAndAlphComparator implements Comparator<AbstractSyntaxObject> {
    private static final Logger logger = LogManager.getLogger("default");

    private static final int ZERO = 0;
    private static final int POSITIVE_NUM = 1;
    private static final int NEGATIVE_NUM = -1;
    private char presetChar;

    public LexemesBySymbAndAlphComparator(char presetChar) {
        this.presetChar = presetChar;
    }

    /**
     * Comparing two lexemes
     *
     * <p>
     * If lexeme1 have bigger amount of
     * preset symbol,then return -1
     * If lexeme2 have bigger amount of
     * preset symbol, then return 1
     * </p>
     *
     * <p>
     * If lexemes have equal size then
     * comparing by alphabet
     * </p>
     *
     * @param o1 first object to compare
     * @param o2 second object to compare
     */
    @Override
    public int compare(AbstractSyntaxObject o1, AbstractSyntaxObject o2) {
        Lexeme lexeme1;
        Lexeme lexeme2;

        if ((o1.getClass().equals(o2.getClass())) &&
                (o1.getClass().equals(Lexeme.class))) {

            lexeme1 = (Lexeme) o1;
            lexeme2 = (Lexeme) o2;

        } else {

            logger.debug(
                    getClass().getSimpleName() + " looking for: lexeme" +
                            ",  but receive: " + o1.getClass().getSimpleName() +
                            "and: " + o2.getClass().getSimpleName());

            return ZERO;
        }

        int symbolAmountInLexeme1 = 0;

        for (char currentChar : lexeme1.getValue().toCharArray()) {

            if (currentChar == presetChar) {
                symbolAmountInLexeme1++;
            }

        }

        int symbolAmountInLexeme2 = 0;

        for (char currentChar : lexeme2.getValue().toCharArray()) {

            if (currentChar == presetChar) {
                symbolAmountInLexeme2++;
            }

        }

        if (symbolAmountInLexeme1 > symbolAmountInLexeme2) {

            logger.trace(
                    "Amount of \"" + presetChar + "\" in \"" +
                            lexeme1.getValue() + "\" is bigger than in: \""
                            + lexeme2.getValue() + "\"");

            return NEGATIVE_NUM;

        } else if (symbolAmountInLexeme1 == symbolAmountInLexeme2) {
            //TODO: QUESTION: REPLACE BY THEN COMPARING METHOD?

            int compareToIgnoreCase = lexeme1.getValue().compareToIgnoreCase(lexeme2.getValue());

            logger.trace(
                    "\"" + lexeme1.getValue() + "\" and \"" + lexeme2.getValue() +
                            "\" have equal amount of \"" + presetChar
                            + "\", then String.compareToIgnoreCase returns: " + compareToIgnoreCase);

            return compareToIgnoreCase;

        } else {

            logger.trace(
                    "Amount of \"" + presetChar + "\" in \"" +
                            lexeme2.getValue() + "\" is bigger than in: \"" +
                            lexeme1.getValue() + "\"");

            return POSITIVE_NUM;
        }
    }

}
