package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.DepLevel;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DepLevel level;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parent;

}
