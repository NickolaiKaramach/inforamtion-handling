package edu.etc.by.karamach.comparator;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Lexeme;

import java.util.Comparator;

public class LexemesByLengthComparator implements Comparator<AbstractSyntaxObject> {

    private static final int ZERO = 0;
    private static final int NEGATIVE_NUM = -1;
    private static final int POSITIVE_NUM = 1;

    @Override
    public int compare(AbstractSyntaxObject o1, AbstractSyntaxObject o2) {
        Lexeme lexeme1;
        Lexeme lexeme2;


        if ((o1.getClass().equals(o2.getClass())) &&
                (o1.getClass().equals(Lexeme.class))) {

            lexeme1 = (Lexeme) o1;
            lexeme2 = (Lexeme) o2;

        } else {
            return ZERO;
        }

        int worldLength1 = lexeme1.getValue().length();
        int worldLength2 = lexeme2.getValue().length();

        if (worldLength1 > worldLength2) {
            return POSITIVE_NUM;
        } else if (worldLength1 < worldLength2) {
            return NEGATIVE_NUM;
        }
        return ZERO;
    }
}
