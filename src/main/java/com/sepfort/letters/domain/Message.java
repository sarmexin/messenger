package com.sepfort.letters.domain;

import com.sepfort.letters.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "message")
public class Message {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID message_id;

    private Status status;

    private String text;

    private UUID image;
}
