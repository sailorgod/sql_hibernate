import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PurchaseList")
public class PurchaseList
{
    @EmbeddedId
    PurchaseListKey key;

    @Column(name = "price")
    private int price;

}
