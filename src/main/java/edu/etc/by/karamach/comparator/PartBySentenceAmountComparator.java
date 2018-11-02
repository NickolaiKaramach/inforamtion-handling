package edu.etc.by.karamach.comparator;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * Comparator of parts
 *
 * @author Nickolai Karamach
 */
public class PartBySentenceAmountComparator implements Comparator<AbstractSyntaxObject> {
    private static final Logger logger = LogManager.getLogger("default");
    private static final int POSITIVE_NUM = 1;
    private static final int NEGATIVE_NUM = -1;
    private static final int ZERO = 0;

    /**
     * Comparing two parts
     *
     * <p>
     * If part1 have more sentences
     * then return 1
     * If part2 have more sentences
     * then return -1
     * If both parts have equal size
     * then return 0
     * </p>
     *
     * @param o1 first part to compare
     * @param o2 second part to compare
     */
    @Override
    public int compare(AbstractSyntaxObject o1, AbstractSyntaxObject o2) {
        Part part1;
        Part part2;

        if ((o1.getClass().equals(o2.getClass())) &&
                (o1.getClass().equals(Part.class))) {

            part1 = (Part) o1;
            part2 = (Part) o2;

        } else {

            logger.debug(
                    getClass().getSimpleName() + " looking for: lexeme" +
                            ",  but receive: " + o1.getClass().getSimpleName() +
                            "and: " + o2.getClass().getSimpleName());

            return ZERO;
        }

        int sentenceAmount1 = part1.getChildren().size();
        int sentenceAmount2 = part2.getChildren().size();

        if (sentenceAmount1 > sentenceAmount2) {

            logger.trace(
                    "\"" + part1.print() + "\"(" + sentenceAmount1 +
                            ") have bigger amount of sentence than " +
                            "\"" + part2.print() + "\"(" + sentenceAmount2 + ")");

            return POSITIVE_NUM;

        } else if (sentenceAmount1 < sentenceAmount2) {

            logger.trace(
                    "\"" + part2.print() + "\"(" + sentenceAmount2 +
                            ") have bigger amount of sentence than " +
                            "\"" + part1.print() + "\"(" + sentenceAmount1 + ")");

            return NEGATIVE_NUM;

        } else {

            logger.trace(
                    "\"" + part2.print() + "\"(" + sentenceAmount2 +
                            ") have equal amount of sentence as " +
                            "\"" + part1.print() + "\"(" + sentenceAmount1 + ")");

            return ZERO;
        }
    }
}
