package com.example.emailservicerabbitmq.entities;

import com.example.emailservicerabbitmq.enums.SendMailStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_status")
public class NotificationStatus extends MasterEntity {

    @Column(name = "mail_from")
    private String mailFrom;

    @Column(name = "mailTo")
    private String mailTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "mail_status")
    private SendMailStatus statusMail;

    @Enumerated(EnumType.STRING)
    @Column(name = "sms_status")
    private SendMailStatus statusSMS;
}
