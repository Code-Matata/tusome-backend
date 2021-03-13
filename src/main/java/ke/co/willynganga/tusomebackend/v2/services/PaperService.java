package ke.co.willynganga.tusomebackend.v2.services;

import ke.co.willynganga.tusomebackend.v2.models.Paper;
import ke.co.willynganga.tusomebackend.v2.other.Category;
import ke.co.willynganga.tusomebackend.v2.other.CourseType;
import ke.co.willynganga.tusomebackend.v2.repository.PaperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperService {
    private final PaperRepository paperRepository;

    public PaperService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public Paper getPaper(long id) {
        return paperRepository.findById(id).orElse(null);
    }

    public List<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    public List<Paper> getPapersByYear(int year) {
        return paperRepository.findPapersByYear(year);
    }

    public List<Paper> getPapersByCategory(Category category) {
        return paperRepository.findPapersByPaperCategory(category);
    }

    public List<Paper> getPaperByCourseType(CourseType courseType) {
        return paperRepository.findPaperByCourseType(courseType);
    }

    public long getItemCount() {
        return paperRepository.count();
    }

    public List<Paper> getPapersByTitle(String title) {
        return null;
    }

    public String addPaper(Paper paper) {
        if (paper != null) {
            paperRepository.save(paper);
            paperRepository.flush();
            return "Paper saved successfully!";
        }
        return "Paper cannot be null!";
    }

    public String deletePaper(long id) {
        Optional<Paper> paper = paperRepository.findById(id);
        paper.ifPresent(paperRepository::delete);
        return paper.isPresent() ? "Paper has been deleted successfully!" : "Paper cannot be found!";
    }
}
