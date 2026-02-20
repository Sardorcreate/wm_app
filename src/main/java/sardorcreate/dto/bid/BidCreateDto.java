package sardorcreate.dto.bid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidCreateDto {

    private long sender;
    private String title;
    private String body;
    private int count;
}
