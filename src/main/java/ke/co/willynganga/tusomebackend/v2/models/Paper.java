package ke.co.willynganga.tusomebackend.v2.models;


import ke.co.willynganga.tusomebackend.v2.other.Category;
import ke.co.willynganga.tusomebackend.v2.other.CourseType;
import lombok.*;

import javax.persistence.*;

@Entity(name = "papers")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NonNull String title;
    @NonNull Integer year;
    @NonNull Category paperCategory;
    @NonNull CourseType courseType;
    String fileUrl;
}
