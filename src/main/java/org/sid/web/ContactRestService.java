package org.sid.web;

import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContactRestService {
    @Autowired
    private ContactRepository contactRepository;
    @RequestMapping(value = "/contacts", method= RequestMethod.GET)
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @RequestMapping(value = "/contacts", method= RequestMethod.POST)
    public Contact save(@RequestBody Contact c){
        return contactRepository.save(c);
    }

    /*@RequestMapping(value = "/contacts/{id}", method= RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){
        contactRepository.delete(id);
        return true;
    }

    @RequestMapping(value = "/contacts/{id}", method= RequestMethod.DELETE)
    public Contact getContact(@PathVariable Long id){
        return contactRepository.findOne(id);
    }*/

    @RequestMapping(value = "/contacts", method= RequestMethod.PUT)
    public Contact save(@PathVariable Long id, @RequestBody Contact c){
        c.setId(id);
        return contactRepository.save(c);
    }

    @RequestMapping(value = "/chercherContacts", method= RequestMethod.GET)
    public Page<Contact> chercher(
            @RequestParam(name = "mc", defaultValue = "") String mc,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size ) {
            return contactRepository.chercher("%"+mc+"%", new PageRequest(page, size));
    }
}
