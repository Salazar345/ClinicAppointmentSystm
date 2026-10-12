package com.scc.ui;

import com.scc.model.User;

/** ADMIN MENU (menu only for now - CRUD will be added later). */
public class AdminMenu {

    public void show(User user) {
        while (true) {
            System.out.println("\n=========== ADMIN MENU ===========");
            System.out.println("Logged in as: " + user.username() + " (" + user.role() + ")");
            System.out.println("  1. Manage User Accounts (approve / deactivate)");
            System.out.println("  2. Manage Doctor Records");
            System.out.println("  3. View All Patients and Appointments");
            System.out.println("  4. View Reports");
            System.out.println("  5. View Activity Logs");
            System.out.println("  6. Logout");

            int choice = InputHelper.readChoice("Choose (1-6): ", 1, 6);
            switch (choice) {
                case 1 -> comingSoon("Manage User Accounts (approve / deactivate)");
                case 2 -> comingSoon("Manage Doctor Records");
                case 3 -> comingSoon("View All Patients and Appointments");
                case 4 -> comingSoon("View Reports");
                case 5 -> comingSoon("View Activity Logs");
                case 6 -> {
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
