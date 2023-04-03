package server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Banks_accounts")
public class BankAccount {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "account_id")
        public int accountid;
        public int bankid;
        public  String clientid;
        public   int balance;
        public   String actype;
        public Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public   boolean status;

    public BankAccount() {
    }

    public BankAccount(int bank_id,int balance, String actype) {
        this.bankid = bank_id;
        this.balance= balance;
        this.actype = actype;
    }

    public String getActype() {
        return actype;
        }

        public void setActype(String actype) {
         this.actype = actype;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
           this.status = status;
        }

        public String getClient_id() {
           return clientid;
        }

    public int isBankid() {
        return bankid;
    }

    public void setClient_id(String client_id) {
        this.clientid = client_id;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bank_id) {
        this.bankid = bank_id;
    }

    public int getAccount_id() {
        return accountid;
    }

    public void setAccount_id(int account_id) {
        this.accountid = account_id;
    }

    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    public int getBalance() {
        return balance;
    }

     public void setBalance(int balance) {
        this.balance = balance;
    }
}
