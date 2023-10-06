import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Email_Client {
    public static void main(String[] args) {
        boolean clientRunning = true;
        Records clientRecords = new Records();

        clientRecords.getRecipients("clientList.txt");
        clientRecords.getBirthdayArray();
        clientRecords.wishForToday();
        clientRecords.getEmails("serializedemails.ser");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter option type:");
            System.out.println("1 - Adding a new recipient");
            System.out.println("2 - Sending an email");
            System.out.println("3 - Printing out all the recipients who have birthdays on given date");
            System.out.println("4 - Printing out details of all the emails sent on the input date");
            System.out.println("5 - Printing out the number of recipient objects in the application");
            System.out.println("6 - Exit Email Client");

            while (clientRunning) {
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        // input format - Official: nimal,nimal@gmail.com,ceo
                        // Use a single input to get all the details of a recipient
                        // code to add a new recipient
                        // store details in clientList.txt file
                        // Hint: use methods for reading and writing files
                        String newRecipientString = scanner.nextLine();
                        clientRecords.addRecipientUsingString(newRecipientString, "clientList.txt");
                        break;

                    case 2:
                        // input format - email, subject, content
                        // code to send an email
                        String input = scanner.nextLine();// get string input
                        emailHandler emailHandlerInstance = emailHandler.getInstance();
                        emailObject Email = emailHandlerInstance.createEmailObject(input);// create email object from
                                                                                          // string
                        emailHandlerInstance.sendAndSave(Email);// send and save email
                        SendEmailTLS.sendEmail(Email);
                        clientRecords.emailArray.add(Email);
                        break;

                    case 3:
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        // code to print recipients who have birthdays on the given date
                        String dateString = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                        LocalDate givenDate = LocalDate.parse(dateString, formatter);
                        List<Recipient> birthdayOnGivenDay = clientRecords.getRecipientsOnBirthday(givenDate);
                        if (birthdayOnGivenDay.isEmpty()) {
                            break;
                        }
                        for (Recipient recipient : birthdayOnGivenDay) {
                            System.out.println(recipient.getName());
                        }
                        break;

                    case 4:
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        // code to print the details of all the emails sent on the input date
                        String sentDateString = scanner.nextLine();
                        formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                        LocalDate sentDate = LocalDate.parse(sentDateString, formatter);
                        List<emailObject> emailsOnDate = clientRecords.getEmailsOnDay(sentDate);
                        for (emailObject obj : emailsOnDate) {
                            emailHandlerInstance = emailHandler.getInstance();
                            System.out.println(emailHandlerInstance.getEmailString(obj));
                        }
                        break;

                    case 5:
                        // code to print the number of recipient objects in the application
                        System.out.println(clientRecords.recipientCount);
                        break;

                    case 6:
                        clientRunning = false;
                        break;
                }

                if (clientRunning) {
                    System.out.println("Enter next option type:");
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
}