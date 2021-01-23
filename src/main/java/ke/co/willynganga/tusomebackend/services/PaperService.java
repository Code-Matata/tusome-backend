package ke.co.willynganga.tusomebackend.services;

import ke.co.willynganga.tusomebackend.models.Paper;
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

    public PaperService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public Paper getPaper(long id) {
        Optional<Paper> paper = paperRepository.findById(id);
        return paper.orElse(null);
    }

    public List<Paper> getAllPapers() {
        return paperRepository.findAll();
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
