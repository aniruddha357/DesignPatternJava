public class CalculateBill {

    public static void main(String[] args) {
        System.out.println("Getting the Residential Bill for a person who as 180 units of electricity");
        Bill residentialBill = BillFactory.getBillByType("RESIDENTIAL");
        residentialBill.addUnits(180);
        System.out.println(residentialBill.calculateBill());

        System.out.println("Getting the Commercial Bill for a person who as 180 units of electricity");
        Bill commercialBill = BillFactory.getBillByType("COMMERCIAL");
        commercialBill.addUnits(180);
        System.out.println(commercialBill.calculateBill());
    }
}
