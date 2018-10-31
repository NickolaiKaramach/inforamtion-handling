package edu.etc.by.karamach.action;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Part;
import edu.etc.by.karamach.composite.Sentence;
import edu.etc.by.karamach.composite.Text;

import java.util.Comparator;
import java.util.List;

public class SortManager {
    private Text root;

    public SortManager(Text root) {
        this.root = root;
    }

    public void sortPart(Comparator<AbstractSyntaxObject> comparator) {
        root.getChildren().sort(comparator);
    }

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
    }

}
