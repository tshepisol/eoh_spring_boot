package za.co.digitalplatoon.invoiceservice.util;

import java.math.BigDecimal;

public class Converter {

    public static BigDecimal roundDecimal(BigDecimal bigDecimal){
        if(bigDecimal ==null)
            return  null;
        else
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
