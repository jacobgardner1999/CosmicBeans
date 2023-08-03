package Components;

public class Traits {
    private Integer perception;
    private Integer hustle;
    private Integer charisma;
    private Integer snootiness;

    public Traits (Integer perception, Integer hustle, Integer charisma, Integer snootiness) {
        this.perception = perception;
        this.hustle = hustle;
        this.charisma = charisma;
        this.snootiness = snootiness;
    }

    public Traits () {
        this.perception = 0;
        this.hustle = 0;
        this.charisma = 0;
        this.snootiness = 0;
    }
    public Integer getPerception() {
        return perception;
    }
    public Integer getHustle() {
        return hustle;
    }
    public Integer getCharisma() {
        return charisma;
    }

    public Integer getSnootiness() {
        return snootiness;
    }

    public boolean isLessThan(Traits traits) {
        return (traits.perception >= perception)
                && (traits.hustle >= hustle)
                && (traits.charisma >= charisma)
                && (traits.snootiness >= snootiness);
    }

    public void updateTraits(Traits traits) {
        perception += traits.getPerception();
        hustle += traits.getHustle();
        charisma += traits.getCharisma();
        snootiness += traits.getSnootiness();
    }
}
