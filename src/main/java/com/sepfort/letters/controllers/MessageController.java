package com.sepfort.letters.controllers;

import com.sepfort.letters.components.MessageModelAssembler;
import com.sepfort.letters.domain.Message;
import com.sepfort.letters.domain.enums.Status;
import com.sepfort.letters.exceptions.MessageNotFoundException;
import com.sepfort.letters.repositories.MessageRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageRepository messageRepository;

    private final MessageModelAssembler assembler;

    public MessageController(MessageRepository messageRepository, MessageModelAssembler assembler) {
        this.messageRepository = messageRepository;
        this.assembler = assembler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Message>> getAllMessage() {

        List<EntityModel<Message>> messages = messageRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(messages,
                linkTo(methodOn(MessageController.class).getAllMessage()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Message> getMessageById(@PathVariable UUID id) {

        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(String.valueOf(id)));

        return assembler.toModel(message);
    }

    @PostMapping()
    public ResponseEntity<EntityModel<Message>> newMessage(@RequestBody Message message) {

        message.setStatus(Status.NEW);
        Message savedMessage = messageRepository.save(message);

        return ResponseEntity
                .created(linkTo(methodOn(MessageController.class).getMessageById(savedMessage.getMessage_id())).toUri())
                .body(assembler.toModel(savedMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessageById(@PathVariable UUID id) {

        Message message = getMessageByIdFromRepo(id);

        if (message.getStatus() == Status.NOT_ALLOWED_DELETE) {
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Method not allowed")
                            .withDetail("You can't deleted an message, because message is in the " + message.getStatus() + " status"));
        }

        message.setStatus(Status.REMOVE);
        //TODO проверить, правильно ли изменяется сущность в БД
        Message savedMessage = messageRepository.save(message);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assembler.toModel(savedMessage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMessageById(@PathVariable UUID id, @RequestBody Message editMessage) {

        Message message = getMessageByIdFromRepo(id);

        if (message.getStatus() == Status.NOT_ALLOWED_DELETE) {
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Method not allowed")
                            .withDetail("You can't deleted an message, because message is in the " + message.getStatus() + " status"));
        }

        message.setStatus(Status.EDIT);
        message.setText(editMessage.getText());
        Message savedMessage = messageRepository.save(message);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assembler.toModel(savedMessage));
    }

    private Message getMessageByIdFromRepo(UUID id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(String.valueOf(id)));
    }
}
