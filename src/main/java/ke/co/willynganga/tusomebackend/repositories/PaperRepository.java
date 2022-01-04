package ke.co.willynganga.tusomebackend.repositories;

import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.other.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    Page<Paper> findPapersByYear(int year, Pageable pageable);

    Page<Paper> findPapersByPaperCategory(Category category, Pageable pageable);

    Page<Paper> findPapersByTitle(String title, Pageable pageable);

}
