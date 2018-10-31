package edu.etc.by.karamach.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements AbstractSyntaxObject, Serializable {
    private static final long serialVersionUID = -6714750132371681501L;
    private static final String TAB_BETWEEN_PARTS = "\n\t";
    private static final String FIRST_TAB_IN_TEXT = "\t";

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

    @Override
    public List<AbstractSyntaxObject> getChildren() {
        return children;
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
            StringBuilder stringBuilder = new StringBuilder(FIRST_TAB_IN_TEXT);

            for (AbstractSyntaxObject object : children) {
                stringBuilder.append(object.print());
                stringBuilder.append(TAB_BETWEEN_PARTS);
            }

            return stringBuilder.toString();
        }
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
