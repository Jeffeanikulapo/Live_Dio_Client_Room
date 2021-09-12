package com.digital.crud.saladereniao.saladereuniao.repository;

import com.digital.crud.saladereniao.saladereuniao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RooRepository extends JpaRepository<Room,Long> {

}
