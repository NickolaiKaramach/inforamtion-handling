package edu.etc.by.karamach.comparator;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Lexeme;

import java.util.Comparator;

public class LexemesBySymbAndAlphComparator implements Comparator<AbstractSyntaxObject> {

    private static final int ZERO = 0;
    private static final int POSITIVE_NUM = 1;
    private static final int NEGATIVE_NUM = -1;
    private char presetChar;

    public LexemesBySymbAndAlphComparator(char presetChar) {
        this.presetChar = presetChar;
    }

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
            return NEGATIVE_NUM;
        } else if (symbolAmountInLexeme1 == symbolAmountInLexeme2) {
            //TODO: QUESTION REPLACE BY THEN COMPARING?

            return lexeme1.getValue().compareToIgnoreCase(lexeme2.getValue());
        } else {
            return POSITIVE_NUM;
        }
    }

}
