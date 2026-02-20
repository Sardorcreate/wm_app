package sardorcreate.dto.bid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.dto.user.UserDto;
import sardorcreate.enums.BidStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BidDto {

    private long id;
    private UserDto sender;
    private String title;
    private String body;
    private int count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BidStatus status;
}
