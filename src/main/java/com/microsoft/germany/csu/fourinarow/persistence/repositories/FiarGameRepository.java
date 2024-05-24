package com.microsoft.germany.csu.fourinarow.persistence.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.microsoft.germany.csu.fourinarow.persistence.entities.FiarGame;

public interface FiarGameRepository extends CrudRepository<FiarGame, UUID> {

    
}
