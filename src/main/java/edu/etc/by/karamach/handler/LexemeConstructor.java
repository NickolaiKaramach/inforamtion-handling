package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Lexeme;

public class LexemeConstructor implements InformationConstructor {

    private InformationConstructor nextHandler;

    public LexemeConstructor() {
    }

    public LexemeConstructor(InformationConstructor nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void setNext(InformationConstructor nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public AbstractSyntaxObject construct(String lexeme) {
        Lexeme newLexeme = new Lexeme();

        if (nextHandler == null) {
            newLexeme.setValue(lexeme);
        } else {
            //parseToSymbols
        }
        return newLexeme;
    }
}
