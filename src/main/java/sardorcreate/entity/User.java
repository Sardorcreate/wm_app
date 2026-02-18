package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.UserStatus;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String fullName;
    private String login;
    private String password;

    @ManyToOne
    private Department department;

    private String roles;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
