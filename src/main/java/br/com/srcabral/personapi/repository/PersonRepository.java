package br.com.srcabral.personapi.repository;

import br.com.srcabral.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
