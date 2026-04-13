import java.util.ArrayList;

public class GestorBancario {
    private ArrayList<UsuarioCliente> usuariosActivos;
    private ArrayList<UsuarioCliente> usuariosDadosDeBaja;
    public GestorBancario(){
        usuariosActivos = new ArrayList<>();
        usuariosDadosDeBaja = new ArrayList<>();
    }
    public void  altaUsuario(UsuarioCliente user){
        if (user.getName()!=null && user.getDireccion()!=null && user.getTipoDeCuenta()!= null){
            usuariosActivos.add(user);
        }

    }
    public void listarUsuarios(){
        int i;
        for(i=0; i<usuariosActivos.size(); i++){
            System.out.println(usuariosActivos.get(i).getName());
        }
    }
    public void listarUsuariosConSusBalances(){
        int i;
        for(i=0; i<usuariosActivos.size(); i++){
            System.out.println("Nombre: " + usuariosActivos.get(i).getName() + "\nBalance: $" + usuariosActivos.get(i).getSaldo());
        }
    }

    public void depositar(UsuarioCliente user, Double monto){
        user.setSaldo(user.getSaldo() + monto);
    }
}
