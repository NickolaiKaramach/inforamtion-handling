package edu.etc.by.karamach.composite;

import java.util.List;

public interface AbstractSyntaxObject {
    String print();

    List<AbstractSyntaxObject> getChildren();
}
