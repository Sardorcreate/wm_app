package sardorcreate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = false")
public class IpPhone extends Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

}