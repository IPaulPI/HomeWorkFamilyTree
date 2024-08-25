package ru.gb.family_tree.models;

import ru.gb.family_tree.interfaces.HasRelations;

import java.io.Serializable;
import java.util.*;

public class FamilyTree<T extends HasRelations<T> & Serializable> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Integer, T> members;
    private int nextId;

    public FamilyTree() {
        this.members = new HashMap<>();
        this.nextId = 1;
    }

    public void addMember(T member) {
        members.put(nextId++, member);
    }

    public T getMember(int id) {
        return members.get(id);
    }

    public void removeMember(int id) {
        members.remove(id);
    }

    public List<T> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public List<T> sortBy(Comparator<T> comparator) {
        List<T> sortedList = new ArrayList<>(members.values());
        sortedList.sort(comparator);
        return sortedList;
    }

    @Override
    public Iterator<T> iterator() {
        return members.values().iterator();
    }
}
