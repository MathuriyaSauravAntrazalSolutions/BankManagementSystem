package BankManagementSystem.src.Constatnts;

/*
*********************************************************************************************************
 *  @Java Class Name :   DatabaseQueries
 *  @Author          :   <Saurav Mathuriya>(saurav.mathuriya@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   10-03-2024
 *  @Description     :   This class contains all the queries which is used to interact to database.
 * 
 ********************************************************************************************************
*/
public class DatabaseQueries {

    // Create App
    public static final String CREATE_APP_DB_QUERY = "CREATE DATABASE AppDB";
    public static final String USE_APP_DB_QUERY = "Use AppDB";
    public static final String CREATE_USERS_TABLE_QUERY = "CREATE TABLE AppDB.Users("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "userId INT NOT NULL,"
    + "username VARCHAR(255) NOT NULL,"
    + "password VARCHAR(255) NOT NULL,"
    + "emailId VARCHAR(255) NOT NULL,"
    + "phone BIGINT NOT NULL,"
    + "firstName VARCHAR(255) NOT NULL,"
    + "lastName VARCHAR(255)"
    +")";
    public static final String CREATE_BANKS_TABLE_QUERY = "CREATE TABLE AppDB.Banks("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "bankId Int NOT NULL,"
    + "name VARCHAR(50) NOT NULL,"
    + "ownerId int NOT NULL,"
    + "address VARCHAR(50) NOT NULL"
    +")";
    public static final String CREATE_USERS_BANKS_INFO_TABLE_QUERY = "CREATE TABLE AppDB.UsersBanksInfo("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "userId Int NOT NULL,"
    + "custId Int NOT NULL,"
    + "bankId Int NOT NULL,"
    + "branch_Code Int NOT NULL,"
    + "accountNumber BIGINT NOT NULL,"
    + "isActive Varchar(255) DEFAULT 'ACTIVE'"
    +")";
    public static final String CREATE_CUSTOMERS_TABLE_QUERY = "CREATE TABLE IF NOT Exists AppDB.customers("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "appUserId INT NOT NULL,"
    + "custId INT NOT NULL,"
    + "bankId INT NOT NULL,"
    + "firstName VARCHAR(255) NOT NULL,"
    + "lastName VARCHAR(255),"
    + "panNumber BIGINT NOT NULL,"
    + "adharNumber BIGINT NOT NULL,"
    + "phone BIGINT NOT NULL,"
    + "gmail VARCHAR(255),"
    + "isActive Varchar(255) DEFAULT 'ACTIVE'"
    +")";
    public static final String CREATE_ACCOUNTS_TABLE_QUERY = "CREATE TABLE IF NOT Exists AppDB.accounts("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "custId INT NOT NULL,"
    + "bankId INT NOT NULL,"
    + "accountNumber BIGINT NOT NULL,"
    + "branch_code INT NOT NULL,"
    + "balance BIGINT NOT NULL,"
    + "type VARCHAR(255)  NOT NULL,"
    + "securityPin INT NOT NULL,"
    + "isActive Varchar(255) DEFAULT 'ACTIVE'"
    +")";
    public static final String CREATE_BRANCHES_TABLE_QUERY = "CREATE TABLE IF NOT Exists AppDB.branches("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "branch_code INT NOT NULL,"
    + "bankId INT NOT NULL,"
    + "name VARCHAR(255) NOT NULL,"
    + "balance BIGINT NOT NULL,"
    + "address VARCHAR(255)"
    +")";
    public static final String CREATE_TRANSACTIONS_TABLE_QUERY = "CREATE TABLE IF NOT Exists AppDB.transactions("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "bankId INT NOT NULL,"
    + "accountNumber BIGINT NOT NULL,"
    + "amount INT NOT NULL,"
    + "type VARCHAR(255),"
    + "date VARCHAR(255)"
    +")";
    public static final String CREATE_LOANS_TABLE_QUERY = "CREATE TABLE IF NOT Exists AppDB.loans("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "custId INT NOT NULL,"
    + "bankId INT NOT NULL,"
    + "accountNumber BIGINT NOT NULL,"
    + "loanAmount INT NOT NULL,"
    + "remainingAmount INT NOT NULL,"
    + "creditScore INT NOT NULL,"
    + "date VARCHAR(255),"
    + "isActive Varchar(255) DEFAULT 'ACTIVE'"
    +")";
    public static final String CREATE_JOINT_ACCOUNTS_TABLE_QUERY = "CREATE TABLE IF NOT Exists AppDB.jointAccounts("
    + "id INT AUTO_INCREMENT PRIMARY KEY,"
    + "bankId INT NOT NULL,"
    + "custId_one INT NOT NULL,"
    + "custId_two INT NOT NULL,"
    + "custId_two_name varchar(255) NOT NULL,"
    + "accountNumber BIGINT NOT NULL,"
    + "balance BIGINT NOT NULL,"
    + "Cust2SecurityPin INT NOT NULL,"
    + "isActive Varchar(255) DEFAULT 'ACTIVE'"
    +")";
}
