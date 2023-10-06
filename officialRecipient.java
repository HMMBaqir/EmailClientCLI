public class officialRecipient extends Recipient {
    private String designation;

    public officialRecipient(String name, String email, String designation) {
        super(name, email, "Official");
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
