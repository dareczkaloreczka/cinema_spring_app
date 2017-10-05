package com.example.cinema_spring_app.view;

import org.springframework.stereotype.Component;

import javax.swing.table.DefaultTableModel;
@Component
public class MyTableModel extends DefaultTableModel{

    public MyTableModel() {
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
