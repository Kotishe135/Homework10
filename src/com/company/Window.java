package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Window {
    private PhoneBook book = new PhoneBook();

    public Window(){
        JFrame window = new JFrame("Телефонная книга");
        JTextField name = new JTextField();
        JTextField number = new JTextField();
        JButton addContact = new JButton("Добавить");
        JButton delContact = new JButton("Удалить");
        JButton saveBook = new JButton("Сохранить в файл");
        JTextArea phoneBook = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(phoneBook);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        window.setSize(400, 225);
        window.setLocation((screenSize.width - window.getWidth()) / 2, (screenSize.height - window.getHeight()) / 2);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);

        name.setSize(150, 25);
        name.setLocation(0, 0);

        number.setSize(150, 25);
        number.setLocation(150, 0);

        phoneBook.setSize(400, 175);
        phoneBook.setDisabledTextColor(Color.BLACK);
        phoneBook.setLocation(0, 25);
        phoneBook.setEnabled(false);

        scrollPane.setSize(400, 150);
        scrollPane.setLocation(0, 25);

        addContact.setSize(50, 25);
        addContact.setFont(new Font("Times New Roman", 0, 10));
        addContact.setMargin(new Insets(0, 0, 0, 0));
        addContact.setLocation(300, 0);
        addContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!name.getText().equals("") && !number.getText().equals("")) {
                    book.addPhone(name.getText(), number.getText());
                    name.setText("");
                    number.setText("");
                    phoneBook.setText("");
                    book.printBook(phoneBook);
                }
            }
        });

        delContact.setSize(50, 25);
        delContact.setFont(new Font("Times New Roman", 0, 10));
        delContact.setMargin(new Insets(0, 0, 0, 0));
        delContact.setLocation(350, 0);
        delContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.delContact(name.getText());
                name.setText("");
                number.setText("");
                phoneBook.setText("");
                book.printBook(phoneBook);
            }
        });

        saveBook.setSize(400, 25);
        saveBook.setLocation(0, 175);
        saveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!phoneBook.getText().equals("")) {
                    String pathOfFile = JOptionPane.showInputDialog(null, "Введите путь до файла");
                    if (pathOfFile != null && !pathOfFile.equals("")) {
                        book.saveInFile(pathOfFile);
                    }
                }
            }
        });

        window.getContentPane().add(name);
        window.getContentPane().add(number);
        window.getContentPane().add(addContact);
        window.getContentPane().add(delContact);
        window.getContentPane().add(saveBook);
        window.getContentPane().add(scrollPane);
        window.setVisible(true);
    }
}
