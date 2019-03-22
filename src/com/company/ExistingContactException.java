package com.company;

import javax.swing.*;

public class ExistingContactException extends Exception {
    @Override
    public String getMessage() {
        return "Данный контакт уже существует";
    }

    public boolean replaceNumber(final String name){
        int answer = JOptionPane.showConfirmDialog(null, "Хотите заменить номер у контакта " + name + "?");
        return answer == 0;
    }
}