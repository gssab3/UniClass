package it.unisa.uniclass.testing.dao;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.dao.AccademicoRemote;

public class TestAccademicoService extends AccademicoService {
    private AccademicoRemote accademicoDao;

    public TestAccademicoService() {}

    public TestAccademicoService(AccademicoRemote accademicoDao) {
        this.accademicoDao = accademicoDao;
    }

    public void setAccademicoDao(AccademicoRemote accademicoDao) {
        this.accademicoDao = accademicoDao;
    }

    @Override
    public Accademico trovaEmailPassUniclass(String email, String pass) {
        return accademicoDao.trovaEmailUniClass(email);
    }
}