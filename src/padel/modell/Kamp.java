package padel.modell;

import java.util.ArrayList;
import java.util.List;

public class Kamp {
    private Lag lag1;
    private Lag lag2;
    private List<PadelSett> spilteSett;
    private Spill gjeldendeSpill;
    private int vunnetSettLag1;
    private int vunnetSettLag2;

    public Kamp(Lag lag1, Lag lag2) {
        this.lag1 = lag1;
        this.lag2 = lag2;
        this.spilteSett = new ArrayList<>();
        this.vunnetSettLag1 = 0;
        this.vunnetSettLag2 = 0;

        startNyttSett();
    }

    private void startNyttSett() {
        PadelSett nyttSett = new PadelSett();
        spilteSett.add(nyttSett);
        gjeldendeSpill = new Spill();
    }

    public boolean registrerPoeng(Lag vinnendeLag) {
        if (erKampenVunnet()) {
            return true;
        }

        int lagNummer = (vinnendeLag == lag1) ? 1 : 2;

        PadelSett aktivtSett = spilteSett.getLast();

        boolean spillVunnet = gjeldendeSpill.leggTilPoeng(lagNummer);

        if (spillVunnet) {
            int vinnerAvSpillet = gjeldendeSpill.vinner();
            aktivtSett.leggTilSpill(vinnerAvSpillet);

            if (aktivtSett.erFerdig()) {

                if (aktivtSett.vinner() == 1) {
                    vunnetSettLag1++;
                } else {
                    vunnetSettLag2++;
                }

                if (erKampenVunnet()) {
                    return true;
                }

                startNyttSett();
            } else {
                gjeldendeSpill = new Spill();
            }
        }
        return false;
    }

    public boolean erKampenVunnet() {
        return vunnetSettLag1 == 2 || vunnetSettLag2 == 2;
    }

    public String getVinnerNavn() {
        if (erKampenVunnet()) {
            return (vunnetSettLag1 > vunnetSettLag2) ? lag1.getNavn() : lag2.getNavn();
        }
        return null;
    }

    public String hentHeleStillingen() {
        String matchStilling = "Match: " + vunnetSettLag1 + "-" + vunnetSettLag2;

        PadelSett aktivtSett = spilteSett.getLast();
        String settStilling = " | Sett: " + aktivtSett.getStilling();

        String spillStilling = " | Spill: " + gjeldendeSpill.getStilling();

        return matchStilling + settStilling + spillStilling;
    }

    public Lag getLag1() {
        return lag1;
    }

    public Lag getLag2() {
        return lag2;
    }
}