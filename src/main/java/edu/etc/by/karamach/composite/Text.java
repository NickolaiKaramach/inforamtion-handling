package edu.etc.by.karamach.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements AbstractSyntaxObject, Serializable {
    private static final long serialVersionUID = -6714750132371681501L;

    transient private List<AbstractSyntaxObject> children = new ArrayList<>();
    private String value;

    public Text(String value) {
        this.value = value;
    }

    public Text() {
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
                "children=" + children +
                ", value='" + value + '\'' +
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

        Text text = (Text) obj;
        return children.equals(text.children) &&
                value.equals(text.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, value);
    }
}
