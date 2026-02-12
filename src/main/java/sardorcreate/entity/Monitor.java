package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@Entity
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int size;

    private int refreshRate;

    @Enumerated(EnumType.STRING)
    private ScreenType monType;
}
