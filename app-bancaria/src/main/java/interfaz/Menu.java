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

    private  Scanner teclado;
    private  UsuarioClienteService userService;
    private  TransaccionService transService;
    private  SucursalService sucService;
    private Sucursal sucursalActual;
    private UsuarioCliente sesionActiva;

    public Menu( UsuarioClienteService userService, TransaccionService transService, SucursalService sucService) {

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
            System.out.println("\n¿A qué sucursal le gustaría acceder? Escriba el nombre que corresponda (Puede escribir S para salir del programa)\n");
            ArrayList<Sucursal> listaSucursales = sucService.getTodasLasSucursales();
            String nombreSucursal = teclado.nextLine();

            if (nombreSucursal.equalsIgnoreCase("S")) {
                correr = false;
                System.out.println("\nGracias por visitar nuestro banco\n");
            } else {
                Sucursal suc = SucursalService.buscarSucursal(nombreSucursal);

                if (sucursal == null) {
                    System.out.println("\nOpción inválida\n");
                }

                while (sucursal != null) {
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
                                Acciones de administrador:
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
                            registrarCuenta();
                            break;
                        case 2:
                            if (sesionActiva == null) {
                                iniciarSesion();
                            } else {
                                System.out.println("\nYa hay una sesión activa\n");
                            }
                            break;
                        case 3:
                            if (sesionActiva != null) {
                                procesarDeposito();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 4:
                            if (sesionActiva != null) {
                                procesarRetiro();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 5:
                            if (sesionActiva != null) {
                                procesarTransferencia();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 6:
                            if (sesionActiva != null) {
                                System.out.println(sesionActiva);;
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 7:
                            if (sesionActiva != null) {
                                procesarEliminacion();
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
                            sucursal = null;
                            sesionActiva = null;
                            break;
                        case 10:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                sucursal.mostrarCuentas();
                            }
                            break;
                        case 11:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                System.out.println("\nIngrese el nombre de la sucursal\n");
                                banco.mostrarSucursales();
                                String sucursalBuscada = teclado.nextLine();
                                Sucursal otraSucursal = banco.buscarSucursal(sucursalBuscada);
                                otraSucursal.mostrarCuentas();
                            }
                            break;
                        case 12:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                banco.mostrarCuentas();
                            }
                            break;
                        case 13:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                System.out.println("Ingrese el nombre de la nueva sucursal");
                                String nombreNuevaSucursal = teclado.nextLine();
                                banco.crearSucursal(nombreNuevaSucursal);
                            }
                            break;
                        case 14:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                procesarEliminacionAdmin();
                            }
                            break;
                        default:
                            System.out.println("\nOpción inválida\n");
                    }
                }
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

//    private void registrarCuenta(String nombreSucursal) {
//        Sucursal sucursal = banco.buscarSucursal(nombreSucursal);
//        TipoCuenta tipoCuenta = null;
//        System.out.println("Ingrese su nombre");
//        String nombre = teclado.nextLine();
//        System.out.println("Ingrese su email");
//        String email = teclado.nextLine();
//        System.out.println("Ingrese su pin");
//        int pin = teclado.nextInt();
//        System.out.println("""
//                Indique el tipo de cuenta que le gustaría crear:
//                1) Caja de ahorro
//                2) Cuenta corriente""");
//        int opcionCuenta = teclado.nextInt();
//        if (opcionCuenta == 1) {
//            tipoCuenta = TipoCuenta.CAJA_AHORRO;
//        } else if (opcionCuenta == 2) {
//            tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
//        } else {
//            System.out.println("Opción inválida");
//        }
//        sucursal.registrarCuentaSucursal(nombre, email, pin, false, tipoCuenta);
//    }

//    private void iniciarSesion() {
//
//    }

//    System.out.println("Ingrese el email de la cuenta a la que desea hacer un depósito:");
//    String emailBuscado = teclado.nextLine();
//    Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
//    System.out.println("Ingrese el monto que desea depositar:");
//    double monto = teclado.nextFloat();
//    depositar(cuentaBuscada, monto);
//
//    System.out.println("Ingrese el email de la cuenta a la que desea hacer un retiro:");
//    String emailBuscado = teclado.nextLine();
//    Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
//    System.out.println("Ingrese el monto que desea retirar:");
//    double monto = teclado.nextFloat();
//    retirar(cuentaBuscada, monto);
//
//    System.out.println("Ingrese el email de la cuenta desde la que quiere enviar la transferencia:");
//    String emailTransferente = teclado.nextLine();
//    Cuenta cuentaTransferente = buscarCuenta(emailTransferente);
//    System.out.println("Ingrese el email de la cuenta a la que quiere enviar la transferencia:");
//    String emailTransferido = teclado.nextLine();
//    Cuenta cuentaTransferido = buscarCuenta(emailTransferido);
//    System.out.println("Ingrese el monto que desea transferir:");
//    double monto = teclado.nextFloat();
//    transferir(cuentaTransferente, cuentaTransferido, monto);
//
//    System.out.println("Ingrese el email de la cuenta que desea eliminar:");
//    String emailBuscado = teclado.nextLine();
//    Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
//    System.out.println("¿Está seguro que desea eliminar la cuenta? (S o N)");
//    String eleccion = teclado.nextLine().toUpperCase();
//    if (eleccion.equals("S")) {eliminarCuenta(cuentaBuscada);
//    } else if (!eleccion.equals("N")) {System.out.println("Opción inválida");
//    }
}