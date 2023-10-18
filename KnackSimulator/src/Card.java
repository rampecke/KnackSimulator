public class Card {
    private Wert wert;
    private Farbe farbe;

    public Card(Wert wert, Farbe farbe) {
        this.wert = wert;
        this.farbe = farbe;
    }

    public void setWert(Wert wert) {
        this.wert = wert;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public int getWert() {
        return switch (this.wert) {
            case SIEBE -> 7;
            case ACHT -> 8;
            case NEUN -> 9;
            case ZEHN, DAME, BUBE, KÖNIG -> 10;
            case ASS -> 11;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "[" + wert + ", " + farbe + ']';
    }
}

enum Wert {
    SIEBE,
    ACHT,
    NEUN,
    ZEHN,
    BUBE,
    DAME,
    KÖNIG,
    ASS
}

enum Farbe {
    KARO,
    PICK,
    HERZ,
    KREUZ
}