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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
@EnableJpaRepositories("com.example.cinema_spring_app")
@ComponentScan("com.example.cinema_spring_app")
@EntityScan(basePackageClasses=Movie.class)

@Component
public class MainFrame extends JFrame {

    private final TableMoviePanel tableMoviePanel;
    private final DetailsMoviePanel detailsMoviePanel;
    private final MovieController movieController;
    private final RepertoireFrame repertoireFrame;
    private final MgmtReservationFrame mgmtReservationFrame;
  //  private final ManageReservationsFrame manageReservationsFrame;

    @Autowired
    public MainFrame(TableMoviePanel tableMoviePanel, DetailsMoviePanel detailsMoviePanel,
                     MovieController movieController, RepertoireFrame repertoireFrame,
                     MgmtReservationFrame mgmtReservationFrame) throws HeadlessException {
        this.tableMoviePanel = tableMoviePanel;
        this.detailsMoviePanel = detailsMoviePanel;
        this.movieController = movieController;
        this.repertoireFrame = repertoireFrame;
        this.mgmtReservationFrame = mgmtReservationFrame;
        //     this.manageReservationsFrame = manageReservationsFrame;
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
        JMenuItem manageReservations = new JMenuItem("Manage reservations");
        repertoire.add(showRepertoire);
        reservations.add(manageReservations);
        menuBar.add(repertoire);
        menuBar.add(reservations);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(tableMoviePanel);
        mainPanel.add(detailsMoviePanel);
        getContentPane().add(mainPanel);

    movieController.fillTheTable();

    showRepertoire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repertoireFrame.setVisible(true);
            }
        });

    manageReservations.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mgmtReservationFrame.setVisible(true);
        }
    });

    }
}