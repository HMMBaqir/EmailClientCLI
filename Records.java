import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Records {
    public int recipientCount = 0;
    public List<emailObject> emailArray = new ArrayList<>();

    private List<Recipient> recipientArray = new ArrayList<>();
    private List<Recipient> birthdayArray = new ArrayList<>();

    public void getEmails(String filename) {
        this.emailArray = readEmailObjects.readEmail(filename);
    }

    public void getRecipients(String filename) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String recipientInfo;
            while ((recipientInfo = buffer.readLine()) != null) {
                this.recipientArray.add(RecipientFactory.createRecipient(recipientInfo));
            }
            this.recipientCount = recipientArray.size();
        } catch (FileNotFoundException e) {
            this.recipientCount = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRecipientToFile(String recipientInfo, String filename) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename, true))) {
            buffer.write(recipientInfo + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRecipientUsingString(String recipientString, String filename) {
        addRecipientToFile(recipientString, filename);
        Recipient recipient = RecipientFactory.createRecipient(recipientString);
        this.recipientArray.add(recipient);
        this.recipientCount++;
        if (checkBirthday(recipient, LocalDate.now())) {
            emailHandler handler = emailHandler.getInstance();
            emailObject obj = handler.getBirthdayWish(recipient);
            handler.sendAndSave(obj);
        }
        if (!Objects.equals(recipient.getType(), "Official")) {
            this.birthdayArray.add(recipient);
        }
    }

    public boolean checkBirthday(Recipient recipient, LocalDate date) {
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        String type = recipient.getType();
        switch (type) {
            case "Personal":
                personalRecipient friend = (personalRecipient) recipient;
                LocalDate fbirthday = friend.getBirthday();
                return ((month == fbirthday.getMonthValue()) && (day == fbirthday.getDayOfMonth()));
            case "Office_friend":
                officialFriendRecipient Ofriend = (officialFriendRecipient) recipient;
                LocalDate Obirthday = Ofriend.getBirthday();
                return ((month == Obirthday.getMonthValue()) && (day == Obirthday.getDayOfMonth()));
            default:
                return false;
        }
    }

    public void getBirthdayArray() {
        if (this.recipientArray == null) {
            return;
        }
        for (Recipient recipient : this.recipientArray) {
            if (!Objects.equals(recipient.getType(), "Official")) {
                this.birthdayArray.add(recipient);
            }
        }
    }

    public void wishForToday() {
        LocalDate today = LocalDate.now();
        List<Recipient> birthdayArray = this.getRecipientsOnBirthday(today);
        List<emailObject> todayEmails = this.getEmailsOnDay(today);
        emailHandler handler = emailHandler.getInstance();
        for (Recipient recipient : birthdayArray) {
            emailObject wish = handler.getBirthdayWish(recipient);
            for (emailObject email : this.emailArray) {
                if (email.equals(wish)) {
                    continue;
                }
                handler.sendAndSave(wish);
                this.emailArray.add(wish);
            }
        }
    }

    public List<Recipient> getRecipientsOnBirthday(LocalDate birthday) {
        List<Recipient> birthdayOnDay = new ArrayList<>();
        for (Recipient recipient : this.birthdayArray) {
            if (checkBirthday(recipient, birthday)) {
                birthdayOnDay.add(recipient);
            }
        }
        return birthdayOnDay;
    }

    public List<emailObject> getEmailsOnDay(LocalDate date) {
        List<emailObject> emailsOnDay = new ArrayList<>();
        if (this.emailArray.isEmpty()) {
            return emailsOnDay;
        }
        for (emailObject obj : this.emailArray) {
            if (obj.getDate().equals(date)) {
                emailsOnDay.add(obj);
            }
        }
        return emailsOnDay;
    }
}