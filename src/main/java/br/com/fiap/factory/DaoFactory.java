package br.com.fiap.factory;

import br.com.fiap.dao.UserDao;
import br.com.fiap.dao.impl.OracleUserDao;

public class DaoFactory {

    public static UserDao getUserDao() {
        return new OracleUserDao();
    }
}
