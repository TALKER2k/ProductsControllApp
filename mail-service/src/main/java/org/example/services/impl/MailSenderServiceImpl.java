package org.example.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.models.HistoryPickProduct;
import org.example.repositories.HistoryPickProductRepository;
import org.example.services.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender javaMailSender;
    private final HistoryPickProductRepository historyPickProductRepository;

    @Value("${spring.mail.username}")
    private String email;

    public MailSenderServiceImpl(JavaMailSender javaMailSender, HistoryPickProductRepository historyPickProductRepository) {
        this.javaMailSender = javaMailSender;
        this.historyPickProductRepository = historyPickProductRepository;
    }

    @Scheduled(cron = "0 0 20 * * *")
    @Override
    public void sendEmail() {
        var subject = "Отчёт по собранным товарам за текущий день";

        LocalDateTime today = LocalDateTime.now();
        List<HistoryPickProduct> records = historyPickProductRepository.findByDate(today);

        var messageBody = generateReport(records);

        var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(messageBody);

        javaMailSender.send(mailMessage);
        log.info("Mail service sent report.");
    }

    private String generateReport(List<HistoryPickProduct> records) {
        StringBuilder report = new StringBuilder();
        report.append("Отчет по собранным товарам за текущий день:\n\n");

        for (HistoryPickProduct record : records) {
            report.append("Наименование товара: ").append(record.getProduct().getName()).append("\n");
            report.append("Логин работника: ").append(record.getUser().getUserName()).append("\n");
            report.append("Количество: ").append(record.getPick()).append("\n");
            report.append("Дата сборки: ").append(record.getPickAt()).append("\n\n");
        }

        return report.toString();
    }
}
