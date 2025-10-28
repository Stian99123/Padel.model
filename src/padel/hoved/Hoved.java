package padel.hoved;

import javax.swing.*;

import padel.modell.Spiller;
import padel.modell.Lag;
import padel.modell.Kamp;
import padel.modell.Spiller;
import java.util.Scanner;

public class Hoved {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scanner =new Scanner(System.in);
        System.out.println("--- Padel Kamp Oppsett ---");

        System.out.print("Skriv inn navn på Spiller 1 (Lag 1): ");
        String navn1 = scanner.nextLine();

        System.out.print("Skriv inn navn på Spiller 2 (Lag 1): ");
        String navn2 = scanner.nextLine();

        System.out.print("Skriv inn navn på Spiller 3 (Lag 2): ");
        String navn3 = scanner.nextLine();

        System.out.print("Skriv inn navn på Spiller 4 (Lag 2): ");
        String navn4 = scanner.nextLine();

        Spiller spiller1 = new Spiller(navn1);
        Spiller spiller2 = new Spiller(navn2);
        Spiller spiller3 = new Spiller(navn3);
        Spiller spiller4 = new Spiller(navn4);

        Lag lag1 = new Lag(spiller1, spiller2);
        Lag lag2 = new Lag(spiller3, spiller4);

        Kamp padelKamp = new Kamp(lag1, lag2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new padel.hoved.Grafikk(padelKamp);
            }
        });
    }
}