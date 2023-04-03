//package server.model;
//
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import server.model.BankAccount;
//import server.repositories.BankAccountRepository;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//public class BankAccountRepositoryImpl implements BankAccountRepository {
//
////    public void changeBalanceForDate(int account_id, int balance, String type, int countDay) {
////        if ((Objects.equals(type, "deposit") && ((countDay%30)) == 0) || (Objects.equals(type, "debit"))){
////            int idBank = findBankIdByAccountId(account_id);
////            float percent = findPercentByBankId(idBank);
////            BankAccount bankAccount = new BankAccount(idBank, balance, type);
////            int newBalance = balance + Math. round (countDay/365 * percent);
////            bankAccount.setBalance(newBalance);
////        } else {
////            System.out.println("Делаем ничего");
////        }
////
////    }
//
//    @Override
//    public float findPercentByBankId(int bank_id) {
//        return 0;
//    }
//
////    @Override
////    public int findBankIdByAccountId(int account_id) {
////        return 0;
////    }
//
//    @Override
//    public  List<BankAccount> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<BankAccount> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<BankAccount> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<BankAccount> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(BankAccount entity) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends BankAccount> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public <S extends BankAccount> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends BankAccount> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<BankAccount> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends BankAccount> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public void deleteInBatch(Iterable<BankAccount> entities) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public BankAccount getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public <S extends BankAccount> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends BankAccount> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends BankAccount> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends BankAccount> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends BankAccount> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends BankAccount> boolean exists(Example<S> example) {
//        return false;
//    }
//}