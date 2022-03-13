package ke.co.willynganga.tusomebackend.controllers;

import io.swagger.annotations.ApiOperation;
import ke.co.willynganga.tusomebackend.models.Image;
import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.models.PaperDTO;
import ke.co.willynganga.tusomebackend.other.Category;
import ke.co.willynganga.tusomebackend.services.ImageService;
import ke.co.willynganga.tusomebackend.services.ImageUrlService;
import ke.co.willynganga.tusomebackend.services.PaperService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/papers")
@CrossOrigin
public class PaperController {

    private final ImageService imageService;
    private final PaperService paperService;
    private final ImageUrlService urlService;

    public PaperController(ImageService imageService, PaperService paperService, ImageUrlService urlService) {
        this.imageService = imageService;
        this.paperService = paperService;
        this.urlService = urlService;
    }

    @ApiOperation(
            value = "Get a paper",
            notes = "Fetches the details for a single paper"
    )
    @GetMapping("/getPaper/{id}")
    public Paper getPaper(@PathVariable("id") long id) {
        return paperService.getPaper(id);
    }

    @ApiOperation(
            value = "Get all papers.",
            notes = "Fetches the details of all papers in DB."
    )
    @GetMapping("/getAllPapers")
    public List<Paper> getAllPapers() {
        return paperService.getAllPapers();
    }

    @ApiOperation(
            value = "Get all papers",
            notes = "Fetches the details for all papers in DB in a paginated fashion."
    )
    @GetMapping("/all")
    public PaperDTO getPagedPapers(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return paperService.getPagedPapers(pageable);
    }

    @ApiOperation(
            value = "Get papers for year.",
            notes = "Fetches the details for all papers for a particular year in DB."
    )
    @GetMapping("/getPaperByTitle/{year}")
    public List<Paper> getPapersByYear(@PathVariable("year") int year) {
        return paperService.getPapersByYear(year);
    }

    @ApiOperation(
            value = "Get papers for year.",
            notes = "Fetches the details for all papers with a particular title in DB in a paginated fashion."
    )
    @GetMapping("/year/{year}")
    public PaperDTO getPapersByYear(@PathVariable("year") int year, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return paperService.getPagedPapersByYear(year, pageable);
    }

    @ApiOperation(
            value = "Get papers by category.",
            notes = "Fetches the details for all papers in provided category in DB."
    )
    @GetMapping("/getPaperByTitle/{category}")
    public List<Paper> getPapersByCategory(@PathVariable("category") Category category) {
        return paperService.getPapersByCategory(category);
    }

    @ApiOperation(
            value = "Get papers by category.",
            notes = "Fetches the details for all papers in provided category in DB in a paginated fashion."
    )
    @GetMapping("/category/{category}")
    public PaperDTO getPagedPapersByCategory(@PathVariable("category") Category category, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return paperService.getPagedPapersByCategory(category, pageable);
    }

    @ApiOperation(
            value = "Get papers by title.",
            notes = "Fetches the details for all papers with provided title in DB."
    )
    @GetMapping("/getPaperByTitle/{title}")
    public List<Paper> getPaperByTitle(@PathVariable("title") String title) {
        return paperService.getPapersByTitle(title);
    }

    @ApiOperation(
            value = "Get papers by title.",
            notes = "Fetches the details for all papers with provided title in DB in a paginated fashion."
    )
    @GetMapping("/title/{title}")
    public PaperDTO getPaperByTitle(@PathVariable("title") String title, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return paperService.getPagedPapersByTitle(title, pageable);
    }

    @ApiOperation(
            value = "Post new paper.",
            notes = "Insert new paper into DB."
    )
    @PostMapping("/addPaper")
    public String addPaper(@RequestParam("images") MultipartFile[] images,
                           @RequestParam("title") String title,
                           @RequestParam("year") int year,
                           @RequestParam("category") Category category) {
        Paper paper = new Paper(title, year, category);
        String response = paperService.addPaper(paper);
        Arrays.stream(images).forEach(image -> saveImage(image, paper));
        return response;
    }

    private void saveImage(MultipartFile image, Paper paper) {
        urlService.addUrl("https://tusome-app.herokuapp.com/api/v1/images/getImage" + imageService.addImage(image).getId(), paper);
    }

    @ApiIgnore
    @DeleteMapping("/deletePaper/{id}")
    public String deletePaper(@PathVariable("id") long id) {
        return paperService.deletePaper(id);
    }
}
