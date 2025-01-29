package com.example.dao;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.service.PersonaleTAService;
import it.unisa.uniclass.utenti.service.dao.PersonaleTARemote;

public class TestPersonaleTAService extends PersonaleTAService {
    private PersonaleTARemote personaleTADao;

    public TestPersonaleTAService() {}

    public void setPersonaleTADao(PersonaleTARemote personaleTADao) {
        this.personaleTADao = personaleTADao;
    }

    @Override
    public PersonaleTA trovaEmailPass(String email, String pass) {
        return personaleTADao.trovaEmailPassword(email, pass);
    }
}