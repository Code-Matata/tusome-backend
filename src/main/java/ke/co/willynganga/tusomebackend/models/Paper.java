package ke.co.willynganga.tusomebackend.models;

import ke.co.willynganga.tusomebackend.other.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "papers_table")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NonNull String title;
    @NonNull int year;
    @NonNull Category paperCategory;
    String imageUrl;
}
