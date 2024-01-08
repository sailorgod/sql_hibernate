import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LinkedPurchaseListPK implements Serializable {

    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;
}
