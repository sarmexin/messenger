//package com.sepfort.letters.config;
//
//import com.sepfort.letters.domain.enums.Status;
//import com.sepfort.letters.domain.Hello;
//import com.sepfort.letters.domain.Order;
//import com.sepfort.letters.repositories.GreetingTestRepository;
//import com.sepfort.letters.repositories.OrderRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//class LoadDataBase {
//    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
//
//    // Spring Boot запустит ВСЕ CommandLineRunner bean-компоненты после загрузки контекста приложения
//    @Bean
//    CommandLineRunner initDataBase(GreetingTestRepository greetingTestRepository, OrderRepository orderRepository) {
//
//        return args -> {
//            greetingTestRepository.save(new Hello("Первый"));
//            greetingTestRepository.save(new Hello("Второй"));
//            greetingTestRepository.findAll().forEach(hello -> log.info("Preloaded " + hello));
//
//            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
//            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
//            orderRepository.findAll().forEach(order -> log.info("Preload " + order));
//        };
//    }
//}
//TODO Здесь если надо можно инициализировать что то. Хотя можно и @PostConstruct использовать
