package ke.co.willynganga.tusomebackend.services;

import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.other.Category;
import ke.co.willynganga.tusomebackend.repositories.PaperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperService {
    private Logger logger = LoggerFactory.getLogger(PaperService.class);
    private final PaperRepository paperRepository;
    private final ImageService imageService;

    public PaperService(PaperRepository paperRepository, ImageService imageService) {
        this.paperRepository = paperRepository;
        this.imageService = imageService;
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

    public long getItemCount() {
        return paperRepository.count();
    }

    public List<Paper> getPapersByTitle(String title) {
        return paperRepository.findPapersByTitle(title);
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
        if (paper.isPresent()) {
            String url = paper.get().getImageUrl();
            long imageId = Long.parseLong(url.substring(45));
            imageService.deleteImage(imageId);
        }
        return paper.isPresent() ? "Paper has been deleted successfully!" : "Paper cannot be found!";
    }
}
