package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@Entity
public class SepMonitor extends Tool{

    private int size;

    private int refreshRate;

    @Enumerated(EnumType.STRING)
    private ScreenType type;
}
