package edu.etc.by.karamach.action;

import edu.etc.by.karamach.composite.Part;
import edu.etc.by.karamach.composite.Text;
import edu.etc.by.karamach.exception.ReceiverException;
import edu.etc.by.karamach.handler.LexemeConstructor;
import edu.etc.by.karamach.handler.PartConstructor;
import edu.etc.by.karamach.handler.SentenceConstructor;
import edu.etc.by.karamach.handler.TextConstructor;
import edu.etc.by.karamach.receiver.StringFileReader;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class SortManagerTestData {
    private static final StringFileReader reader = new StringFileReader();
    private static final TextConstructor textConstructor = new TextConstructor();
    private static final PartConstructor partConstructor = new PartConstructor();
    private static final SentenceConstructor sentenceConstructor = new SentenceConstructor();
    private static final LexemeConstructor lexemeConstructor = new LexemeConstructor();

    private static final int AMOUNT_OF_TEST_FILES = 3;
    private static final String NEW_LINE_CHAR = "\n";
    private static final String TXT_EXTENSION = ".txt";

    private static final String SORT_PARTS_TEXT_PATH = "repository/test/sort/texts/partSort/sortTestText";
    private static final String SORT_PARTS_ANSWER_PATH = "repository/test/sort/answer/partSort/sortPartAnswer";

    private static final String SORT_LEXEMES_BY_LENGTH_ANSWER_PATH = "repository/test/sort/answer/lexemeSort/sortByLengthAnswer";
    private static final String SORT_LEXEMES_BY_LENGTH_TEXT_PATH = "repository/test/sort/texts/lexemeSort/sortTestText";

    private static final String COMPOSITE_SORT_LEXEMES_TEXT_PATH = "repository/test/sort/texts/lexemeSort/sortByAmountOfSymbolThenByAlph/sortTestText";
    private static final String COMPOSITE_SORT_LEXEMES_ANSWER_PATH = "repository/test/sort/answer/lexemeSort/compositeSortAnswer";

    //TODO: QUESTION: Can we use it(static) in this case?
    static {
        textConstructor.setNext(partConstructor);
        partConstructor.setNext(sentenceConstructor);
        sentenceConstructor.setNext(lexemeConstructor);
    }

    @DataProvider(name = "getPartsSortData")
    public Object[][] getSortPartTestData() throws ReceiverException {

        List<Text> allTexts = new ArrayList<>();
        List<Text> answersPartsSort = new ArrayList<>();

        takePartDataFromFiles(allTexts, answersPartsSort);

        return new Object[][]{
                {allTexts.get(0), answersPartsSort.get(0)},
                {allTexts.get(1), answersPartsSort.get(1)},
                {allTexts.get(2), answersPartsSort.get(2)}
        };
    }

    @DataProvider(name = "getLexemesByLengthSortData")
    public Object[][] getLexemesByLengthSortData() throws ReceiverException {

        String textPath = SORT_LEXEMES_BY_LENGTH_TEXT_PATH;
        String answerPath = SORT_LEXEMES_BY_LENGTH_ANSWER_PATH;

        return getActualExpectedObjectsFromFile(textPath, answerPath);
    }

    @DataProvider(name = "getLexemesBySymbAndAlphSortData")
    public Object[][] getLexemesBySymbAndAlphSortData() throws ReceiverException {

        String textPath = COMPOSITE_SORT_LEXEMES_TEXT_PATH;
        String answerPath = COMPOSITE_SORT_LEXEMES_ANSWER_PATH;


        return getActualExpectedObjectsFromFile(textPath, answerPath);
    }


    /*
     *
     *
     *  Private methods there
     *
     *
     *
     */

    private Object[][] getActualExpectedObjectsFromFile(String textPath, String answerPath) throws ReceiverException {

        List<Text> allTexts = new ArrayList<>();
        List<Text> allAnswers = new ArrayList<>();

        fillDataFromFile(allTexts, allAnswers, textPath, answerPath);

        return new Object[][]{
                {allTexts.get(0), allAnswers.get(0)},
                {allTexts.get(1), allAnswers.get(1)},
                {allTexts.get(2), allAnswers.get(2)}
        };
    }

    private void fillDataFromFile(List<Text> allTexts, List<Text> allAnswers, String textPath, String answerPath) throws ReceiverException {

        for (int i = 1; i <= AMOUNT_OF_TEST_FILES; i++) {

            StringBuilder totalText = getWholeFile(textPath + i + TXT_EXTENSION);
            allTexts.add(textConstructor.construct(totalText.toString()));

            prepareTextByReadingSentences(allAnswers, answerPath + i + TXT_EXTENSION);
        }
    }

    private void takePartDataFromFiles(List<Text> allPartTexts, List<Text> answersPartsSort) throws ReceiverException {

        for (int i = 1; i <= AMOUNT_OF_TEST_FILES; i++) {

            StringBuilder totalText = getWholeFile(SORT_PARTS_TEXT_PATH + i + TXT_EXTENSION);

            StringBuilder totalPartAnswer = getWholeFile(SORT_PARTS_ANSWER_PATH + i + TXT_EXTENSION);

            allPartTexts.add(textConstructor.construct(totalText.toString()));
            answersPartsSort.add(textConstructor.construct(totalPartAnswer.toString()));
        }
    }

    private StringBuilder getWholeFile(String uri) throws ReceiverException {
        List<String> strings;
        strings = reader.readAll(uri);

        StringBuilder totalText = new StringBuilder();

        for (String string : strings) {

            totalText.append(string);
            totalText.append(NEW_LINE_CHAR);

        }

        return totalText;
    }

    private void prepareTextByReadingSentences(List<Text> allAnswers, String uri) throws ReceiverException {
        Text answer = new Text();
        Part answerPart = new Part();

        List<String> nonParsedAnswers;
        nonParsedAnswers = reader.readAll(uri);

        for (String string : nonParsedAnswers) {
            answerPart.add(sentenceConstructor.construct(string));
        }

        answer.add(answerPart);
        allAnswers.add(answer);
    }
}
