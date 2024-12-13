package Services;

import Models.FinancialGoal;
import Models.User;
import Services.Interfaces.IFinancialGoalService;

public class FinancialGoalService implements IFinancialGoalService {
    @Override
    public void checkAndNotifyGoals(User user) {

    }

//    @Override
//    public void checkAndNotifyGoals(User user) {
//        if (user.getWallet() == null) {
//            System.err.println("Error: Wallet is null for user " + user.getUsername());
//            return;
//        }
//
//        double walletBalance = user.getWallet().getBalance();
//
//        for (FinancialGoal goal : user.getFinancialGoals()) {
//            if (walletBalance >= goal.getTargetAmount()) {
//                System.out.println("Congratulation! You reach the Financial Goal " + goal.getName());
//            } else {
//                double progress = (walletBalance / goal.getTargetAmount()) * 100;
//                System.out.printf("Target Progress '%s': %.2f%%\n", goal.getName(), progress);
//            }
//        }
//    }


}
