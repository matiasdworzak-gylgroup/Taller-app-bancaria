package service;

import entity.Sucursal;
import repository.SucursalRepository;

import java.util.ArrayList;

public class SucursalService {

    private SucursalRepository sucursalRepo;

    public SucursalService(SucursalRepository sucursalRepo) {
        this.sucursalRepo = sucursalRepo;
    }

    public ArrayList<Sucursal> getTodasLasSucursales(){
        return sucursalRepo.getTodasLasSucursales();
    }
}
