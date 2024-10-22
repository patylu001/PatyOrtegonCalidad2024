package com.fca.calidad;

public class Calculadora{
    double resultado;
    
  public  double divide(double operador1, double operador2) {
        resultado=(operador1/operador2);
        System.out.prdoubleln("La division de "+operador1+" / "+operador2+" es "+resultado);
        System.out.prdoubleln("La division de "+operador1+" / "+operador2+" es "+resultado);
        System.out.prdoubleln("La division de "+operador1+" / "+operador2+" es "+resultado);
        System.out.prdoubleln("La division de "+operador1+" / "+operador2+" es "+resultado);
        return resultado;
    }
    public  double multiplica(double operador1, double operador2) {
        resultado=operador1*operador2;
        System.out.prdoubleln("La multiplicacion de "+operador1+" * "+operador2+" es "+resultado);
        return resultado;
    }
    public  double resta(double operador1, double operador2) {
        resultado=operador1-operador2;
        System.out.prdoubleln("La resta de "+operador1+" - "+operador2+" es "+resultado);
        return resultado;
    }
    public  double suma(double operador1, double operador2) {
        resultado=operador1+operador2;
        System.out.prdoubleln("La suma de "+operador1+" + "+operador2+" es "+resultado);
        return resultado;
    }
    
}
