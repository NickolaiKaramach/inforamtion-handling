package edu.etc.by.karamach.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lexeme implements AbstractSyntaxObject, Serializable {
    private static final long serialVersionUID = 8707118521730556652L;

    private String value;
    transient private List<AbstractSyntaxObject> children = new ArrayList<>();

    public Lexeme(String value) {
        this.value = value;
    }

    public Lexeme() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void add(AbstractSyntaxObject syntaxObject) {
        this.children.add(syntaxObject);
    }

    public void remove(AbstractSyntaxObject syntaxObject) {
        this.children.remove(syntaxObject);
    }

    public void clear() {
        this.children.clear();
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "value='" + value + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Lexeme lexeme = (Lexeme) obj;
        return value.equals(lexeme.getValue()) &&
                children.equals(lexeme.children);
    }

    //TODO: QUESTION: Would we make our hash?
    @Override
    public int hashCode() {
        return Objects.hash(value, children);
    }
}

