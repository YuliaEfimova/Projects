package server.model;

import server.repositories.BankAccountRepository;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "Banks")
public class Bank  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bankid")
    private int bankid;
    private String name;
    private int limittransfer;
    private int limittakeoff;
    private  int percent;
    private  int banklimit;
    private int comission;


    public Bank() {
    }

    public Bank(int bank_limite, String name , int limittransfer, int limittakeoff, int percent, int comission ) {
        this.banklimit = bank_limite;
        this.name=name;
        this.limittransfer =limittransfer;
        this.limittakeoff =limittakeoff;
        this.percent=percent;
        this.comission=comission;
    }

    public int getBank_limit() {
        return banklimit;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public int getLimittakeoff() {
        return limittakeoff;
    }

    public void setLimittakeoff(int limittakeoff) {
        this.limittakeoff = limittakeoff;
    }

    public int getLimittransfer() {
        return limittransfer;
    }

    public void setLimittransfer(int limittransfer) {
        this.limittransfer = limittransfer;
    }

    public float getComission() {
        return comission;
    }

    public void setComission(int comission) {
        this.comission = comission;
    }

    public void setBank_limit(int bank_limit) {
        this.banklimit = bank_limit;
    }

    public int getBank_id() {
        return bankid;
    }

    public void setBank_id(int bank_id) {
        this.bankid = bank_id;
    }

    public void setName(String name) {
        this.name = name;
    }



//    public void changeDate(int countDay) {
//
//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAll();
//        for (int i = 0; i < bankAccounts.size(); i++) {
//            BankAccount account = bankAccounts.get(i);
//            int account_id = account.getAccount_id();
//            int balance = account.getBalance();
//            String actype = account.getActype();
//            BankAccountRepositoryImpl bari = new BankAccountRepositoryImpl();
//            bari.changeBalanceForDate(account_id, balance, actype, countDay);
//            System.out.println(bankAccounts.get(i));
//        }
//        System.out.println("Смена даты");
//    }

}


