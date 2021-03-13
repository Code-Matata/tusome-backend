package ke.co.willynganga.tusomebackend.v2.repository;

import ke.co.willynganga.tusomebackend.v2.other.Category;
import ke.co.willynganga.tusomebackend.v2.models.Paper;
import ke.co.willynganga.tusomebackend.v2.other.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findPapersByYear(int year);

    List<Paper> findPapersByPaperCategory(Category category);

    List<Paper> findPapersByTitle(String title);

    List<Paper> findPaperByCourseType(CourseType courseType);
}
