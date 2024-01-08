import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PurchaseListKey implements Serializable
{
 @Column(name = "student_name")
 private String studentName;

 @Column(name = "course_name")
 private String courseName;

 @Column(name = "subscription_date")
 private Date subscriptionDate;

}
