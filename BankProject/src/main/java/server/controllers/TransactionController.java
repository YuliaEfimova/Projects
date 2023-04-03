package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.model.Transaction;
import server.repositories.BankAccountRepository;
import server.repositories.TransactionRepository;

import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/transaction")
    public String transaction(Map<String, Object> model) {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Iterable<Transaction> transactions1=transactionRepository.findByPersonid1(auth.getName());
        Iterable<Transaction> transactions2=transactionRepository.findByPersonid2(auth.getName());

        model.put("transactions1",transactions1 );
        model.put("transactions2",transactions2 );// передаю эту модель
        return "transaction";
    }

    @PostMapping("/transaction")
    public String addtransaction(
            @RequestParam (name="sum", required=false, defaultValue="0") int sum,
            @RequestParam (name="account_id1", required=false, defaultValue="0") int account_id1,
            @RequestParam (name="account_id2", required=false, defaultValue="0") int account_id2,
            Map<String, Object> model ) //описываю поле которое передаётся на стреничке, прописываю что оно не обязательно для заполнения и что дефолтное значение 0
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        try{
        if(bankAccountRepository.findByAccountid(account_id1).get(0).getClient_id().equals(auth.getName())){
        bankAccountRepository.findByAccountid(account_id1).get(0).balance-=sum;
        bankAccountRepository.findByAccountid(account_id2).get(0).balance+=sum;
        Transaction transaction = new Transaction(account_id1,account_id2,sum,bankAccountRepository.findByAccountid(account_id1).get(0).getClient_id(),bankAccountRepository.findByAccountid(account_id2).get(0).getClient_id()); //создаю новую транзакцию с параметром суммы
            transaction.status=true;
            transactionRepository.save(transaction);
        }}
        catch (Exception exception){
            Iterable<Transaction> transactions1=transactionRepository.findByPersonid1(auth.getName());
            Iterable<Transaction> transactions2=transactionRepository.findByPersonid2(auth.getName());

            model.put("transactions1",transactions1 );
            model.put("transactions2",transactions2 );// передаю эту модель

            return "transaction";

        }

        Iterable<Transaction> transactions1=transactionRepository.findByPersonid1(auth.getName());
        Iterable<Transaction> transactions2=transactionRepository.findByPersonid2(auth.getName());

        model.put("transactions1",transactions1 );
        model.put("transactions2",transactions2 );// передаю эту модель


        return "transaction";
    }

    @GetMapping("/filter")
    public String filter(Map<String, Object> model) {
        Iterable<Transaction> transactions = transactionRepository.findAll(); //создаю модельку всех транзакций
        model.put("transactions",transactions );
        return "filter";
    }
    @PostMapping ("/filter")
    public String filter(
            @RequestParam  boolean status,
            Map<String, Object> model )
    {
        List<Transaction> transactions = transactionRepository.findByStatus(status);
        model.put("transactions",transactions );
        return "filter";
    }
}
