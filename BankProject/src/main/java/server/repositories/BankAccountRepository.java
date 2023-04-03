package server.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import server.model.BankAccount;
import server.model.Transaction;

import java.util.List;


public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
////        List<BankAccount> findByAccount_id(@Param("account_id") int account_id);
//        List<BankAccount> findByClient_id(String  client_id);
//


  // List<BankAccount> findAll() {
//        return null;
//    }
  List<BankAccount> findByBankid(int  bank_id);
    List<BankAccount> findByClientid(String  clientid);
  List<BankAccount> findByAccountid(int  accountid);

 //  @Query("select percent from banks where banks.bank_id = :bank_id")
   //float findPercentByBankId(@Param("bank_id") int bank_id);

//    @Query("select bank_id from Banks_accounts where Banks_accounts.account_id = :account_id")
//    int findBankIdByAccountId(@Param("account_id") int account_id);
}