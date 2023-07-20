package Components;

public class Traits {
    private Integer perception;

    public Traits (Integer perception) {
        this.perception = perception;
    }

    public Traits () {
        this.perception = 0;
    }
    public Integer getPerception() {
        return perception;
    }

    public boolean checkValid(Traits traits) {
        return traits.perception >= perception;
    }

    public void updateTraits(Traits traits) {
        perception += traits.getPerception();
    }
}
