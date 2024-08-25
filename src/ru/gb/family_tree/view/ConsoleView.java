package ru.gb.family_tree.view;

import java.util.List;
import java.util.Scanner;

public class ConsoleView<T> implements FamilyTreeView<T> {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showAllMembers(List<T> members) {
        for (T member : members) {
            System.out.println(member);
        }
    }

    @Override
    public void showMember(T member) {
        System.out.println(member);
    }

    @Override
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void showMenu() {
        System.out.println("Меню:");
        System.out.println("1. Добавить нового члена");
        System.out.println("2. Показать всех членов");
        System.out.println("3. Установить супружеские отношения");
        System.out.println("4. Установить родительские отношения");
        System.out.println("5. Сохранить древо в файл");
        System.out.println("6. Загрузить древо из файла");
        System.out.println("7. Найти члена по имени");
        System.out.println("8. Удалить члена");
        System.out.println("0. Выход");
    }
}
