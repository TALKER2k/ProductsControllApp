package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.HistoryCollectProduct;
import org.example.repositories.HistoryCollectProductRepository;
import org.example.services.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender javaMailSender;
    private final HistoryCollectProductRepository historyCollectProductRepository;

    @Value("${spring.mail.username}")
    private String email;

    @Scheduled(cron = "0 0 20 * * *")
    @Override
    public void sendEmail() {
        var subject = "Отчёт по собранным товарам за текущий день";

        LocalDateTime today = LocalDateTime.now();
        List<HistoryCollectProduct> records = historyCollectProductRepository.findByDate(today);

        var messageBody = generateReport(records);

        var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(messageBody);

        javaMailSender.send(mailMessage);
        log.info("Mail service sent report.");
    }

    private String generateReport(List<HistoryCollectProduct> records) {
        StringBuilder report = new StringBuilder();
        report.append("Отчет по собранным товарам за текущий день:\n\n");

        for (HistoryCollectProduct record : records) {
            report.append("Наименование товара: ").append(record.getProduct().getName()).append("\n");
            report.append("Логин работника: ").append(record.getUser().getUserName()).append("\n");
            report.append("Количество: ").append(record.getCollect()).append("\n");
            report.append("Дата сборки: ").append(record.getCollectedAt()).append("\n\n");
        }

        return report.toString();
    }
}
