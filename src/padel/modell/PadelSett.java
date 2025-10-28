package padel.modell;

public class PadelSett {
    private int spillLag1;
    private int spillLag2;

    public PadelSett() {
        this.spillLag1 = 0;
        this.spillLag2 = 0;
    }

    public void leggTilSpill(int lagNummer) {
        if (lagNummer == 1) {
            spillLag1++;
        } else if (lagNummer == 2) {
            spillLag2++;
        }
    }

    public boolean erFerdig() {
        boolean seierSeks = (spillLag1 >= 6 || spillLag2 >= 6) && Math.abs(spillLag1 - spillLag2) >= 2;
        boolean seierTiebreak = (spillLag1 == 7 || spillLag2 == 7) && Math.abs(spillLag1 - spillLag2) == 1;

        return seierSeks || seierTiebreak;
    }

    public int vinner() {
        if (erFerdig()) {
            if (spillLag1 > spillLag2) return 1;
            return 2;
        }
        return 0;
    }

    public String getStilling() {
        return spillLag1 + "-" + spillLag2;
    }
}