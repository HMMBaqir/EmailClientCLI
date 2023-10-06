
import java.time.LocalDate;

public class officialFriendRecipient extends Recipient {
    private String designation;
    private LocalDate birthday;

    public officialFriendRecipient(String name, String email, String designation, LocalDate birthday) {
        super(name, email, "Office Friend");
        this.designation = designation;
        this.birthday = birthday;
    }

    public String getDesignation() {
        return designation;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}