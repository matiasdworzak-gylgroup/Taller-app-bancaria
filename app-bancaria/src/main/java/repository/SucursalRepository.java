package repository;

import entity.Banco;
import entity.Sucursal;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SucursalRepository {
    private Banco Bancodb = Banco.getInstancia();

    public ArrayList<Sucursal> getTodasLasSucursales(){
        return Bancodb.getSucursales();
    }

}
