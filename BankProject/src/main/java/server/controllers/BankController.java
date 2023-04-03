//package server.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import server.model.Bank;
//import server.model.Role;
//import server.repositories.BankRepository;
//
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/bank")
//
//public class BankController {
//
//    @Autowired
//    BankRepository bankRepository;
//
//        @GetMapping
//        public String bankList(Map<String, Object> model){
//            model.put("banks", bankRepository.findAll());
//            return "bankList";
//        }
//
//
//        @PostMapping
//        public String bankSave(
//                @RequestParam(required = false) String nameBank,
//                @RequestParam Map<String, String> form,
//                @RequestParam(value = "idBank", required = false) Bank bank){
//            bank.setNameBank(nameBank);
//
//            Set<String> roles = Arrays.stream(Role.values())
//                    .map(Role::name)
//                    .collect(Collectors.toSet());
//
//            bank.getRoles().clear();
//
//            for (String key : form.keySet()){
//                if (roles.contains(key)) {
//                    bank.getRoles().add(Role.valueOf(key));
//                }
//            }
//
//            bankRepository.save(bank);
//
//            return "redirect:/bank";
//        }
//
//}
