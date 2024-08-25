package ru.gb.family_tree.util;

import ru.gb.family_tree.interfaces.HasRelations;
import ru.gb.family_tree.models.FamilyTree;

import java.io.*;

public class FileHandler<T extends Serializable & HasRelations<T>> {

    public void saveFamilyTree(FamilyTree<T> familyTree, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
            System.out.println("Древо успешно сохранено в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении древа: " + e.getMessage());
        }
    }

    public FamilyTree<T> loadFamilyTree(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке древа: " + e.getMessage());
            return null;
        }
    }
}
