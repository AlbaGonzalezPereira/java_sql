package principal;

import DB.DB;
import implementacion.Departamento;
import implementacion.DepartamentoDAO;
import implementacion.Empleado;
import implementacion.EmpleadoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author alba_
 */
public class Principal {

    private static Scanner sc = new Scanner(System.in);
    private static DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private static EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public static void main(String[] args) throws IOException {
        try {
            DB.open();
            crearMenu();
        } catch (SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
        }
    }

    private static void crearMenu() throws SQLException {
        int opcion;

        do {
            System.out.println("           MENÚ          ");
            System.out.println("*************************");
            System.out.println("1. Dar de alta un empleado");
            System.out.println("2. Dar de baja un empleado");
            System.out.println("3. Insertar departamento");
            System.out.println("4. Eliminar departamento");
            System.out.println("5. Modificar departamento");
            System.out.println("6. Modificar empleado");
            System.out.println("7. Salir");
            System.out.println("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    darDeAltaEmpleado();
                    break;
                case 2:
                    darDeBajaEmpleado();
                    break;
                case 3:
                    insertarDepartamento();
                    break;
                case 4:
                    eliminarDepartamento();
                    break;
                case 5:
                    updateDepartamento();
                    break;
                case 6:
                    updateEmpleado();
                    break;
                case 7:
                    System.out.println("Hasta luego");
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opcion != 6);
    }

    private static void darDeAltaEmpleado() {
        // Lógica para dar de alta un empleado
        System.out.println("Nombre del empleado:");
        String nombre = sc.nextLine();
        System.out.println("NIF:");
        String nif = sc.nextLine();
        System.out.println("Apellido 1:");
        String apellido1 = sc.nextLine();
        System.out.println("Apellido 2:");
        String apellido2 = sc.nextLine();
//        System.out.println("Codigo empleado:");
//        int codigo = Integer.parseInt(sc.nextLine());
        System.out.println("Codigo departamento del empleado:");
        int codigoDepartamento = Integer.parseInt(sc.nextLine());

        Empleado empleado = new Empleado(nif, nombre, apellido1, apellido2, codigoDepartamento);
        //System.out.println(empleadoDAO.insertarEmpleado(empleado));//comprobamos que es lo que nos devuelve
        int filasInsertadas = empleadoDAO.insertarEmpleado(empleado);
        //System.out.println(empleado); //--> comprbamos que hace bien el objeto
        if (filasInsertadas > 0) {
            System.out.println("Empleado insertado correctamente");
        } else {
            System.out.println("Error al insertar el empleado");
        }
    }

    private static void darDeBajaEmpleado() throws SQLException {
        System.out.println("Introduzca el dni del empleado a dar de baja:");
        String dni = sc.nextLine();

        Empleado e = empleadoDAO.buscarEmpleadoNif(dni);
        if (e == null) {
            System.out.println("Empleado no encontrado");
        }

        if (e != null) {
            int filasEliminadas = empleadoDAO.eliminarEmpleado(dni);
            if (filasEliminadas > 0) {
                System.out.println("Empleado eliminado correctamente");
            } else {
                System.out.println("Error al eliminar el empleado");
            }
        }

    }

    private static void insertarDepartamento() throws SQLException {
        System.out.println("Nombre del departamento:");
        String nombre = sc.nextLine();
        System.out.println("Presupuesto del departamento:");
        int presupuesto = Integer.parseInt(sc.nextLine());
        System.out.println("Gastos del departamento:");
        int gastos = Integer.parseInt(sc.nextLine());

        Departamento departamento = new Departamento(nombre, presupuesto, gastos);

        int filasInsertadas = DepartamentoDAO.insertarDepartamento(departamento);
        if (filasInsertadas > 0) {
            System.out.println("Departamento insertado correctamente");
        } else {
            System.out.println("Error al insertar el departamento");
        }
    }

    private static void eliminarDepartamento() throws SQLException {
        System.out.println("Código del departamento a eliminar:");
        int codigo = Integer.parseInt(sc.nextLine());

        int filasEliminadas = DepartamentoDAO.eliminarDepartamento(codigo);
        if (filasEliminadas > 0) {
            System.out.println("Departamento eliminado correctamente");
        } else {
            System.out.println("Error al eliminar el departamento");
        }
    }

    private static void updateDepartamento() {
        // Lógica para modificar un departamento

        System.out.println("Modificar departamento");
        System.out.println("Indique departamento a modificar: ");
        int codigo = Integer.parseInt(sc.nextLine());
        //Departamento departamentoExistente = null;
        try {
            // Obtener el departamento por su código
            Departamento departamento = DepartamentoDAO.buscarDepartamento(codigo);
            if (departamento != null) {
                System.out.println("Departamento encontrado:");
                System.out.println(departamento);

// Solicitar nuevos datos para el departamento
                System.out.println("Ingrese el nuevo nombre del departamento:");
                String nuevoNombre = sc.nextLine();
                System.out.println("Ingrese el nuevo presupuesto del departamento:");
                double nuevoPresupuesto = Double.parseDouble(sc.nextLine());
                System.out.println("Ingrese los nuevos gastos del departamento:");
                double nuevosGastos = Double.parseDouble(sc.nextLine());

                // Actualizar el objeto Departamento con los nuevos datos
                departamento.setNombre(nuevoNombre);
                departamento.setPresupuesto(nuevoPresupuesto);
                departamento.setGastos(nuevosGastos);
                System.out.println(departamento);

                // Actualizar el departamento en la base de datos
                int filasActualizadas = DepartamentoDAO.updateDepartamento(departamento);
                if (filasActualizadas > 0) {
                    System.out.println("Departamento actualizado correctamente");
                } else {
                    System.out.println("Error al actualizar el departamento");
                }
            } else {
                System.out.println("No se encontró un departamento con el código especificado");
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }

    private static void updateEmpleado() {
        // Lógica para modificar un departamento

        System.out.println("Modificar empleado");
        System.out.println("Indique empleado a modificar: ");
        int codigo = Integer.parseInt(sc.nextLine());
        //Departamento departamentoExistente = null;
        try {
            // Obtener el departamento por su código
            Empleado empleado = EmpleadoDAO.buscarEmpleado(codigo);
            if (empleado != null) {
                System.out.println("Empleado encontrado:");
                System.out.println(empleado);

// Solicitar nuevos datos para el departamento
                System.out.println("Ingrese el nuevo nombre del empleado:");
                String nuevoNombre = sc.nextLine();
                System.out.println("Ingrese el primer apellido del empleado:");
                String nuevoApellido1 = sc.nextLine();
                System.out.println("Ingrese el segundo apellido del empleado:");
                String nuevoApellido2 = sc.nextLine();
                System.out.println("Ingrese el nif del empleado:");
                String nuevoNif = sc.nextLine();
                System.out.print("Nuevo codigo del departamento: ");
                int codigoDepartamento = Integer.parseInt(sc.nextLine());

                // Actualizar el objeto Departamento con los nuevos datos
                empleado.setNombre(nuevoNombre);
                empleado.setApellido1(nuevoApellido1);
                empleado.setApellido2(nuevoApellido2);
                System.out.println(empleado);

                // Actualizar el departamento en la base de datos
                int filasActualizadas = EmpleadoDAO.updateEmpleado(empleado);
                if (filasActualizadas > 0) {
                    System.out.println("Empleado actualizado correctamente");
                } else {
                    System.out.println("Error al actualizar el empleado");
                }
            } else {
                System.out.println("No se encontró ningún empleado con el código especificado");
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }

}
