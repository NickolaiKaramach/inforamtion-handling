package edu.etc.by.karamach.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part implements AbstractSyntaxObject, Serializable {
    private static final long serialVersionUID = 5848439469391337096L;
    private static final String SPACE_BETWEEN_SENTENCES = " ";

    transient private List<AbstractSyntaxObject> children = new ArrayList<>();
    private String value;

    public Part(String value) {
        this.value = value;
    }

    public Part() {
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
            StringBuilder stringBuilder = new StringBuilder();

            for (AbstractSyntaxObject object : children) {
                stringBuilder.append(object.print());
                stringBuilder.append(SPACE_BETWEEN_SENTENCES);
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

        Part part = (Part) obj;
        return children.equals(part.children) &&
                value.equals(part.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, value);
    }
}
