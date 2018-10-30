package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.Sentence;

public class SentenceConstructor implements InformationConstructor {
    private static final String COMMA_REGEX = ",";
    private static final String SEMI_COLON_REGEX = ";";
    private static final String COLON_REGEX = ":";
    private static final String QUOTES_REGEX = "\"";
    private static final String BRACE_OPEN_REGEX = "\\(";
    private static final String BRACE_CLOSE_REGEX = "\\)";
    private static final String ONE_OR_MORE_SPACES_REGEX = "\\s+";
    private InformationConstructor nextHandler;

    public SentenceConstructor() {
    }

    public SentenceConstructor(InformationConstructor nextHandler) {
        setNext(nextHandler);
    }

    public void setNext(InformationConstructor nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Sentence construct(String sentence) {
        //Do we need to construct with STRING
        Sentence newSentence = new Sentence();

        if (nextHandler != null) {
            String[] lexemes = parseSentenceToLexemes(sentence);
            for (String currentLexeme : lexemes) {
                newSentence.add(nextHandler.construct(currentLexeme));
            }
        } else {
            newSentence.setValue(sentence);
        }

        return newSentence;
    }

    private String[] parseSentenceToLexemes(String sentence) {
        //TODO: QUESTION: Is it a literal??? "?"
        sentence = sentence.replaceAll(COMMA_REGEX, " , ");
        sentence = sentence.replaceAll(SEMI_COLON_REGEX, " ; ");
        sentence = sentence.replaceAll(COLON_REGEX, " : ");
        sentence = sentence.replaceAll(QUOTES_REGEX, " \" ");
        sentence = sentence.replaceAll(BRACE_OPEN_REGEX, " ( ");
        sentence = sentence.replaceAll(BRACE_CLOSE_REGEX, " ) ");

        return sentence.split(ONE_OR_MORE_SPACES_REGEX);
    }
}
