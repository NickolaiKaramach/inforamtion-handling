package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.Text;

public class TextConstructor implements InformationConstructor {
    private InformationConstructor nextHandler;

    private static final int SPACES_TO_TAB = 4;

    private static final String TAB_REGEX = "(\t)";
    private static final String NEW_LINE_REGEX = "(\n)";
    private static final String OR_REGEX = "|";
    private static final String SPACE_REGEX = "(\\s)";
    private static final String SPACE_TAB_REGEX = "(" + SPACE_REGEX + "{" + SPACES_TO_TAB + "})";
    private static final String SPACE_OR_TAB_REGEX = "(" + TAB_REGEX + OR_REGEX + SPACE_TAB_REGEX + ")";

    public TextConstructor(InformationConstructor nextHandler) {
        setNext(nextHandler);
    }

    public TextConstructor() {
    }

    public void setNext(InformationConstructor nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Divide
     *
     * @param text just single string representing a whole text
     * @return one composite syntax object - Text
     */
    public Text construct(String text) {
        Text newText = new Text();

        if (nextHandler != null) {
            String[] parts = parseTextToParts(text);
            for (String part : parts) {
                newText.add(nextHandler.construct(part));
            }
        } else {
            newText.setValue(text);
        }

        return newText;
    }

    private String[] parseTextToParts(String stringText) {
        stringText = stringText.trim();
        stringText = stringText.replaceFirst(TAB_REGEX, SPACE_REGEX);
        return stringText.split(NEW_LINE_REGEX + SPACE_OR_TAB_REGEX);
    }
}
