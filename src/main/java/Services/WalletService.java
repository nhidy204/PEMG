package Services;

import Constants.FileConstants;
import Models.Wallet;
import Services.Interfaces.IWalletService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WalletService implements IWalletService {
    private static WalletService instance;
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.WALLET_FILE_NAME;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private WalletService() {}

    public static WalletService getInstance() {
        if (instance == null) {
            instance = new WalletService();
        }
        return instance;
    }

    @Override
    public void addWallet(Wallet wallet, ArrayList<Wallet> wallets) {
        wallets.add(wallet);
        saveWallets(wallets);
        System.out.println("Wallet added successfully.");
    }

    @Override
    public ArrayList<Wallet> listWallets(ArrayList<Wallet> wallets) {
        return wallets;
    }

    @Override
    public void editWallet(String walletId, Wallet updatedWallet, ArrayList<Wallet> wallets) {
        for (Wallet wallet : wallets) {
            if (wallet.getId().equals(walletId)) {
                wallet.setBalance(updatedWallet.getBalance());
                wallet.setUpdatedAt(updatedWallet.getUpdatedAt());
                wallet.setName(updatedWallet.getName());
                saveWallets(wallets);
                System.out.println("Wallet updated successfully.");
                return;
            }
        }
        System.out.println("Wallet not found.");
    }

    @Override
    public void deleteWallet(String walletId, ArrayList<Wallet> wallets) {
        wallets.removeIf(wallet -> wallet.getId().equals(walletId));
        saveWallets(wallets);
        System.out.println("Wallet deleted successfully.");
    }

    @Override
    public void saveWallets(ArrayList<Wallet> wallets) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(wallets, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Wallet> loadWallets() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Wallet[] walletsArray = gson.fromJson(reader, Wallet[].class);
            return new ArrayList<>(Arrays.asList(walletsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Wallet file not found. Creating a new one.");
            return new ArrayList<>();
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Wallet getWalletById(String walletId) {
        ArrayList<Wallet> wallets = loadWallets();
        for (Wallet wallet : wallets) {
            if (wallet.getId().equals(walletId)) {
                return wallet;
            }
        }
        return null;
    }
}
