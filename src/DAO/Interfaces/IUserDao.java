package DAO.Interfaces;

import java.sql.SQLException;

public interface IUserDao {

    Boolean logar(String name, String password) throws SQLException;

}
