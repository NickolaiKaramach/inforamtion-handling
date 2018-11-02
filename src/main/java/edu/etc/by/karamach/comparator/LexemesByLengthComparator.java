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
public class LexemesByLengthComparator implements Comparator<AbstractSyntaxObject> {
    private static final Logger logger = LogManager.getLogger("default");

    private static final int ZERO = 0;
    private static final int NEGATIVE_NUM = -1;
    private static final int POSITIVE_NUM = 1;

    /**
     * Comparing two lexemes
     *
     * <p>
     * If lexeme1 have bigger length returns 1
     * If lexeme2 have bigger length returns -1
     * If lexemes have equal size returns 0
     * </p>
     *
     * @param o1 first object to compare
     * @param o2 second object to compare
     */
    @Override
    public int compare(AbstractSyntaxObject o1, AbstractSyntaxObject o2) {
        Lexeme lexeme1;
        Lexeme lexeme2;

        //TODO: QUESTION: Can we do this in comparator?
        if ((o1.getClass().equals(o2.getClass())) &&
                (o1.getClass().equals(Lexeme.class))) {

            lexeme1 = (Lexeme) o1;
            lexeme2 = (Lexeme) o2;

        } else {

            logger.debug(
                    getClass().getSimpleName() + " waits for: lexeme" +
                            ",  but receive: " + o1.getClass().getSimpleName() +
                            "and: " + o2.getClass().getSimpleName());

            return ZERO;
        }

        int worldLength1 = lexeme1.getValue().length();
        int worldLength2 = lexeme2.getValue().length();

        if (worldLength1 > worldLength2) {

            logger.trace(
                    "Lexeme: \"" + lexeme1.getValue() +
                            "\" have bigger length than: \"" + lexeme2.getValue() + "\"");

            return POSITIVE_NUM;

        } else if (worldLength1 < worldLength2) {

            logger.trace("Lexeme: \"" + lexeme2.getValue() +
                    "\" have bigger length than: \"" + lexeme1.getValue() + "\"");

            return NEGATIVE_NUM;

        }

        logger.trace("Lexeme: \"" + lexeme1.getValue() +
                "\" have equal size with: \"" + lexeme2.getValue() + "\"");

        return ZERO;
    }
}
