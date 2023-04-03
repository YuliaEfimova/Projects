package server.model;

import javax.persistence.*;

@Entity
@Table (name = "banks_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int transaction_id;
    public int accountid1;
    public int accountid2;
    public String personid1;
    public String personid2;
    public int sum;
    public  boolean status;


    public Transaction() {
    }

    public Transaction(int account_id1, int account_id2,int sum,String personid1,String personid2) {
        this.sum = sum;
        this.accountid1 = account_id1;
        this.accountid2 = account_id2;
        this.personid1=personid1;
        this.personid2=personid2;

    } // если я ввожу какие-то данные вручную, то нужен конструктор, который будет их принимать

    public int getAccount_id2() {
        return accountid2;
    }

    public void setAccount_id2(int account_id2) {
        this.accountid2 = account_id2;
    }

    public int getAccount_id1() {
        return accountid1;
    }

    public void setAccount_id1(int account_id1) {
        this.accountid1 = account_id1;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getPersonid1() {
        return personid1;
    }
    public String getPersonid2() {
        return personid2;
    }
    public void setPersonid1(String personid) {
        personid1=personid;

    }
    public void setPersonid2(String personid) {
        personid2=personid;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }


}
