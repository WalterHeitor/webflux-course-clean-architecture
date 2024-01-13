package br.com.softwalter.webfluxcourse.data.mongodb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class UserData {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String password;
}
