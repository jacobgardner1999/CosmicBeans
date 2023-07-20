package Components;

public class Traits {
    private Integer perception;

    public Traits (Integer perception) {
        this.perception = perception;
    }

    public Integer getPerception() {
        return perception;
    }

    public void updateTraits(Traits traits) {
        perception += traits.getPerception();
    }
}
