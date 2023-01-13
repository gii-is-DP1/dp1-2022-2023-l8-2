package org.springframework.samples.sieteislas.card;


import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {
	
}
