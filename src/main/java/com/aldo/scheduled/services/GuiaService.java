package com.aldo.scheduled.services;

import com.aldo.scheduled.model.GuiaModel;
import com.aldo.scheduled.repositories.GuiaRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class GuiaService {

    private GuiaRepository guiaRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public GuiaModel incrementGuia(){
        String maxNumberStr = guiaRepository.findMaxNumber();

        int maxNumber = (maxNumberStr != null) ? Integer.parseInt(maxNumberStr) : 0;
        int newNumber = maxNumber + 1;
        int numDigits = Math.max(4, String.valueOf(newNumber).length());

        String formattedNumber = String.format("%0" + numDigits + "d", newNumber);

        GuiaModel newGuia = new GuiaModel();
        newGuia.setNumber(formattedNumber);
        newGuia.setDate(LocalDateTime.now());

        return guiaRepository.save(newGuia);
    }

}
