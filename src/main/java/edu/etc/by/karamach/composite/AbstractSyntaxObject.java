package edu.etc.by.karamach.composite;

import java.util.List;

public interface AbstractSyntaxObject {
    /**
     * Get string to print all data
     *
     * @return all data with data of included inner objects
     */
    String print();

    List<AbstractSyntaxObject> getChildren();
}
