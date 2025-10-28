package padel.modell;

public class Lag {
    private Spiller spiller1;
    private Spiller spiller2;
    private int antallVunneSpill;
    private int antallvunneSet;

    public Lag(Spiller spiller1, Spiller spiller2) {
        this.spiller1 = spiller1;
        this.spiller2 = spiller2;
        this.antallVunneSpill = 0;
        this.antallvunneSet = 0;
    }

    public String getLagNavn() {
        return spiller1.getNavn() + " og " + spiller2.getNavn();
    }

    public int getAntallVunneSpill() {
        return antallVunneSpill;
    }

    public int  getAntallvunneSet() {
        return antallvunneSet;
    }

    public void vinnSpill(){
        this.antallVunneSpill++;
    }
    public void vinnSet(){
        this.antallvunneSet++;

        this.antallVunneSpill = 0;
    }

    public String getNavn() {
        return spiller1.getNavn() + " og " + spiller2.getNavn();
    }
}
