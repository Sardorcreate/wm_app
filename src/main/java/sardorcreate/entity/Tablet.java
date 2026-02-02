package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.OSType;

@Getter
@Setter
@Entity
public class Tablet extends Tool{

    @Enumerated(EnumType.STRING)
    private OSType osType;

    private int ram;
    private int rom;
}
