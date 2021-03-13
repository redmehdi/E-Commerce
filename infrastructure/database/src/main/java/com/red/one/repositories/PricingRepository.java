package com.red.one.repositories;

import com.red.one.entities.PricingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PricingRepository extends CrudRepository<PricingEntity, Long> {

    @Query(value = "SELECT * FROM poke_species s inner join poke_character c ON s.id = c.species_id ORDER BY c.height DESC limit 5", nativeQuery = true)
    List<PricingEntity> findPrice();
}
