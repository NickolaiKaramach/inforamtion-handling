package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.Part;

public class PartConstructor implements InformationConstructor {
    private static final String QUESTION_REGEX = "\\?";
    private static final String EXCLAMATION_REGEX = "!";
    private static final String SENTENCE_END_MARK = "*end*";
    private static final String DOTS_REGEX = "((\\.){3})";
    private static final String SINGLE_DOT_REGEX = "(\\.)(([^*.\\s])|($))";
    private static final String SENTENCE_END_MARK_REGEX = "\\*end\\*(\\s*)";
    private InformationConstructor nextHandler;

    public PartConstructor() {
    }

    public PartConstructor(InformationConstructor nextHandler) {
        setNext(nextHandler);
    }

    public void setNext(InformationConstructor nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Part construct(String part) {
        Part newPart = new Part();

        if (nextHandler != null) {
            String[] sentences = parsePartToSentence(part);
            for (String sentence : sentences) {
                newPart.add(nextHandler.construct(sentence));
            }
        } else {
            newPart.setValue(part);
        }

        return newPart;
    }

    private String[] parsePartToSentence(String part) {
        part = part.replaceAll(QUESTION_REGEX, " ?" + SENTENCE_END_MARK);
        part = part.replaceAll(EXCLAMATION_REGEX, " !" + SENTENCE_END_MARK);
        part = part.replaceAll(DOTS_REGEX, " ..." + SENTENCE_END_MARK);
        part = part.replaceAll(SINGLE_DOT_REGEX, " ." + SENTENCE_END_MARK + " ");
        return part.split(SENTENCE_END_MARK_REGEX);
    }
}
