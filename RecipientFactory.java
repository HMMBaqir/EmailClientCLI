import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecipientFactory {
    public static Recipient createRecipient(String inputString) {
        int p = inputString.indexOf(':');
        String recipientType = inputString.substring(0, p).strip();
        String[] recipientInfo = inputString.substring(p + 1).split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        switch (recipientType) {
            case "Official":
                return new officialRecipient(recipientInfo[0].strip(), recipientInfo[1].strip(),
                        recipientInfo[2].strip());
            case "Office Friend":
                LocalDate obirthday = LocalDate.parse(recipientInfo[3].strip(), formatter);
                return new officialFriendRecipient(recipientInfo[0].strip(), recipientInfo[1].strip(),
                        recipientInfo[2].strip(), obirthday);
            case "Personal":
                LocalDate fbirthday = LocalDate.parse(recipientInfo[3].strip(), formatter);
                return new personalRecipient(recipientInfo[0].strip(), recipientInfo[1].strip(),
                        recipientInfo[2].strip(), recipientInfo[3].strip(), fbirthday);
            default:
                System.out.println("Invalid Input");
                return null;
        }
    }
}