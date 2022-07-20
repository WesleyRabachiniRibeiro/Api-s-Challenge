package com.savelife.user.services


import com.savelife.user.entities.Phone;
import com.savelife.user.repositories.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class PhoneService {

    private final PhoneRepository repository;

    private PhoneService(PhoneRepository repository){
        this.repository = repository;
    }

    public Phone savePhone(Phone phone){
        return repository.save(phone);
    }

    public Phone findPhone(@PathVariable Long id){
        Optional<Phone> phone = repository.findById(id);
        return phone.orElseThrow(() -> new EntityNotFoundException("Phone Not Found!"));
    }

    public Page<Phone> listAllPhones(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Phone updatePhone(Phone updatedPhone, Long id){
        Phone phone = this.findPhone(id);
        updatedPhone.setId(phone.getId());
        return repository.save(updatedPhone);
    }

    public void deletePhone(@PathVariable Long id){
        Phone phone = this.findPhone(id);
        repository.delete(phone);
    }

}
