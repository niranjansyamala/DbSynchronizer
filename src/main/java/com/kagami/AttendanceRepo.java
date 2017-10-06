package com.kagami;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AttendanceRepo extends JpaRepository<TransactionEntity, Serializable> {


}
