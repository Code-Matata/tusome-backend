package ke.co.willynganga.tusomebackend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ke.co.willynganga.tusomebackend.other.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "papers")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"imageUrls"})
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NonNull String title;
    @NonNull int year;
    @NonNull Category paperCategory;
    @JsonManagedReference
    @OneToMany(mappedBy = "paper",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<ImageUrl> imageUrls;
}
