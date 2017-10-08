package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.SeanceController;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
@Component
public class RepertoireFrame extends JDialog {

    private final SeanceController seanceController;
    private final TableSeancePanel tableSeancePanel;
    private final DetailsSeancePanel detailsSeancePanel;

    public RepertoireFrame(SeanceController seanceController, TableSeancePanel tableSeancePanel, DetailsSeancePanel detailsSeancePanel) throws HeadlessException {
        this.seanceController = seanceController;
        this.tableSeancePanel = tableSeancePanel;
        this.detailsSeancePanel = detailsSeancePanel;
        init();
    }

    private void init() {
        setTitle("Repertoire");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(550, 700);
        JPanel mainPanel = new JPanel();
        mainPanel.add(tableSeancePanel);
        mainPanel.add(detailsSeancePanel);
        getContentPane().add(mainPanel);

        seanceController.fillTheTable();
    }
}
