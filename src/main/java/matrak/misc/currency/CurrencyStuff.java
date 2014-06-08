package matrak.misc.currency;

import static matrak.misc.Util.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyStuff {
	
	public static void main(String[] args) 
	{
		log(" --------- Rounding errors with float  --------- ");
		float f1 = 1.12f;
		float f2 = 1.31f;
		float f1f2 = f1 + f2;
		log(f1, "+", f2, f1f2, "f1f2 == 2.43", f1f2 == 2.43);
		
		log(" --------- BigDecimal  --------- ");
		BigDecimal bd1 = new BigDecimal("1.12");
		BigDecimal bd2 = new BigDecimal("1.31");
		BigDecimal bd1bd2 = bd1.add(bd2);
		log("new BigDecimal(f1) - f1", new BigDecimal(f1).subtract(new BigDecimal( String.valueOf(f1) )));
		log(bd1, "+", bd2, bd1bd2, "bd1bd2 == 2.43", bd1bd2.equals( new BigDecimal("2.43") ));
		
		log("NumberFormat.getCurrencyInstance().format(bd1)",          NumberFormat.getCurrencyInstance().format(bd1));
		log("NumberFormat.getCurrencyInstance(Locale.US).format(bd1)", NumberFormat.getCurrencyInstance(Locale.US).format(bd1));
		
		BigDecimal sum = new BigDecimal("145");
		BigDecimal divided = sum.divide(new BigDecimal("7"), 2, RoundingMode.CEILING);
		log("145 / 7", divided);
	}
	
}
