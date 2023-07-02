package com.sepfort.letters.components;

import com.sepfort.letters.controllers.MessageController;
import com.sepfort.letters.domain.Message;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MessageModelAssembler implements RepresentationModelAssembler<Message, EntityModel<Message>> {

    /**
     * Он основан на преобразовании объекта, не являющегося моделью (Message),
     * в объект, основанный на модели ( EntityModel<Employee>).
     *
     * @return EntityModel<T>
     */
    @Override
    public EntityModel<Message> toModel(Message message) {

        return EntityModel.of(message,
                linkTo(methodOn(MessageController.class).getAllMessage()).withSelfRel());
    }

}
