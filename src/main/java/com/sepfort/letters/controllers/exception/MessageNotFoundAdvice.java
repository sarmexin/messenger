package com.sepfort.letters.controllers.exception;

import com.sepfort.letters.exceptions.MessageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MessageNotFoundAdvice {

    // @ResponseBody сигнализирует о том, что этот совет отображается прямо в теле ответа.
    @ResponseBody
    // @ExceptionHandler настраивает совет так, чтобы он отвечал только в том случае, если HelloException выброшено сообщение
    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String messageNotFoundHandler(MessageNotFoundException ex) {
        return ex.getMessage();
    }
}
