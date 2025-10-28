package padel.modell;

public class Spill {
    private int poengLag1;
    private int poengLag2;

    public Spill(){
        this.poengLag1 = 0;
        this.poengLag2 = 0;
    }

    public boolean leggTilPoeng(int lagNummer){
        if (erFerdig()){
            return true;
        }

        if (lagNummer == 1) {
            poengLag1++;
        } else if (lagNummer == 2) {
            poengLag2++;
        }

        return erFerdig();
    }

    public  boolean erFerdig(){
        return  (poengLag1 >= 4 && poengLag1 >= poengLag2 + 2) ||
                (poengLag2 >= 4 && poengLag2 >= poengLag1 + 2);
    }

    public int vinner() {
        if (erFerdig()) {
            if (poengLag1 > poengLag2) return 1;
            return 2;
        }
        return 0; // Ikke ferdig
    }

    public String getStilling() {
        if (poengLag1 >= 3 && poengLag2 >= 3) {
            int diff = poengLag1 - poengLag2;
            if (diff == 0) {
                return "Deuce";
            } else if (diff == 1) {
                return "Adv Lag 1";
            } else if (diff == -1) {
                return "Adv Lag 2";
            } else if (diff >= 2) {
                return "Game Lag 1";
            } else if (diff <= -2) {
                return "Game Lag 2";
            }
        }

        String poengStr1 = getPoengString(poengLag1);
        String poengStr2 = getPoengString(poengLag2);

        if (poengLag1 == poengLag2 && poengLag1 < 3) {
            return poengStr1 + "-0";
        }

        return poengStr1 + "-" + poengStr2;
    }

    private String getPoengString(int poeng) {
        return switch (poeng) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> "Error";
        };
    }
}