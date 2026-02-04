package sardorcreate.dto.ip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IpCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private long price;
}
