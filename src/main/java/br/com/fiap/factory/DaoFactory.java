package br.com.fiap.factory;

import br.com.fiap.dao.impl.OracleUserDao;

public class DaoFactory {

    public static OracleUserDao getUserValidateDao() {
        return new OracleUserDao();
    }
}
