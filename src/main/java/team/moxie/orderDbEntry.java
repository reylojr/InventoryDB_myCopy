package team.moxie;

public class orderDbEntry {
    private String date;
    private String email;
    private String shippingAddress;
    private String productID;
    private int quantity;
    private String [] statuses = { "ordered", "processing", "complete" };
    private String status;

    public orderDbEntry(String date, String email, String shippingAddress, String productID, int quantity){
        this.date = date;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.productID = productID;
        this.quantity = quantity;
        status = statuses[1];
    }


    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }


    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public String getShippingAddress(){
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress){
        this.shippingAddress = shippingAddress;
    }


    public String getProductID(){
        return productID;
    }
    public void setProductID(String productID){
        this.productID = productID;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String toString() {
        return "Data Base Order Entry\n" +
                "Purchase Date: " + date + "\n" +
                "Email Address: " + email + "\n" +
                "Shipping Address: " + shippingAddress + "\n" +
                "Product ID: " + productID + "\n" +
                "Quantity: " + quantity + "\n" +
                "Status: " + status + "\n";
    }
}
