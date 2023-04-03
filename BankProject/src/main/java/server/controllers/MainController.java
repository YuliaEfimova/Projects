package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.event.AuthenticationFailureProxyUntrustedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import server.model.Bank;
//import server.model.BankAccount;
import server.model.Bank;
import server.model.BankAccount;

//import server.model.User;
import server.model.Transaction;
import server.repositories.BankAccountRepository;
import server.repositories.TransactionRepository;
import server.repositories.UserRepository;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class  MainController {
//    @Autowired
//    private CurrentAccountRepository currentAccountRepository;


    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }

    @GetMapping("/userInfo")
    public String info(Map<String, Object> model){
        return "userInfo";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        System.out.println(userRepository.findByUsername(auth.getName()).getRoles());

        Iterable<BankAccount> bankAccounts =  bankAccountRepository.findByClientid(auth.getName());
        System.out.println(bankAccountRepository.findByBankid(1));


        model.put("bankAccounts",bankAccounts );



        return "main";
    }

    @PostMapping("/main")
    public String replenishment(
            @RequestParam (name="account_id", required=false, defaultValue="0") int account_id1,
            @RequestParam (name="sum", required=false, defaultValue="0") int sum,
            Map<String, Object> model )
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(bankAccountRepository.findByAccountid(account_id1).get(0).getClient_id());
//        System.out.println(auth.getName());
        if (bankAccountRepository.findByAccountid(account_id1).get(0).getClient_id().equals(auth.getName())){
            bankAccountRepository.findByAccountid(account_id1).get(0).balance +=sum;
        }

        Iterable<BankAccount> bankAccounts =  bankAccountRepository.findByClientid(auth.getName());
        model.put("bankAccounts", bankAccounts); // передаю эту модель
        return "main";
    }


//    @GetMapping("/changeDate")
//    public String changeDate(Map<String, Object> model) {
//        System.out.println("Зашел в GET");
//        return "changeDate";
//    }

//    @PostMapping("/changeDate")
//    public String changeDate(@RequestParam (name="changeDate", required=false, defaultValue="0") int changeDate) {
//        //Как будет создаваться ЦБ? Должен получать из формы changeDate
//        Bank centralBank = new Bank(); //создаю центральный банк
////        centralBank.changeDate(changeDate);
//        return "main";
//    }



//   @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String name, @RequestParam String sername,
//                      @RequestParam Integer currentAccount, Map<String, Object> model) {
//        CurrentAccount currentAccountss = new CurrentAccount(name, sername, currentAccount, user);
//
//        currentAccountRepository.save(currentAccountss);
//        Iterable<CurrentAccount> currentAccountes = currentAccountRepository.findAll();
//
//        model.put("currentAccounts", currentAccountes);
//
//        return "main";
//    }

    @GetMapping("/bankAccount")
    @PreAuthorize("hasAuthority('USER')")
    public String bankaccount(Map<String, Object> model) {

        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAll();

        model.put("bankAccounts",bankAccounts );
        return "bankAccount";
    }
    @PostMapping("/bankAccount")
    @PreAuthorize("hasAuthority('USER')")
    public String addbankAccount(
            @RequestParam (name="bank_id", required=false, defaultValue="0") int bank_id,
            @RequestParam (name="balance", required=false, defaultValue="0") int balance,
            @RequestParam (name="type", required=false, defaultValue="0") String type,
            Map<String, Object> model ) //описываю поле которое передаётся на страничке, прописываю что оно не обязательно для заполнения и что дефолтное значение 0
    {
        BankAccount bankAccount = new BankAccount(bank_id,balance,type); //создаю новую транзакцию с параметром суммы
        Date date=new Date();
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        bankAccount.clientid=auth.getName();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        bankAccount.date=(date);
        bankAccount.status=true;
        bankAccountRepository.save(bankAccount); // сохраняю новую транзакцию

        Iterable<BankAccount> bankAccounts = bankAccountRepository.findByClientid(auth.getName());

        model.put("bankAccounts",bankAccounts );
        return "bankAccount";
    }

}