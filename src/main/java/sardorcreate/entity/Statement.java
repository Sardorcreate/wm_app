package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.StateStatus;
import sardorcreate.enums.ToolsName;

@Getter
@Setter
@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private ToolsName tool;
    private String title;
    private String text;

    @Enumerated(EnumType.STRING)
    private StateStatus status;

    @ManyToOne
    private User user;
}
