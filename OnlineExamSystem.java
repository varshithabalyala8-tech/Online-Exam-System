import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExamSystem {

    static Scanner sc = new Scanner(System.in);
    static String username = "user1";
    static String password = "pass1";
    static String name = "Student";
    static boolean examSubmitted = false;

    public static void main(String[] args) {
        System.out.println("Welcome to Online Examination System");
        if (login()) {
            showMenu();
        } else {
            System.out.println("Invalid login. Exiting.");
        }
    }

    static boolean login() {
        System.out.print("Enter Login ID: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        return user.equals(username) && pass.equals(password);
    }

    static void showMenu() {
        while (true) {
            System.out.println("\n1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Take Exam");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    changePassword();
                    break;
                case 3:
                    takeExam();
                    break;
                case 4:
                    System.out.println("Logging out... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void updateProfile() {
        System.out.print("Enter new name: ");
        name = sc.nextLine();
        System.out.println("Profile updated successfully.");
    }

    static void changePassword() {
        System.out.print("Enter old password: ");
        String old = sc.nextLine();
        if (old.equals(password)) {
            System.out.print("Enter new password: ");
            password = sc.nextLine();
            System.out.println("Password updated.");
        } else {
            System.out.println("Incorrect password.");
        }
    }

    static void takeExam() {
        int score = 0;
        examSubmitted = false;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (!examSubmitted) {
                    System.out.println("\n⏰ Time is up! Auto-submitting your answers...");
                    examSubmitted = true;
                    sc.close(); // stop further input
                    System.exit(0); // or return to menu safely
                }
            }
        }, 60000); // 1 minute = 60000 ms

        System.out.println("\n📘 Exam Started! (You have 1 minute)");

        System.out.println("\nQ1. What is the capital of India?");
        System.out.println("a) Mumbai\nb) Delhi\nc) Kolkata\nd) Chennai");
        String ans1 = sc.nextLine();
        if (ans1.equalsIgnoreCase("b")) score++;

        System.out.println("\nQ2. Which language is used for Android?");
        System.out.println("a) Java\nb) Python\nc) Kotlin\nd) Swift");
        String ans2 = sc.nextLine();
        if (ans2.equalsIgnoreCase("c")) score++;

        System.out.println("\nQ3. What does OOP stand for?");
        System.out.println("a) Object Oriented Programming\nb) Open Over Protocol\nc) Only On Point\nd) None");
        String ans3 = sc.nextLine();
        if (ans3.equalsIgnoreCase("a")) score++;

        examSubmitted = true;
        timer.cancel(); // stop the timer

        System.out.println("\n✅ Exam Submitted!");
        System.out.println("Score: " + score + "/3");
    }
}