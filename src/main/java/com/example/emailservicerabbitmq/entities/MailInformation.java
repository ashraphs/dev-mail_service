package com.example.emailservicerabbitmq.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mail_information")
public class MailInformation extends MasterEntity {

    @Column(name = "mail_from")
    private String mailFrom;

    @Column(name = "mail_to")
    private String mailTo;

    @Column(name = "mail_cc")
    private String mailCc;

    @Column(name = "mail_subject")
    private String mailSubject;

    @Column(name = "mail_body")
    private String mailBody;

}
