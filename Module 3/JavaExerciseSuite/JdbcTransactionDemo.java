import java.sql.*;

public class JdbcTransactionDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/bank?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "ragul0165"; // Replace with your MySQL root password

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Disable auto-commit to start a transaction
            conn.setAutoCommit(false);
            
            // Print initial balances
            printBalances(conn);
            
            // Transfer 100 from account 1 to account 2
            boolean success = transferMoney(conn, 1, 2, 100.00);
            
            if (success) {
                // If everything is OK, commit the transaction
                conn.commit();
                System.out.println("\nTransaction committed successfully!");
            } else {
                // If there was an error, roll back the transaction
                conn.rollback();
                System.out.println("\nTransaction failed and rolled back!");
            }
            
            // Print final balances
            System.out.println("\nFinal Balances:");
            printBalances(conn);
            
        } catch (Exception e) {
            // If there's any exception, roll back the transaction
            try {
                if (conn != null) {
                    conn.rollback();
                }
                System.out.println("Transaction rolled back due to: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Reset auto-commit to true
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static boolean transferMoney(Connection conn, int fromAccount, int toAccount, double amount) 
            throws SQLException {
        
        // First check if the from account has sufficient balance
        String checkBalanceSQL = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkBalanceSQL)) {
            pstmt.setInt(1, fromAccount);
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("Account " + fromAccount + " not found!");
                return false;
            }
            
            double currentBalance = rs.getDouble("balance");
            if (currentBalance < amount) {
                System.out.println("Insufficient funds in account " + fromAccount);
                return false;
            }
        }
        
        // Deduct amount from the source account
        String deductSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deductSQL)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, fromAccount);
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated == 0) {
                System.out.println("Failed to update account " + fromAccount);
                return false;
            }
            System.out.println("Deducted $" + amount + " from account " + fromAccount);
        }
        
        // Add amount to the target account
        String addSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(addSQL)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, toAccount);
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated == 0) {
                System.out.println("Failed to update account " + toAccount);
                return false;
            }
            System.out.println("Added $" + amount + " to account " + toAccount);
        }
        
        return true;
    }
    
    private static void printBalances(Connection conn) throws SQLException {
        String sql = "SELECT id, balance FROM accounts ORDER BY id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nCurrent Account Balances:");
            System.out.println("Account ID\tBalance");
            System.out.println("----------\t-------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                double balance = rs.getDouble("balance");
                System.out.printf("%-10d\t$%.2f%n", id, balance);
            }
        }
    }
}
