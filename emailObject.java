import java.time.LocalDate;

public class emailObject {
    private String recipientEmail;
    private String emailSubject;
    private String emailContent;
    private LocalDate date;

    public emailObject(String recipientEmail, String emailSubject, String emailContent) {
        this.recipientEmail = recipientEmail;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
        this.date = LocalDate.now();
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public LocalDate getDate() {
        return date;
    }
}