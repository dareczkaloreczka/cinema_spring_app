package com.example.cinema_spring_app.view;
import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@EnableJpaRepositories("com.example.cinema_spring_app")
@ComponentScan("com.example.cinema_spring_app")
@EntityScan(basePackageClasses=Movie.class)

@Component
public class MainFrame extends JFrame {

    private final TablePanel tablePanel;
    private final DetailsPanel detailsPanel;
    private final MovieController movieController;
    @Autowired
    public MainFrame(TablePanel tablePanel, DetailsPanel detailsPanel, MovieController movieController) throws HeadlessException {
        this.tablePanel = tablePanel;
        this.detailsPanel = detailsPanel;
        this.movieController = movieController;
        init();
    }

    private void init() {

        setTitle("Cinema Pitu-pitu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 500);
        JMenuBar menuBar = new JMenuBar();
        JMenu repertoire = new JMenu("Repertoire");
        JMenu reservations = new JMenu("Reservations");
        JMenuItem showRepertoire = new JMenuItem("Show Repertoire");
        JMenuItem addNewReservation = new JMenuItem("Create new..");
        JMenuItem manageReservations = new JMenuItem("Manage reservations");
        repertoire.add(showRepertoire);
        reservations.add(addNewReservation);
        reservations.add(manageReservations);
        menuBar.add(repertoire);
        menuBar.add(reservations);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(tablePanel);
        mainPanel.add(detailsPanel);
        getContentPane().add(mainPanel);

    movieController.fillTheTable();
     /*   showRepertoire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RepertoireFrame repertoireFrame = new RepertoireFrame();
                repertoireFrame.setVisible(true);
            }
        });


    }*/
    }
}