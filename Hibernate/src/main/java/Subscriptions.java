import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Subscriptions")
public class Subscriptions {

    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable=false, updatable=false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

}
