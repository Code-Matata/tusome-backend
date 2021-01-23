package ke.co.willynganga.tusomebackend.controllers;

import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.other.Category;
import ke.co.willynganga.tusomebackend.services.ImageService;
import ke.co.willynganga.tusomebackend.services.PaperService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/papers")
public class PaperController {

    private final ImageService imageService;
    private final PaperService paperService;

    public PaperController(ImageService imageService, PaperService paperService) {
        this.imageService = imageService;
        this.paperService = paperService;
    }

    @GetMapping("/getPaper/{id}")
    public Paper getPaper(@PathVariable("id") long id) {
        return paperService.getPaper(id);
    }

    @GetMapping("/getAllPapers")
    public List<Paper> getAllPapers() {
        return paperService.getAllPapers();
    }

    @GetMapping("/getPaperByTitle/{year}")
    public List<Paper> getPapersByYear(@PathVariable("year") int year) {
        return paperService.getPapersByYear(year);
    }

    @GetMapping("/getPaperByTitle/{category}")
    public List<Paper> getPapersByCategory(@PathVariable("category") Category category) {
        return paperService.getPapersByCategory(category);
    }

    @GetMapping("/getPaperByTitle/{title}")
    public List<Paper> getPaperByTitle(@PathVariable("title") String title) {
        return paperService.getPapersByTitle(title);
    }

    @PostMapping("/addPaper")
    public String addPaper(@RequestParam("image") MultipartFile image,
                           @RequestParam("title") String title,
                           @RequestParam("year") int year,
                           @RequestParam("category") Category category) {
        Paper paper = new Paper(title, year, category);
        imageService.addImage(paper.getId(), image);
        paper.setImageUrl("http://localhost:8083/api/v1/images/getImage/" + paper.getId());
        return paperService.addPaper(paper);
    }
}
