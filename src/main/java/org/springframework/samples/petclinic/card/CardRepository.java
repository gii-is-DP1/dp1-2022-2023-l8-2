package org.springframework.samples.petclinic.card;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
    
}
