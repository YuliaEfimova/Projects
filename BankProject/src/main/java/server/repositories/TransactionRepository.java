package server.repositories;


import org.springframework.data.repository.CrudRepository;
import server.model.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    List<Transaction> findByStatus( boolean status);
    List<Transaction> findByPersonid1( String personid1);
    List<Transaction> findByPersonid2( String personid2);

}