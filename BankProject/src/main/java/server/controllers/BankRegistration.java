package server.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import server.model.Bank;
import server.model.Role;
//import server.repositories.BankRepository;

import java.util.Collections;
import java.util.Map;

@Controller
public class BankRegistration {

//    @Autowired
//    private BankRepository bankRepository;

    @GetMapping("/bankRegistration")
    public String bankRegistration(){
        System.out.println("Зашел в GET");
        return "bankRegistration";
    }

    @PostMapping("/bankRegistration")
    public String addBank(Bank bank, Map<String, Object> model){
        //bank.setActive(true);
        System.out.println(1);
        System.out.println(bank.getBank_id());
        System.out.println(bank.getName());
        System.out.println(bank.getLimittransfer());
        System.out.println(bank.getLimittakeoff());
        System.out.println(bank.getPercent());
        System.out.println(bank.getBank_limit());
        System.out.println(bank.getComission());

//        bankRepository.save(bank);

        return "redirect:/main";
    }

}