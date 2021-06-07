public abstract class AbstractBill implements Bill {
    private final float charge;
    private int units = 0;

    protected AbstractBill(float charge){
        this.charge = charge;
    }

    @Override
    public void addUnits(int units) {
        this.units = units;
    }

    @Override
    public float calculateBill() {
        return this.charge * this.units;
    }

}
