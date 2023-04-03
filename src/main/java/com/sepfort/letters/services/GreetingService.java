package com.sepfort.letters.services;

import com.sepfort.letters.models.Hello;
import com.sepfort.letters.repositories.GreetingTestRepository;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingTestRepository greetingTestRepository;

    public GreetingService(GreetingTestRepository greetingTestRepository) {
        this.greetingTestRepository = greetingTestRepository;
    }

    public String getGreetingById(Integer id) {
        return greetingTestRepository.findById(id).isPresent() ? greetingTestRepository.findById(id).get().getText() : "Пусто";
    }
}
