package com.mycompany.bankbostoncuentas;

import java.util.ArrayList;
import java.util.Scanner;

// Clase principal que contiene el menú y la lógica de interacción con el usuario
public class BankBostonCuentas {

    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("=======================================");
        System.out.println("   ¡Bienvenido a Bank Boston Cuentas! ");
        System.out.println("    Su confianza, nuestro compromiso. ");
        System.out.println("=======================================\n");
        
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos de cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    // Registro de cliente con validaciones
                    String rut;
                    do {
                        System.out.print("Ingrese RUT (formato XXXXXXXX-X, puede incluir puntos): ");
                        rut = scanner.nextLine();
                        if (!Cliente.validarRut(rut)) {
                            System.out.println("RUT inválido. Intente nuevamente.");
                        } else if (buscarClientePorRut(clientes, rut) != null) {
                            System.out.println("El RUT ya está registrado. Intente con otro.");
                        } else {
                            break; // RUT válido y no registrado
                        }
                    } while (true);

                    String nombre;
                    do {
                        System.out.print("Ingrese nombre: ");
                        nombre = scanner.nextLine();
                    } while (nombre.trim().isEmpty());

                    String apellidoPaterno;
                    do {
                        System.out.print("Ingrese apellido paterno: ");
                        apellidoPaterno = scanner.nextLine();
                    } while (apellidoPaterno.trim().isEmpty());

                    String apellidoMaterno;
                    do {
                        System.out.print("Ingrese apellido materno: ");
                        apellidoMaterno = scanner.nextLine();
                    } while (apellidoMaterno.trim().isEmpty());

                    String domicilio;
                    do {
                        System.out.print("Ingrese domicilio: ");
                        domicilio = scanner.nextLine();
                    } while (domicilio.trim().isEmpty());

                    String comuna;
                    do {
                        System.out.print("Ingrese comuna: ");
                        comuna = scanner.nextLine();
                    } while (comuna.trim().isEmpty());

                    String region;
                    do {
                        System.out.print("Ingrese Región: ");
                        region = scanner.nextLine();
                    } while (region.trim().isEmpty());                    
                    
                    String telefono;
                    do {
                        System.out.print("Ingrese número de teléfono (9xxxxxxxx o 2xxxxxxxx): ");
                        telefono = scanner.nextLine();
                        if (!Cliente.validarTelefono(telefono)) {
                            System.out.println("Teléfono inválido. Intente nuevamente (ej: 9xxxxxxxx o 2xxxxxxxx).");
                        }
                    } while (!Cliente.validarTelefono(telefono));

                    try {
                        Cliente nuevoCliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna, region, telefono);
                        clientes.add(nuevoCliente);
                        System.out.println("¡Cliente registrado exitosamente! Número de cuenta: " + nuevoCliente.getCuenta().getNumeroCuenta());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al registrar cliente: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Visualización de datos de cliente
                    System.out.print("Ingrese RUT del cliente: ");
                    String rutBuscar = scanner.nextLine();
                    Cliente clienteEncontrado = buscarClientePorRut(clientes, rutBuscar);
                    if (clienteEncontrado != null) {
                        clienteEncontrado.mostrarDatos();
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 3:
                    // Depósito
                    System.out.print("Ingrese RUT del cliente: ");
                    rutBuscar = scanner.nextLine();
                    clienteEncontrado = buscarClientePorRut(clientes, rutBuscar);
                    if (clienteEncontrado != null) {
                        System.out.print("Ingrese monto a depositar: $");
                        int monto = Integer.parseInt(scanner.nextLine());
                        clienteEncontrado.getCuenta().depositar(monto);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 4:
                    // Giro
                    System.out.print("Ingrese RUT del cliente: ");
                    rutBuscar = scanner.nextLine();
                    clienteEncontrado = buscarClientePorRut(clientes, rutBuscar);
                    if (clienteEncontrado != null) {
                        System.out.print("Ingrese monto a girar: $");
                        int monto = Integer.parseInt(scanner.nextLine());
                        clienteEncontrado.getCuenta().girar(monto);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 5:
                    // Consultar saldo
                    System.out.print("Ingrese RUT del cliente: ");
                    rutBuscar = scanner.nextLine();
                    clienteEncontrado = buscarClientePorRut(clientes, rutBuscar);
                    if (clienteEncontrado != null) {
                        clienteEncontrado.getCuenta().consultarSaldo();
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("\nGracias por usar Bank Boston Cuentas. ¡Que tenga un excelente día!");                  
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 6);
    }

    // Método auxiliar para buscar cliente por RUT
    private static Cliente buscarClientePorRut(ArrayList<Cliente> clientes, String rut) {
        String rutBuscar = rut.replace(".", "").toUpperCase();
        for (Cliente c : clientes) {
            if (c.getRut().replace(".", "").toUpperCase().equals(rutBuscar)) {
                return c;
            }
        }
        return null;
    }
}
