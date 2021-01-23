package ke.co.willynganga.tusomebackend.repositories;

import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.other.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findPapersByYear(int year);

    List<Paper> findPapersByPaperCategory(Category category);

    List<Paper> findPapersByTitle(String title);

}
