package bankingApplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private String Name;

    public Bank() {
    }

    public Bank(String Name) {
        this.Name = Name;
    }

    public void listAccounts() {
        Connection con = BankingConnection.connect();
        Statement statement;
        try {
            statement = con.createStatement();
            String sql = "Select * from account";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                System.out.println(results.getString(1) + " " + results.getString(2) + " "
                        + results.getString(3));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void openAccount(Account account) {
        Connection con = BankingConnection.connect();
        String sql = "Insert into account(accNumber,accName,accBalance) values(?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeAccount(int number) {
        Connection con = BankingConnection.connect();
        String sql = "Delete from account where accNumber = ? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void depositMoney(int number, double amount) {
        Account account = getAccount(number);
        account.deposit(amount);
        Connection con = BankingConnection.connect();
        String sql = "Update account Set accBalance = ? Where accNumber = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
            System.out.println("Balance: " + account.getBalance());
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void withdrawMoney(int number, double amount) {
        Account account = getAccount(number);
        account.withdraw(amount);
        Connection con = BankingConnection.connect();
        System.out.println("Balance: " + account.getBalance());
    }

    public Account getAccount(int number) {
        Connection con = BankingConnection.connect();
        String sql = "Select * from account where accNumber = ?";
        PreparedStatement preparedStatement;
        Account account = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            String accName = result.getString(2);
            double accBalance = result.getDouble(3);
            account = new Account(number, accName, accBalance);

            while (result.next()) {
                System.out.println(result.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

        return account;
    }

}
