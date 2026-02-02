package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ScannerType;

@Getter
@Setter
@Entity
public class Scanner extends Tool{

    @Enumerated(EnumType.STRING)
    private ScannerType type;

    private int dpi;
}
