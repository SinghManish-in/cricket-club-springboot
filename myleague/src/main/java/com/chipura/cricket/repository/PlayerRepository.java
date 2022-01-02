package com.chipura.cricket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chipura.cricket.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
