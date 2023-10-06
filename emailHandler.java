public class emailHandler {

    private static emailHandler instance;
    private SendEmailTLS sendEmailTLS;
    public static saveEmailObjects saveEmailObjects;

    private emailHandler() {
        this.sendEmailTLS = new SendEmailTLS();
        this.saveEmailObjects = new saveEmailObjects();
    }

    public static emailHandler getInstance() {
        if (instance == null) {
            instance = new emailHandler();
        }
        return instance;
    }

    public static void sendAndSave(emailObject emailObject) {
        SendEmailTLS.sendEmail(emailObject);
        saveEmailObjects.saveEmail(emailObject, "serializedemails.ser");
    }

    public void sendAndSaveEmail(emailObject emailObject) {
        sendEmailTLS.sendEmail(emailObject);
        saveEmailObjects.saveEmail(emailObject, "serializedemails.ser");
    }

    public emailObject createEmailObject(String stringInput) {
        if (stringInput == null || stringInput.isEmpty()) {
            throw new IllegalArgumentException("Invalid input string");
        }
        int p = stringInput.indexOf(',');
        String email = stringInput.substring(0, p).strip();
        String rest = stringInput.substring(p + 1);
        p = rest.indexOf(',');
        String subject = rest.substring(0, p).strip();
        String content = rest.substring(p + 1).strip();
        return new emailObject(email, subject, content);
    }

    public String getEmailString(emailObject emailObject) {
        return emailObject.getRecipientEmail() + "," + emailObject.getEmailSubject() + ","
                + emailObject.getEmailContent();
    }

    public emailObject getBirthdayWish(Recipient recipient) {
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient object is null");
        }
        String emailAddress = recipient.getEmail();
        String subject = "Happy Birthday!";
        String type = recipient.getType();
        String message = " Baqir";
        switch (type) {
            case "Office Friend":
                message = "Wish you a Happy Birthday." + message;
                break;
            case "Personal":
                message = "hugs and love on your birthday." + message;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of recipient when generating birthday wish");
        }
        return new emailObject(emailAddress, subject, message);
    }
}