package ru.gb.family_tree.models;

import ru.gb.family_tree.interfaces.HasRelations;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Human implements HasRelations<Human>, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String gender;
    private List<Human> children;
    private List<Human> parents;
    private Human spouse;

    public Human(int id, String name, String birthDate, String gender) {
        this.id = id;
        this.name = name;
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    // Геттеры и сеттеры...

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public String getGender() {
        return gender;
    }

    public List<Human> getChildren() {
        return children;
    }

    public List<Human> getParents() {
        return parents;
    }

    public Human getSpouse() {
        return spouse;
    }

    @Override
    public void addChild(Human child) {
        if (!children.contains(child)) {
            children.add(child);
            child.addParent(this);
        }
    }

    @Override
    public void addParent(Human parent) {
        if (!parents.contains(parent)) {
            parents.add(parent);
            parent.addChild(this);
        }
    }

    public void setSpouse(Human spouse) {
        if (this.spouse != null) {
            this.spouse.spouse = null;
        }
        this.spouse = spouse;
        if (spouse != null && spouse.getSpouse() != this) {
            spouse.setSpouse(this);
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Имя: " + name + ", Дата рождения: " + birthDate + ", Пол: " + gender;
    }
}
