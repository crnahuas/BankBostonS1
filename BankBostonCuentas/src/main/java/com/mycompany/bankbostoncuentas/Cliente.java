package com.mycompany.bankbostoncuentas;

// Representa a un cliente del banco. Contiene datos personales, dirección, teléfono y su cuenta corriente.
public class Cliente {

    private String rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private String comuna;
    private String region;
    private String telefono;
    private CuentaCorriente cuenta;

    // Constructor
    public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
            String domicilio, String comuna, String region, String telefono) {
        if (rut == null || !validarRut(rut)) {
            throw new IllegalArgumentException("RUT inválido.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío.");
        }
        if (apellidoMaterno == null || apellidoMaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido materno no puede estar vacío.");
        }
        if (region == null || region.trim().isEmpty()) {
            throw new IllegalArgumentException("La región no puede estar vacía.");
        }
        if (domicilio == null || domicilio.trim().isEmpty()) {
            throw new IllegalArgumentException("El domicilio no puede estar vacío.");
        }
        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException("La comuna no puede estar vacía.");
        }
        if (telefono == null || !validarTelefono(telefono)) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }

        this.rut = limpiarRut(rut);
        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.domicilio = domicilio.trim();
        this.comuna = comuna.trim();
        this.region = region.trim();
        this.telefono = telefono.trim();
        this.cuenta = new CuentaCorriente(); // número de cuenta se genera automático
    }

    // Getter
    public String getRut() {
        return rut;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    // Validar RUT
    public static boolean validarRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return false;
        }
        String rutLimpio = rut.replace(".", "").replace(" ", "").toUpperCase();

        if (!rutLimpio.matches("^\\d{7,8}-[0-9K]$")) {
            return false;
        }

        String[] partes = rutLimpio.split("-");
        String cuerpo = partes[0];
        char dv = partes[1].charAt(0);

        int suma = 0;
        int multiplo = 2;

        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(cuerpo.charAt(i)) * multiplo;
            multiplo = (multiplo == 7) ? 2 : multiplo + 1;
        }

        int resto = 11 - (suma % 11);
        char dvEsperado;

        if (resto == 11) {
            dvEsperado = '0';
        } else if (resto == 10) {
            dvEsperado = 'K';
        } else {
            dvEsperado = (char) (resto + '0');
        }

        return dv == dvEsperado;
    }

    private String limpiarRut(String rut) {
        return rut.replace(".", "").toUpperCase();
    }

    // Validar Teléfono
    public static boolean validarTelefono(String telefono) {
        if (telefono == null) {
            return false;
        }
        String tel = telefono.trim();
        return tel.matches("^(9\\d{8}|2\\d{7})$");
    }
    
    // Datos Cliente
    public void mostrarDatos() {
        System.out.println("Datos del cliente:");
        System.out.println("RUT: " + rut);
        System.out.println("Nombre completo: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno);
        System.out.println("Domicilio: " + domicilio);
        System.out.println("Comuna: " + comuna);
        System.out.println("Región: " + region);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Número de cuenta: " + cuenta.getNumeroCuenta());
        cuenta.consultarSaldo();
    }
}
