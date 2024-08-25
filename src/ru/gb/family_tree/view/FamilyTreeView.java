package ru.gb.family_tree.view;

import java.util.List;

public interface FamilyTreeView<T> {
    void showMessage(String message);
    void showAllMembers(List<T> members);
    void showMember(T member);
    String getUserInput(String prompt);
    void showMenu();
}
