package edu.etc.by.karamach.iteration;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;
import edu.etc.by.karamach.composite.Part;
import edu.etc.by.karamach.composite.Text;

import java.util.List;

public class PartIterator implements Iterator<Part> {
    private AbstractSyntaxObject root;
    private List<AbstractSyntaxObject> collection;

    public PartIterator(Text collectionRoot) {
        root = collectionRoot;
        collection = collectionRoot.getChildren();
    }

    @Override
    public Part next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
