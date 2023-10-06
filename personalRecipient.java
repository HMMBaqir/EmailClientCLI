import java.time.LocalDate;

public class personalRecipient extends Recipient {
    private String nickname;
    private LocalDate birthday;

    public personalRecipient(String name, String email, String type, String nickname, LocalDate birthday) {
        super(name, email, type);
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}