package Services.Interfaces;

import Models.Wallet;
import java.util.ArrayList;

public interface IWalletService {
    void addWallet(Wallet wallet, ArrayList<Wallet> wallets);
    Wallet getWalletByUserId(String userId);
    void editWallet(String walletId, Wallet updatedWallet, ArrayList<Wallet> wallets);
    void deleteWallet(String walletId, ArrayList<Wallet> wallets);
    void saveWallets(ArrayList<Wallet> wallets);
    ArrayList<Wallet> loadWallets();
    Wallet getWalletById(String walletId);
}
