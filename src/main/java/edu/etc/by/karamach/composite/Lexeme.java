package edu.etc.by.karamach.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Lexeme syntax object
 *
 * @author Nickolai Karamach
 */
public class Lexeme implements AbstractSyntaxObject, Serializable {

    private static final long serialVersionUID = -9114200734932264324L;

    private String value = "";

    transient private List<AbstractSyntaxObject> children = new ArrayList<>();

    /*
     *      Methods:
     *
     */

    public Lexeme(String value) {
        this.value = value;
    }

    public Lexeme() {
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<AbstractSyntaxObject> getChildren() {
        return children;
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
    public String print() {

        if (children == null || (children.size() == 0)) {
            return value;
        } else {

            StringBuilder stringBuilder = new StringBuilder();

            for (AbstractSyntaxObject object : children) {
                stringBuilder.append(object.print());
            }

            return stringBuilder.toString();
        }
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

    //TODO: QUESTION:Do we need our own hash?
    @Override
    public int hashCode() {
        return Objects.hash(value, children);
    }
}

