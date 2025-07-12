package com.willians.ListifyShop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CpfValid, String> {

    @Override
    public void initialize(CpfValid cpf){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!value.matches("[0-9]+") || value.contains(" ") || value.length() != 11){
            return false;
        }

        int acumuladorParaDitio1 = 0;
        int acumuladorParaDitio2 = 0;
        final int ONZE = 11;

        for (int i = 0; i < 10; i++){
            int valor = Integer.parseInt(String.valueOf(value.charAt(i)));
            if(i < 9){
                acumuladorParaDitio1 +=  valor * (ONZE - (i + 1));
            }
            acumuladorParaDitio2 += valor * (ONZE - i);
        }

        int modulo1 = acumuladorParaDitio1 % ONZE;
        int modulo2 = acumuladorParaDitio2 % ONZE;

        int resto1 = ONZE - modulo1;
        int resto2 = ONZE - modulo2;

        Integer digito1 = resto1 < 10 ? resto1 : 0;
        Integer digito2 = resto2 < 10 ? resto2 : 0;

        Integer digito1Origem = Integer.parseInt(String.valueOf(value.charAt(9)));
        Integer digito2Origem = Integer.parseInt(String.valueOf(value.charAt(10)));

        if (digito1.equals(digito1Origem) && digito2.equals(digito2Origem)){
            return true;
        }

        return false;
    }
}
