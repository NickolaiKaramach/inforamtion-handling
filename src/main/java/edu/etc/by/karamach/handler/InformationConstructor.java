package edu.etc.by.karamach.handler;

import edu.etc.by.karamach.composite.AbstractSyntaxObject;

public interface InformationConstructor {
    void setNext(InformationConstructor nextHandler);

    AbstractSyntaxObject construct(String strings);
}
