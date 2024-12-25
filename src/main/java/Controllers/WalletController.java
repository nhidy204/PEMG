package Controllers;

import Models.Wallet;
import Services.Interfaces.IWalletService;
import Services.Interfaces.IValidateService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class WalletController {
    private final IWalletService walletService;
    private final IValidateService validateService;
    private final ArrayList<Wallet> wallets;
    private final Scanner scanner = new Scanner(System.in);

    public WalletController(IWalletService walletService, IValidateService validateService) {
        this.walletService = walletService;
        this.validateService = validateService;
        this.wallets = walletService.loadWallets();
    }

    public void showWalletMenu(String userId) {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Wallet");
            System.out.println("2. View Wallet");
            System.out.println("3. Edit Wallet");
            System.out.println("4. Delete Wallet");
            System.out.println("5. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    addWallet(userId);
                    break;
                case 2:
                    viewWallet(userId);
                    break;
                case 3:
                    editWallet(userId);
                    break;
                case 4:
                    deleteWallet(userId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addWallet(String userId) {
        Wallet existingWallet = walletService.getWalletByUserId(userId);
        if (existingWallet == null) {
            String name = validateService.inputString("Enter wallet name: ", null);
            double balance = validateService.inputDouble("Enter wallet balance: ");
            Wallet wallet = new Wallet(UUID.randomUUID().toString(), LocalDateTime.now().toString(), LocalDateTime.now().toString(), balance, userId, name);
            walletService.addWallet(wallet, wallets);
            saveWallets();
        } else {
            System.out.println("User already has a wallet.");
        }
    }

    private void viewWallet(String userId) {
        int id = 1;
        Wallet wallet = walletService.getWalletByUserId(userId);
        if (wallet != null) {
            System.out.printf("%-10s %-20s %-20s %-12s\n", "No.", "Name", "Balance", "Wallet ID");
            System.out.printf("%-10s %-20s %-20s %-12s\n", id, wallet.getName(), wallet.getBalance(), wallet.getId());
            id++;
        } else {
            System.out.println("No wallet found for this user.");
        }
    }

    private void editWallet(String userId) {
        Wallet wallet = walletService.getWalletByUserId(userId);
        if (wallet != null) {
            String name = validateService.inputString("Enter new wallet name: ", null);
            double balance = validateService.inputDouble("Enter new wallet balance: ");
            Wallet updatedWallet = new Wallet(wallet.getId(), LocalDateTime.now().toString(), LocalDateTime.now().toString(), balance, userId, name);
            walletService.editWallet(wallet.getId(), updatedWallet, wallets);
            saveWallets();
        } else {
            System.out.println("No wallet found for this user.");
        }
    }

    private void deleteWallet(String userId) {
        Wallet wallet = walletService.getWalletByUserId(userId);
        if (wallet != null) {
            walletService.deleteWallet(wallet.getId(), wallets);
            saveWallets();
        } else {
            System.out.println("No wallet found for this user.");
        }
    }

    private void saveWallets() {
        walletService.saveWallets(wallets);
    }
    public Wallet getWalletByUserId(String userId) {
        return walletService.getWalletByUserId(userId);
    }
}
