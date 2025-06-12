package dao;

import model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {
    public ClienteDAO() {
        super(Cliente.class);
    }
}