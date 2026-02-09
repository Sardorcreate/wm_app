package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "inventory",
        uniqueConstraints =
        @UniqueConstraint(columnNames = "inventoryId")
)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "inventoryId", nullable = false)
    private long inventoryId;
}
