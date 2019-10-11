package org.websparrow.repository;

import org.springframework.data.repository.CrudRepository;
import org.websparrow.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
