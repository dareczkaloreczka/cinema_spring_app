package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.repo.HallRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HallControllerTest {

    HallRepository hallRepositoryMock = Mockito.mock(HallRepository.class);
    HallController hallController;
    @Before
    public void setUp() throws Exception {
        hallController = new HallController(hallRepositoryMock);
    }

    @Test
    public void getAllHalls() throws Exception {
        hallController.getAllHalls();
        Mockito.verify(hallRepositoryMock, Mockito.atLeastOnce()).findAll();
    }

}