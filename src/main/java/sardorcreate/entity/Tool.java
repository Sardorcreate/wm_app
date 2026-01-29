package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ToolsName;

@Getter
@Setter
@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    private ToolsName name;

    private String inventoryNum;

    @ManyToOne
    private Employee owner;

    private String model;

}
