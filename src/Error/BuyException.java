package Error;

public class BuyException extends IndexOutOfBoundsException {

    public BuyException(String message) {
        super(message);
    }

    public static void throwItemBuyException(String message) {
        throw new BuyException("Item buy exception: " + message);
    }

    public static void throwFullInventoryException(String message) {
        throw new BuyException("Full inventory exception: " + message);
    }

    public static void throwEmptyItemException(String message) {
        throw new BuyException("Empty item exception: " + message);
    }
}
