package com.natwest.hiring.repository;

import com.natwest.hiring.entity.TransactionObjEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionObjEntity, Integer> {
}
