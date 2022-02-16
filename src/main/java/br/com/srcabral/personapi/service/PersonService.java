package br.com.srcabral.personapi.service;

import br.com.srcabral.personapi.dto.request.PersonDTO;
import br.com.srcabral.personapi.dto.response.MessageResponseDTO;
import br.com.srcabral.personapi.entity.Person;
import br.com.srcabral.personapi.exception.PersonNotFoundException;
import br.com.srcabral.personapi.mapper.PersonMapper;
import br.com.srcabral.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listall() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyExistId(id);
        //Optional<Person> optionalPerson = personRepository.findById(id);
        //if(optionalPerson.isEmpty()){
          //  throw new PersonNotFoundException(id);
        //}
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws  PersonNotFoundException{
        verifyExistId(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateByid(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyExistId(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID ");
    }

    private Person verifyExistId(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
