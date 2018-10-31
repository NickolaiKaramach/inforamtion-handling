package edu.etc.by.karamach.comparator;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Part;

import java.util.Comparator;

public class PartBySentenceAmountComparator implements Comparator<AbstractSyntaxObject> {

    private static final int POSITIVE_NUM = 1;
    private static final int NEGATIVE_NUM = -1;
    private static final int ZERO = 0;

    @Override
    public int compare(AbstractSyntaxObject o1, AbstractSyntaxObject o2) {
        Part part1;
        Part part2;

        if ((o1.getClass().equals(o2.getClass())) &&
                (o1.getClass().equals(Part.class))) {

            part1 = (Part) o1;
            part2 = (Part) o2;

        } else {
            return ZERO;
        }

        int sentenceAmount1 = part1.getChildren().size();
        int sentenceAmount2 = part2.getChildren().size();

        if (sentenceAmount1 > sentenceAmount2) {
            return POSITIVE_NUM;
        } else if (sentenceAmount1 < sentenceAmount2) {
            return NEGATIVE_NUM;
        } else {
            return ZERO;
        }
    }
}
