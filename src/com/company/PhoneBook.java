package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private Map<String, String> phoneBook = new TreeMap<>();

    public void saveInFile(final String pathOfFile) {
        FileWriter book = null;
        try {
            book = new FileWriter(pathOfFile, false);
        } catch (IOException e) {
            try {
                new File(pathOfFile).createNewFile();
                JOptionPane.showMessageDialog(null, "Создан новый файл по этому пути");
                book = new FileWriter(pathOfFile, false);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Файл не может быть создан по этому пути");
                return;
            }
        }
        for (String name : phoneBook.keySet()){
            try {
                book.write(name + ": " + phoneBook.get(name) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPhone(final String name, final String number){
        if (isExist(name)) {
            try {
                throw new ExistingContactException();
            } catch (ExistingContactException e) {
                e.printStackTrace();
                if (e.replaceNumber(name)) {
                    replaceNumber(name, number);
                }
                return;
            }
        }
        phoneBook.put(name, number);
    }

    public String getContact(final String name){
        return name + ": " + phoneBook.get(name);
    }

    public void delContact(final String name){
        phoneBook.remove(name);
    }

    public void printBook(){
        for(String name : phoneBook.keySet()){
            System.out.println(getContact(name));
        }
    }

    public void printBook(JTextArea textArea){
        for(String name : phoneBook.keySet()){
            textArea.append(getContact(name) + "\n");
        }
    }

    public boolean isExist(final String name){
        return phoneBook.get(name) != null;
    }

    public void replaceNumber(final String name, final String number){
        phoneBook.replace(name, number);
    }
}
