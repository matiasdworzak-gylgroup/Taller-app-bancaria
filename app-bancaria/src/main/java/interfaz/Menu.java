package interfaz;

import entity.Banco;
import entity.Sucursal;
import entity.UsuarioCliente;
import service.SucursalService;
import service.TransaccionService;
import service.UsuarioClienteService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner teclado;
    private UsuarioClienteService userService;
    private TransaccionService transService;
    private SucursalService sucService;
    private Sucursal sucursalActual;
    private UsuarioCliente sesionActiva;

    public Menu(UsuarioClienteService userService, TransaccionService transService, SucursalService sucService) {

        this.userService = userService;
        this.transService = transService;
        this.sucService = sucService;
        this.sucursalActual = null;
        this.sesionActiva = null;
        teclado = new Scanner(System.in);
    }


    public void mostrarMenu() {
        boolean correr = true;
        while (correr) {
            System.out.println("\n-----Bienvenido a nuestro banco-----\n");

            ArrayList<Sucursal> listaSucursales = sucService.getTodasLasSucursales();
            mostrarLista("Elija la opcion deseada", listaSucursales);
            int opcion = teclado.nextInt();
            if (opcion > 0 && opcion <= listaSucursales.size()) {
                this.sucursalActual = listaSucursales.get(opcion - 1);
                System.out.println("Entrando a sucursal: " + sucursalActual.getNombre());
                correr = false;
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        while (sucursalActual != null) {
            System.out.println("""
                    Ingrese el número que corresponda con la acción que desee realizar:
                    1) Registrar cuenta
                    2) Iniciar sesión
                    3) Depositar dinero
                    4) Retirar dinero
                    5) Realizar una transferencia
                    6) Mostrar datos de la cuenta
                    7) Eliminar cuenta
                    8) Cerrar sesión
                    9) Salir de la sucursal
                    """);
            if (sesionActiva != null && sesionActiva.isAdmin()) {
                System.out.println("""
                        Acciones de administrador:1
                        10) Mostrar datos de esta sucursal
                        11) Mostrar datos de otra sucursal
                        12) Mostrar datos del banco
                        13) Crear sucursal
                        14) Eliminar una cuenta
                        """);
            }

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    // registrarCuenta();
                    break;
                case 2:
                    if (sesionActiva == null) {
                        //  iniciarSesion();
                    } else {
                        System.out.println("\nYa hay una sesión activa\n");
                    }
                    break;
                case 3:
                    if (sesionActiva != null) {
                        // procesarDeposito();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 4:
                    if (sesionActiva != null) {
                        // procesarRetiro();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 5:
                    if (sesionActiva != null) {
                        //  procesarTransferencia();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 6:
                    if (sesionActiva != null) {
                        System.out.println(sesionActiva);
                        ;
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 7:
                    if (sesionActiva != null) {
                        //  procesarEliminacion();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 8:
                    if (sesionActiva != null) {
                        sesionActiva = null;
                        System.out.println("\nSesión cerrada con éxito\n");
                    } else {
                        System.out.println("\nNo hay una sesión activa\n");
                    }
                    break;
                case 9:
                    // sucursal = null;
                    sesionActiva = null;
                    break;
                case 10:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        //  sucursal.mostrarCuentas();
                    }
                    break;
                case 11:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        System.out.println("\nIngrese el nombre de la sucursal\n");
                        // banco.mostrarSucursales();
                        String sucursalBuscada = teclado.nextLine();
                        //Sucursal otraSucursal = banco.buscarSucursal(sucursalBuscada);
                        // otraSucursal.mostrarCuentas();
                    }
                    break;
                case 12:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        //   banco.mostrarCuentas();
                    }
                    break;
                case 13:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        System.out.println("Ingrese el nombre de la nueva sucursal");
                        String nombreNuevaSucursal = teclado.nextLine();
                        //  banco.crearSucursal(nombreNuevaSucursal);
                    }
                    break;
                case 14:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        //   procesarEliminacionAdmin();
                    }
                    break;
                default:
                    System.out.println("\nOpción inválida\n");
            }
        }
    }
    private <T> void mostrarLista(String titulo, ArrayList<T> lista) {
        System.out.println("=== " + titulo + " ===");
        if (lista.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ") " + lista.get(i));
            }
        }
        System.out.println("-------------------------");
    }
}






