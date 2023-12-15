
package Main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class notasTiempo {
     // Supongamos que tienes dos fechas LocalDate
        LocalDate fechaInicial = LocalDate.of(2020, 1, 1);
        LocalDate fechaFinal = LocalDate.of(2021, 2, 1);

        // Calcula la diferencia entre las fechas
        long diasDeDiferencia = ChronoUnit.DAYS.between(fechaInicial, fechaFinal);

        // También puedes calcular la diferencia en semanas, meses o años si lo deseas
        long semanasDeDiferencia = ChronoUnit.WEEKS.between(fechaInicial, fechaFinal);
        long mesesDeDiferencia = ChronoUnit.MONTHS.between(fechaInicial, fechaFinal);
        long añosDeDiferencia = ChronoUnit.YEARS.between(fechaInicial, fechaFinal);

        LocalDate fechaHoy = LocalDate.now();
        
        
        
        
        // Imprime los resultados
//      System.out.println("Días de diferencia: " + diasDeDiferencia);
//      System.out.println("Semanas de diferencia: " + semanasDeDiferencia);
//      System.out.println("Meses de diferencia: " + mesesDeDiferencia);
//      System.out.println("Años de diferencia: " + añosDeDiferencia);
}
