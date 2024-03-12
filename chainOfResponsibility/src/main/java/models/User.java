package models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    private String name;

    private String surname;

    private String email;

    private String password;

    private Status status;
}
