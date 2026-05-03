public class ItemRequest {
    private String itemId;
    private int quantity;

    public ItemRequest(String itemId, int quantity) {
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("ItemId cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}