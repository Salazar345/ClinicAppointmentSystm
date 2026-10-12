package com.scc.ui;

import com.scc.model.User;

/** RECEPTIONIST MENU (menu only for now - CRUD will be added later). */
public class ReceptionistMenu {

    public void show(User user) {
        while (true) {
            System.out.println("\n=========== RECEPTIONIST MENU ===========");
            System.out.println("Logged in as: " + user.username() + " (" + user.role() + ")");
            System.out.println("  1. Register / Edit Patient");
            System.out.println("  2. Search Patients");
            System.out.println("  3. Book Appointment");
            System.out.println("  4. Reschedule Appointment");
            System.out.println("  5. Cancel Appointment");
            System.out.println("  6. View Daily Schedule");
            System.out.println("  7. Logout");

            int choice = InputHelper.readChoice("Choose (1-7): ", 1, 7);
            switch (choice) {
                case 1 -> comingSoon("Register / Edit Patient");
                case 2 -> comingSoon("Search Patients");
                case 3 -> comingSoon("Book Appointment");
                case 4 -> comingSoon("Reschedule Appointment");
                case 5 -> comingSoon("Cancel Appointment");
                case 6 -> comingSoon("View Daily Schedule");
                case 7 -> {
                    if (InputHelper.confirm("Are you sure you want to logout?")) {
                        System.out.println("Goodbye, " + user.username() + "! You are logged out.");
                        return;   // back to the main menu
                    }
                }
            }
        }
    }

    private void comingSoon(String feature) {
        System.out.println("\n[" + feature + "] - coming soon.");
        InputHelper.pause();
    }
}
