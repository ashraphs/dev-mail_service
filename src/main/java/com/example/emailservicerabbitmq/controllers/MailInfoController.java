package com.example.emailservicerabbitmq.controllers;


import com.example.emailservicerabbitmq.entities.MailInformation;
import com.example.emailservicerabbitmq.repositories.MailInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController(value = "/mailinformation")
public class MailInfoController {

    @Autowired
    private MailInformationRepository mailInformationRepository;

    @GetMapping
    public List<MailInformation> findAll() {
        return mailInformationRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MailInformation> findById(@PathVariable final String id) {

        Optional<MailInformation> mailInformation = mailInformationRepository.findById(id);

        if (mailInformation.isPresent()) {
            return new ResponseEntity<>(mailInformation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody MailInformation mailInformation) {

        if (mailInformation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MailInformation mailInfo = new MailInformation();
        mailInfo.setMailFrom(mailInformation.getMailFrom());
        mailInfo.setMailTo(mailInformation.getMailTo());
        mailInfo.setMailBody(mailInformation.getMailBody());
        mailInfo.setMailSubject(mailInformation.getMailSubject());
        mailInfo.setMailCc(mailInformation.getMailCc());

        mailInfo = mailInformationRepository.save(mailInfo);
        return new ResponseEntity<Object>(mailInfo, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MailInformation> delete(@PathVariable final String id) {

        Optional<MailInformation> currentMailInfo = mailInformationRepository.findById(id);

        return currentMailInfo.map(result -> {
            mailInformationRepository.delete(result.getId());
            return new ResponseEntity(HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody MailInformation mailInformation) {

        if (mailInformation == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<MailInformation> currentMailInfo = mailInformationRepository.findById(id);
        if (!currentMailInfo.isPresent()) {
            return ResponseEntity.notFound().build();

        } else {

            currentMailInfo.get().setMailFrom(mailInformation.getMailFrom());
            currentMailInfo.get().setMailTo(mailInformation.getMailTo());
            currentMailInfo.get().setMailBody(mailInformation.getMailBody());
            currentMailInfo.get().setMailSubject(mailInformation.getMailSubject());
            currentMailInfo.get().setMailCc(mailInformation.getMailCc());

            MailInformation newMailInfo = mailInformationRepository.save(currentMailInfo.get());
            return new ResponseEntity<Object>(newMailInfo, HttpStatus.CREATED);
        }
    }

}
