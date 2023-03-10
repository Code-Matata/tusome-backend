package ke.co.willynganga.tusomebackend.services;

import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.models.PaperDTO;
import ke.co.willynganga.tusomebackend.other.Category;
import ke.co.willynganga.tusomebackend.repositories.PaperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperService {
    private final Logger logger = LoggerFactory.getLogger(PaperService.class);
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

    public PaperDTO getPagedPapers(Pageable pageable) {
        Page<Paper> page = paperRepository.findAll(pageable);
        return PaperDTO.builder()
                .pages(page.getTotalPages())
                .currentPage(page.getNumber())
                .papers(page.getContent())
                .build();
    }

    public List<Paper> getPapersByYear(int year) {
        return paperRepository.findPapersByYear(year, null).getContent();
    }

    public PaperDTO getPagedPapersByYear(int year, Pageable pageable) {
        Page<Paper> page = paperRepository.findPapersByYear(year, null);
        return PaperDTO.builder()
                .pages(page.getTotalPages())
                .currentPage(page.getNumber())
                .papers(page.getContent())
                .build();
    }

    public List<Paper> getPapersByCategory(Category category) {
        return paperRepository.findPapersByPaperCategory(category, null).getContent();
    }

    public PaperDTO getPagedPapersByCategory(Category category, Pageable pageable) {
        Page<Paper> page = paperRepository.findPapersByPaperCategory(category, null);
        return PaperDTO.builder()
                .papers(page.getContent())
                .currentPage(page.getNumber())
                .papers(page.getContent())
                .build();
    }

    public long getItemCount() {
        return paperRepository.count();
    }

    public List<Paper> getPapersByTitle(String title) {
        return paperRepository.findPapersByTitle(title, null).getContent();
    }

    public PaperDTO getPagedPapersByTitle(String title, Pageable pageable) {
        Page<Paper> page = paperRepository.findPapersByTitle(title, null);
        return PaperDTO.builder()
                .papers(page.getContent())
                .currentPage(page.getNumber())
                .papers(page.getContent())
                .build();
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
        paper.ifPresent(value -> value.getImageUrls().stream()
                .map(imageUrl -> imageUrl.getUrl().substring(45))
                .map(Long::parseLong)
                .forEach(imageService::deleteImage));
        return paper.isPresent() ? "Paper has been deleted successfully!" : "Paper cannot be found!";
    }
}
