package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Observer {
    void update(String groupName, String notification);
}

interface ObservableObject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String groupName, String notification);
}

// класс объекта
class Group implements ObservableObject {
    private String name;
    private ArrayList <Observer> observers;
    private String notification;

    public Group(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
    }


    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    public void notifyObservers(String groupName, String notification) {
        for (Observer observer : observers) {
            observer.update(groupName, notification);
        }
    }

    public void setNotification(String notification) {
        this.notification = notification;
        notifyObservers(this.name, notification);
    }
}

// класс пользователей
class Subscriber implements Observer {
    private String name;
    private List<String> groups;

    public Subscriber(String name, String[] groups) {
        this.name = name;
        this.groups = new ArrayList<>(Arrays.asList(groups));
    }


    public void update(String groupName, String notification) {
        if (groups.contains(groupName)) {
            System.out.println(name + " получил оповещение из группы " + groupName + ":\n" + notification);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Group gr_1 = new Group("Музыка");
        Group gr_2 = new Group("Спорт");
        Group gr_3 = new Group("Игры");
        Group gr_4 = new Group("Фильмы");

        Subscriber subscriber_1 = new Subscriber("Елизавета", new String[]{"Музыка", "Спорт", "Игры"});
        Subscriber subscriber_2 = new Subscriber("Юлий", new String[]{"Музыка", "Спорт", "Игры", "Фильмы"});


        gr_1.setNotification("Вышла долгожданная песня вашего любимого исполнителя!");
        gr_2.setNotification("Через минуту начнётся легендарный матч!Не пропустите!");
        gr_3.setNotification("Идёт бесплатная раздача новой игры");
        gr_4.setNotification("Посмотрите новую серию 'Cмешариков'");


        gr_1.addObserver(subscriber_1);
        gr_2.addObserver(subscriber_1);
        gr_3.addObserver(subscriber_1);
        gr_1.addObserver(subscriber_2);
        gr_2.addObserver(subscriber_2);
        gr_3.addObserver(subscriber_2);
        gr_4.addObserver(subscriber_2);

    }
}