package br.edu.digitalhouse.museuapp;

import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    Map<String, String> usuarios = new HashMap<>();

    public LoginManager() {
        usuarios.put("museu@digital.com", "1234");
        usuarios.put("a", "a");

    }

    public String getSenhaPorUsuario(String usuario) {
        return usuarios.get(usuario);
    }
}
