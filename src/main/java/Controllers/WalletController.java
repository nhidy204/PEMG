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

    public void showWalletMenu() {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Wallet");
            System.out.println("2. List Wallets");
            System.out.println("3. Edit Wallet");
            System.out.println("4. Delete Wallet");
            System.out.println("5. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    addWallet();
                    break;
                case 2:
                    listWallets();
                    break;
                case 3:
                    editWallet();
                    break;
                case 4:
                    deleteWallet();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addWallet() {
        String userId = validateService.inputString("Enter user ID: ", null);
        String name = validateService.inputString("Enter wallet name: ", null);
        double balance = validateService.inputDouble("Enter wallet balance: ");

        Wallet wallet = new Wallet(UUID.randomUUID().toString(), LocalDateTime.now().toString(), LocalDateTime.now().toString(), balance, userId, name);
        walletService.addWallet(wallet, wallets);
        saveWallets();
    }

    private void listWallets() {
        ArrayList<Wallet> walletList = walletService.listWallets(wallets);
        for (Wallet wallet : walletList) {
            System.out.println("Wallet Name: " + wallet.getName() + ", Balance: " + wallet.getBalance() + ", ID: " + wallet.getId());
        }
    }

    private void editWallet() {
        String id = validateService.inputString("Enter wallet ID to edit: ", null);
        String name = validateService.inputString("Enter new wallet name: ", null);
        double balance = validateService.inputDouble("Enter new wallet balance: ");

        Wallet updatedWallet = new Wallet(id, LocalDateTime.now().toString(), LocalDateTime.now().toString(), balance, null, name);
        walletService.editWallet(id, updatedWallet, wallets);
        saveWallets();
    }

    private void deleteWallet() {
        String id = validateService.inputString("Enter wallet ID to delete: ", null);
        walletService.deleteWallet(id, wallets);
        saveWallets();
    }

    private void saveWallets() {
        walletService.saveWallets(wallets);
    }
}
