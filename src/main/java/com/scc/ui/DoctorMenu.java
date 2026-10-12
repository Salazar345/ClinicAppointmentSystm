package com.scc.ui;

import com.scc.model.User;

/** DOCTOR MENU (menu only for now - CRUD will be added later). */
public class DoctorMenu {

    public void show(User user) {
        while (true) {
            System.out.println("\n=========== DOCTOR MENU ===========");
            System.out.println("Logged in as: " + user.username() + " (" + user.role() + ")");
            System.out.println("  1. View My Appointment Schedule");
            System.out.println("  2. View Assigned Patient Details");
            System.out.println("  3. Update Appointment Status");
            System.out.println("  4. Logout");

            int choice = InputHelper.readChoice("Choose (1-4): ", 1, 4);
            switch (choice) {
                case 1 -> comingSoon("View My Appointment Schedule");
                case 2 -> comingSoon("View Assigned Patient Details");
                case 3 -> comingSoon("Update Appointment Status");
                case 4 -> {
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
