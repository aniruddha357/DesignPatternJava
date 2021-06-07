public class BillFactory {
    private static final float commercialCharge = 7.5f;
    private static final float residentialCharge = 5f;

    public static Bill getBillByType(String billType){
        if (billType.equalsIgnoreCase("COMMERCIAL")){
            return new CommercialBill(commercialCharge);
        }

        if (billType.equalsIgnoreCase("RESIDENTIAL")){
            return new ResidentialBill(residentialCharge);
        }

        return null;

    }

}
