package com.mycompany.bankbostoncuentas;

// Clase CuentaCorriente representa una cuenta bancaria con número y saldo
public class CuentaCorriente {

    private static int contadorCuentas = 100000000; // Generador automático de números de cuenta
    private int numeroCuenta;
    private int saldo;

    public CuentaCorriente() {
        this.numeroCuenta = contadorCuentas++;
        this.saldo = 0; // saldo inicial
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    // Método para depositar dinero en la cuenta
    public void depositar(int monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("¡Depósito realizado de manera exitosa!");
            System.out.println("Usted tiene un saldo actual de $" + saldo + " pesos.");
        } else {
            System.out.println("El monto a depositar debe ser mayor que cero.");
        }
    }

    // Método para girar (retirar) dinero de la cuenta
    public void girar(int monto) {
        if (monto <= 0) {
            System.out.println("El monto a girar debe ser mayor que cero.");
        } else if (monto > saldo) {
            System.out.println("Fondos insuficientes para realizar el giro.");
        } else {
            saldo -= monto;
            System.out.println("¡Giro realizado de manera exitosa!");
            System.out.println("Usted tiene un saldo actual de $" + saldo + " pesos.");
        }
    }

    // Método para consultar el saldo actual
    public void consultarSaldo() {
        System.out.println("Saldo actual: $" + saldo + " pesos.");
    }
}
