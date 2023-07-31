package principal;

import DB.DB;
import implementacion.Cliente;
import implementacion.ClienteDAO;
import implementacion.Comercial;
import implementacion.ComercialDAO;
import implementacion.Pedido;
import implementacion.PedidoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author alba_
 */
public class Principal {

    static Scanner sc = new Scanner(System.in);
    static int opcion;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {

        try ( DB db = DB.open()) {//abrimos la conexión
            do {
                System.out.println("Menú de opciones: \n"
                        + "1. Insertar cliente \n"
                        + "2. Actualizar cliente \n"
                        + "3. Listar todos los clientes \n"
                        + "4. Eliminar cliente \n"
                        + "5. Insertar comercial \n"
                        + "6. Actualizar comercial \n"
                        + "7. Listar todos los comerciales \n"
                        + "8. Eliminar comercial \n"
                        + "9. Insertar pedido \n"
                        + "10.Actualizar pedido \n"
                        + "11.Listar todos los pedidos \n"
                        + "12.Eliminar pedido \n"
                        + "13. Salir. \n"
                        + "Elige una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        insertarCliente();
                        break;
                    case 2:
                        updateCliente();
                        break;
                    case 3:
                        cargarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        insertarComercial();
                        break;
                    case 6:
                        updateComercial();
                        break;
                    case 7:
                        cargarComercial();
                        break;
                    case 8:
                        eliminarComercial();
                        break;
                    case 9:
                        insertarPedido();
                        break;
                    case 10:
                        updatePedido();
                        break;
                    case 11:
                        cargarPedido();
                        break;
                    case 12:
                        eliminarPedido();
                        break;
                    case 13:
                        //salir
                        break;
                }
            } while (opcion != 13);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }//fin main

    private static void insertarCliente() {
        System.out.println("Introduce nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce apellido1: ");
        String apellido1 = sc.nextLine();
        System.out.println("Introduce apellido2: ");
        String apellido2 = sc.nextLine();
        System.out.println("Introduce ciudad: ");
        String ciudad = sc.nextLine();
        System.out.println("Introduce categoria: ");
        int categoria = Integer.parseInt(sc.nextLine());

        Cliente nuevoCliente = new Cliente(nombre, apellido1, apellido2, ciudad, categoria);

        try {
            int filasAfectadas = ClienteDAO.insertarCliente(nuevoCliente);
            System.out.println(filasAfectadas);
            if (filasAfectadas > 0) {
                System.out.println("Cliente insertado correctamente.");
            } else {
                System.out.println("Error al insertar cliente.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void updateCliente() {
        System.out.println("Actualizando cliente...");
        System.out.println("Ingrese el ID del cliente a actualizar.");
        int id = Integer.parseInt(sc.nextLine());

        try {
            Cliente clienteExistente = ClienteDAO.buscarCliente(id);
            if (clienteExistente == null) {
                System.out.println("Cliente no encontrado.");
            } else {
                System.out.println("Datos actuales del cliente: ");
                System.out.println(clienteExistente);
                System.out.println("Nuevo nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Nuevo apellido1: ");
                String apellido1 = sc.nextLine();
                System.out.println("Nuevo apellido2: ");
                String apellido2 = sc.nextLine();
                System.out.println("Nuevo ciudad: ");
                String ciudad = sc.nextLine();
                System.out.println("Nuevo categoria: ");
                int categoria = Integer.parseInt(sc.nextLine());

                Cliente clienteActualizado = new Cliente(id, nombre, apellido1, apellido2, ciudad, categoria);

                int filasAfectadas = ClienteDAO.updateCliente(clienteActualizado);
                if (filasAfectadas > 0) {
                    System.out.println("Cliente actualizado correctamente.");
                } else {
                    System.out.println("Error al actualizar el cliente.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void cargarCliente() {
        System.out.println("Cargando todos los clientes...");
        try {
            Cliente[] listaClientes = ClienteDAO.cargarCliente();
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void eliminarCliente() {
        System.out.println("Eliminando cliente...");
        System.out.println("Ingrese el ID del cliente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            int filasAfectadas = ClienteDAO.eliminarCliente(id);
            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("Error al eliminarCliente.Cliente no encontrado.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void insertarComercial() {
        System.out.println("Insertando comercial...");
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce apellido1: ");
        String apellido1 = sc.nextLine();
        System.out.println("Introduce apellido2: ");
        String apellido2 = sc.nextLine();
        System.out.println("Introduce comisión: ");
        float comision = Float.parseFloat(sc.nextLine());

        Comercial nuevoComercial = new Comercial(nombre, apellido1, apellido2, comision);

        try {
            int filasAfectadas = ComercialDAO.insertarComercial(nuevoComercial);
            if (filasAfectadas > 0) {
                System.out.println("Comercial insertado correctamente.");
            } else {
                System.out.println("Error al insertar el comercial.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void updateComercial() {
        System.out.println("Actualizando comercial...");
        System.out.println("Ingrese el ID del comercial a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            Comercial comercialExistente = ComercialDAO.buscarComercial(id);
            if (comercialExistente == null) {
                System.out.println("Comercial no encontrado.");
            } else {
                System.out.println("Datos actuales do comercial: ");
                System.out.println(comercialExistente);

                System.out.println("Nuevo nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Nuevo apellido1: ");
                String apellido1 = sc.nextLine();
                System.out.println("Nuevo apellido2: ");
                String apellido2 = sc.nextLine();
                System.out.println("Nuevo comisión: ");
                float comision = Integer.parseInt(sc.nextLine());

                Comercial comercialActualizado = new Comercial(id, nombre, apellido1, apellido2, comision);

                int filasAfectadas = ComercialDAO.updateComercial(comercialActualizado);
                if (filasAfectadas > 0) {
                    System.out.println("Comercial actualizado correctamente.");
                } else {
                    System.out.println("Error al actualizar el comercial. ");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void cargarComercial() {
        System.out.println("Listando todos los comerciales...");
        try {
            Comercial[] comerciantes = ComercialDAO.cargarComercial();
            for (Comercial comercial : comerciantes) {
                System.out.println(comercial);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void eliminarComercial() {
        System.out.println("Eliminando comercial...");
        System.out.println("Introduce el ID del comercial a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            int filasAfectadas = ComercialDAO.eliminarComercial(id);
            if (filasAfectadas > 0) {
                System.out.println("Comercial eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar comercial.Comercial no encontrado.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void insertarPedido() throws ParseException, SQLException, ClassNotFoundException {
        System.out.println("Insertando pedido...");
        System.out.print("Total: ");
        double total = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha: ");
        LocalDate fecha = LocalDate.parse(sc.nextLine());
        System.out.print("ID_Cliente: ");
        int id_cliente = Integer.parseInt(sc.nextLine());
        System.out.print("ID_Comercial: ");
        int id_comercial = Integer.parseInt(sc.nextLine());

        Pedido nuevoPedido = new Pedido(total, id_cliente, id_comercial);
             

        try {
            nuevoPedido.setFecha(fecha);
            int filasAfectadas = PedidoDAO.insertarPedido(nuevoPedido);
            PedidoDAO.updatePedido(nuevoPedido);
            
            if (filasAfectadas > 0) {
                System.out.println("Pedido insertado correctamente.");
            } else {
                System.out.println("Error al insertar el pedido.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }              
    }

    private static void updatePedido() throws ParseException {
        System.out.println("Actualizando pedido...");
        System.out.println("Ingrese el ID del pedido: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            Pedido pedidoExistente = PedidoDAO.buscarPedido(id);
            if (pedidoExistente == null) {
                System.out.println("Pedido no encontrado.");
            } else {
                System.out.println("Datos actuales del pedido.");
                System.out.println(pedidoExistente);

                System.out.println("Actualizando pedido...");
                System.out.print("Nuevo total: ");
                double total = Double.parseDouble(sc.nextLine());
                System.out.print("Nueva fecha: ");
                LocalDate fecha = LocalDate.parse(sc.nextLine());
//                pedidoExistente.setFecha(fecha);
                System.out.print("Nuevo ID_Cliente: ");
                int id_cliente = Integer.parseInt(sc.nextLine());
                System.out.print("Nuevo ID_Comercial: ");
                int id_comercial = Integer.parseInt(sc.nextLine());
                
                Pedido pedidoActualizado = new Pedido(id, total, fecha, id_cliente, id_comercial);
                pedidoActualizado.setFecha(fecha);
                int filasAfectadas = PedidoDAO.updatePedido(pedidoActualizado);
                
                if (filasAfectadas > 0) {
                    System.out.println("Pedido actualizado correctamente.");
                } else {
                    System.out.println("Error al actualizar el pedido.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void cargarPedido() throws Exception {
        System.out.println("Listando todos los pedidos...");
        try {
            Pedido[] pedido = PedidoDAO.cargarPedido();
            for (Pedido pedidos : pedido) {
                System.out.println(pedidos);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void eliminarPedido() throws ClassNotFoundException {
        System.out.println("Eliminando pedido...");
        System.out.println("Ingrese el ID del pedido a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        int filasAfectadas = PedidoDAO.eliminarPedido(id);
        if (filasAfectadas > 0) {
            System.out.println("Pedido eliminado correctamente.");
        } else {
            System.out.println("Error al elminar el pedido. Pedido no encontrado");
        }
    }
    
}//fin class

