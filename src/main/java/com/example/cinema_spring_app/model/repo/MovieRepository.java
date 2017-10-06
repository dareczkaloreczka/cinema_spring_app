package com.example.cinema_spring_app.model.repo;


import com.example.cinema_spring_app.model.Movie;

import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public Movie findByTitle(String title);

    @Modifying
    @Transactional
    @Query("update Movie m set m.title = :newTitle where m.id = :id")
    public void updateTitle (@Param("id") Integer id, @Param("newTitle") String newTitle);

    @Modifying
    @Transactional
    @Query("update Movie m set m.year = :newYear where m.id = :id")
    public void updateYear (@Param("id") Integer id, @Param("newYear") Date newYear);

    @Modifying
    @Transactional
    @Query("update Movie m set m.director = :newDirector where m.id = :id")
    public void updateDirector (@Param("id") Integer id, @Param("newDirector") String newDirector);

    @Modifying
    @Transactional
    @Query("update Movie m set m.duration = :newDuration where m.id = :id")
    public void updateDuration (@Param("id") Integer id, @Param("newDuration") int newDuration);

    @Modifying
    @Transactional
    @Query("update Movie m set m.ageCategory = :newAgeCategory where m.id = :id")
    public void updateCategory (@Param("id") Integer id, @Param("newAgeCategory") MovieCategory newAgeCategory);

    @Modifying
    @Transactional
    @Query("update Movie m set m.genre = :newGenre where m.id = :id")
    public void updateGenre (@Param("id") Integer id, @Param("newGenre") MovieGenre newGenre);




   /* public void addMovie(Movie movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(movie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            // HibernateUtil.getSessionFactory().close();
        }
    }

    public Movie getMovie(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Movie movie = null;
        try {
            tx = session.beginTransaction();
            movie = session.get(Movie.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            //HibernateUtil.getSessionFactory().close();
        }
        return movie;
    }

    public Movie getMovieByTitle(String title){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("from Movie where title=:title");
        query.setParameter("title", title);
        Movie movie = (Movie) query.uniqueResult();
        return movie;
    }

    public List<Movie> getAllTheMovies() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Movie> query = session.createQuery("select m from Movie m", Movie.class);
        List<Movie> allMovies = query.getResultList();
        session.close();
        // HibernateUtil.getSessionFactory().close();
        return allMovies;
    }

    public void updateMovie(Movie movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(movie);
        tx.commit();
        session.close();
    }

    public void removeMovie(Movie movie){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(movie);
        tx.commit();
        session.close();
    }*/

}
