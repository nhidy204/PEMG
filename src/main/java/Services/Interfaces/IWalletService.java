package Services.Interfaces;

import Models.Wallet;
import java.util.ArrayList;

public interface IWalletService {
    void addWallet(Wallet wallet, ArrayList<Wallet> wallets);
    ArrayList<Wallet> listWallets(ArrayList<Wallet> wallets);
    void editWallet(String walletId, Wallet updatedWallet, ArrayList<Wallet> wallets);
    void deleteWallet(String walletId, ArrayList<Wallet> wallets);
    ArrayList<Wallet> loadWallets();

    void saveWallets(ArrayList<Wallet> wallets);

    Wallet getWalletById(String walletId);
}
