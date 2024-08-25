package ru.gb.family_tree.main;

import ru.gb.family_tree.models.FamilyTree;
import ru.gb.family_tree.models.Human;
import ru.gb.family_tree.presenter.FamilyTreePresenter;
import ru.gb.family_tree.util.FileHandler;
import ru.gb.family_tree.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        FamilyTree<Human> familyTree = new FamilyTree<>();
        ConsoleView<Human> view = new ConsoleView<>();
        FamilyTreePresenter<Human> presenter = new FamilyTreePresenter<>(familyTree, view);
        FileHandler<Human> fileHandler = new FileHandler<>();

        boolean running = true;
        while (running) {
            view.showMenu();
            String choice = view.getUserInput("Выберите действие: ");
            switch (choice) {
                case "1":
                    presenter.addNewMember();
                    break;
                case "2":
                    presenter.showAllMembers();
                    break;
                case "3":
                    int id1 = Integer.parseInt(view.getUserInput("Введите ID первого члена: "));
                    int id2 = Integer.parseInt(view.getUserInput("Введите ID второго члена: "));
                    presenter.setSpouse(id1, id2);
                    break;
                case "4":
                    int childId = Integer.parseInt(view.getUserInput("Введите ID ребенка: "));
                    int parentId = Integer.parseInt(view.getUserInput("Введите ID родителя: "));
                    presenter.setParent(childId, parentId);
                    break;
                case "5":
                    String filename = view.getUserInput("Введите имя файла для сохранения: ");
                    fileHandler.saveFamilyTree(familyTree, filename);
                    break;
                case "6":
                    filename = view.getUserInput("Введите имя файла для загрузки: ");
                    FamilyTree<Human> loadedTree = fileHandler.loadFamilyTree(filename);
                    if (loadedTree != null) {
                        familyTree = loadedTree;
                        presenter = new FamilyTreePresenter<>(familyTree, view);
                        view.showMessage("Древо загружено.");
                    } else {
                        view.showMessage("Ошибка при загрузке древа.");
                    }
                    break;
                case "7":
                    presenter.findMemberByName();
                    break;
                case "8":
                    presenter.removeMember();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    view.showMessage("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
