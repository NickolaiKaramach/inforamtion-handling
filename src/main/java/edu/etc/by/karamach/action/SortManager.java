package edu.etc.by.karamach.action;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Part;
import edu.etc.by.karamach.composite.Sentence;
import edu.etc.by.karamach.composite.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * Sort action class
 *
 * @author Nickolai Karamach
 */
public class SortManager {
    private static final Logger logger = LogManager.getLogger("default");
    private Text root;

    public SortManager() {
    }

    public SortManager(Text root) {
        logger.trace("A new sort manager have been initialized!");
        this.root = root;
    }

    public Text getRoot() {
        return root;
    }

    public void setRoot(Text root) {
        this.root = root;
    }

    /**
     * Sort all parts by comparator
     *
     * @param comparator will sort all parts
     */
    public void sortPart(Comparator<AbstractSyntaxObject> comparator) {
        root.getChildren().sort(comparator);
        logger.debug("Root text have been successfully sorted by parts using " + comparator.getClass());
    }

    /**
     * Sort all lexemes by comparator
     *
     * @param comparator will sort all lexemes
     */
    //TODO:QUESTION: Can we iterate lexemes this way to sort them
    public void sortLexemes(Comparator<AbstractSyntaxObject> comparator) {
        for (int i = 0; i < root.getChildren().size(); i++) {

            if (root.getChildren() != null) {
                Part currentPart = (Part) root.getChildren().get(i);

                if (currentPart.getChildren() != null) {

                    for (int j = 0; j < currentPart.getChildren().size(); j++) {

                        Sentence currentSentence = (Sentence) currentPart.getChildren().get(j);
                        List<AbstractSyntaxObject> currentLexemes = currentSentence.getChildren();

                        if (currentLexemes != null) {
                            currentLexemes.sort(comparator);
                        }
                    }
                }
            }
        }

        logger.debug("Root text have been successfully sorted by parts using " + comparator.getClass());
    }

}
