package org.springframework.samples.sieteislas.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends CrudRepository<CardType, Integer> {

    @Query("SELECT c FROM CardType c WHERE c.name LIKE :name")
    CardType getTypeByName(@Param("name") String name);
}
