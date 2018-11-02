package edu.etc.by.karamach.action;

import edu.etc.by.karamach.comparator.LexemesByLengthComparator;
import edu.etc.by.karamach.comparator.LexemesBySymbAndAlphComparator;
import edu.etc.by.karamach.comparator.PartBySentenceAmountComparator;
import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Text;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;

public class SortManagerTest {
    private static final char PRESET_CHAR = 'e';
    private SortManager sortManager = new SortManager();

    @Test(dataProvider = "getPartsSortData", dataProviderClass = SortManagerTestData.class, groups = {"partSort"})
    public void partSortTest(Text input, Text expected) {

        sortManager.setRoot(input);
        Comparator<AbstractSyntaxObject> comparator = new PartBySentenceAmountComparator();

        sortManager.sortPart(comparator);
        Assert.assertEquals(input, expected);
    }

    @Test(dataProvider = "getLexemesByLengthSortData", dataProviderClass = SortManagerTestData.class,
            groups = {"lexemeSort"})
    public void lexemeByLengthSortTest(Text input, Text expected) {
        sortManager.setRoot(input);
        Comparator<AbstractSyntaxObject> comparator = new LexemesByLengthComparator();

        sortManager.sortLexemes(comparator);
        Assert.assertEquals(input, expected);
    }

    @Test(dataProvider = "getLexemesBySymbAndAlphSortData", dataProviderClass = SortManagerTestData.class,
            groups = {"lexemeSort"})
    public void lexemeCompositeSortTest(Text input, Text expected) {
        sortManager.setRoot(input);
        Comparator<AbstractSyntaxObject> comparator = new LexemesBySymbAndAlphComparator(PRESET_CHAR);

        sortManager.sortLexemes(comparator);
        Assert.assertEquals(input, expected);
    }
}