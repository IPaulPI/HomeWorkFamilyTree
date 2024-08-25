package ru.gb.family_tree.interfaces;

import java.util.List;

public interface HasRelations<T> {
    List<T> getChildren();
    List<T> getParents();
    void addChild(T child);
    void addParent(T parent);
}
