package ru.gb.family_tree.presenter;

import ru.gb.family_tree.interfaces.HasRelations;
import ru.gb.family_tree.models.FamilyTree;
import ru.gb.family_tree.models.Human;
import ru.gb.family_tree.view.FamilyTreeView;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyTreePresenter<T extends HasRelations<T> & Serializable> {
    private FamilyTree<T> familyTree;
    private FamilyTreeView view;

    public FamilyTreePresenter(FamilyTree<T> familyTree, FamilyTreeView view) {
        this.familyTree = familyTree;
        this.view = view;
    }

    public void addMember(T member) {
        familyTree.addMember(member);
        view.showMessage("Член добавлен: " + member);
    }

    public void addNewMember() {
        String name = view.getUserInput("Введите имя: ");
        String birthDate = view.getUserInput("Введите дату рождения (дд-ММ-гггг): ");
        String gender = view.getUserInput("Введите пол (мужской/женский): ");

        int id = familyTree.getAllMembers().size() + 1;  // Простое назначение ID

        T member = (T) new Human(id, name, birthDate, gender);  // Преобразование типа
        addMember(member);
    }

    public void showAllMembers() {
        List<T> members = familyTree.getAllMembers();
        view.showAllMembers(members);
    }

    public void setSpouse(int id1, int id2) {
        T member1 = familyTree.getMember(id1);
        T member2 = familyTree.getMember(id2);

        if (member1 != null && member2 != null) {
            ((Human) member1).setSpouse((Human) member2);
            view.showMessage("Супружеские отношения установлены между: " + member1 + " и " + member2);
        } else {
            view.showMessage("Один или оба члена не найдены.");
        }
    }

    public void setParent(int childId, int parentId) {
        T child = familyTree.getMember(childId);
        T parent = familyTree.getMember(parentId);

        if (child != null && parent != null) {
            child.addParent(parent);
            view.showMessage("Родительские отношения установлены между: " + parent + " и " + child);
        } else {
            view.showMessage("Один или оба члена не найдены.");
        }
    }

    public void findMemberByName() {
        String name = view.getUserInput("Введите имя для поиска: ");
        List<T> foundMembers = familyTree.getAllMembers().stream()
                .filter(member -> ((Human) member).getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (!foundMembers.isEmpty()) {
            view.showMessage("Найдены члены:");
            view.showAllMembers(foundMembers);
        } else {
            view.showMessage("Член с таким именем не найден.");
        }
    }

    public void removeMember() {
        int id = Integer.parseInt(view.getUserInput("Введите ID члена для удаления: "));
        T member = familyTree.getMember(id);

        if (member != null) {
            familyTree.removeMember(id);
            view.showMessage("Член удален: " + member);
        } else {
            view.showMessage("Член с таким ID не найден.");
        }
    }
}
