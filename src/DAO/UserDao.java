package DAO;

import DAO.Interfaces.IUserDao;


import java.io.*;
import java.nio.charset.StandardCharsets;


public class UserDao implements IUserDao {

    private String userPath = "files/usuario.csv";

    @Override
    public Boolean logar(String user, String password){
        try(InputStream is = new FileInputStream(this.userPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
        ) {
            String linha;
            while ((linha = br.readLine()) != null){

                String[] dados = linha.split(",");
                String login = dados[1];
                String pass = dados[2];
                if(login.equals(user) && pass.equals(password)){
                    return true;
                }
                return false;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
