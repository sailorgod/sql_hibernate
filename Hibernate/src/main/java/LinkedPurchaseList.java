import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Linked_Purchase_List")
public class LinkedPurchaseList
{
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LinkedPurchaseListPK id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "student_name", referencedColumnName = "student_name"),
            @JoinColumn(name = "course_name", referencedColumnName = "course_name"),
            @JoinColumn(name = "subscription_date", referencedColumnName = "subscription_date")
    })
    private PurchaseList purchaseList;

    private int price;

}
