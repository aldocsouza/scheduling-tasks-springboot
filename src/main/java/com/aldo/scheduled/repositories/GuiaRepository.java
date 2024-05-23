package com.aldo.scheduled.repositories;

import com.aldo.scheduled.model.GuiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaRepository extends JpaRepository<GuiaModel, Long> {

    @Query("SELECT MAX(number) FROM GuiaModel")
    String findMaxNumber();
}
