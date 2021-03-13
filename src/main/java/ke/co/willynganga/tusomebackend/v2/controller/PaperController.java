package ke.co.willynganga.tusomebackend.v2.controller;

import ke.co.willynganga.tusomebackend.v2.other.Category;
import ke.co.willynganga.tusomebackend.v2.models.ExamFile;
import ke.co.willynganga.tusomebackend.v2.models.Paper;
import ke.co.willynganga.tusomebackend.v2.other.CourseType;
import ke.co.willynganga.tusomebackend.v2.services.ExamFileService;
import ke.co.willynganga.tusomebackend.v2.services.PaperService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@RestController()
@RequestMapping("api/v2")
@CrossOrigin
public class PaperController {

    private final PaperService paperService;
    private final ExamFileService examFileService;

    public PaperController(PaperService paperService, ExamFileService examFileService) {
        this.paperService = paperService;
        this.examFileService = examFileService;
    }

    @GetMapping("/papers/{id}")
    public Paper getPaper(@PathVariable("id") long id) {
        return paperService.getPaper(id);
    }

    @GetMapping("/papers")
    public List<Paper> getAllPapers() {
        return paperService.getAllPapers();
    }

    @GetMapping("/papers/year/{year}")
    public List<Paper> getPapersByYear(@PathVariable("year") int year) {
        return paperService.getPapersByYear(year);
    }

    @GetMapping("/papers/category/{category}")
    public List<Paper> getPapersByCategory(@PathVariable("category") Category category) {
        return paperService.getPapersByCategory(category);
    }

    @GetMapping("/papers/title/{title}")
    public List<Paper> getPaperByTitle(@PathVariable("title") String title) {
        return paperService.getPapersByTitle(title);
    }

    @PostMapping("/addPaper")
    public String addPaper(@RequestParam("file") MultipartFile file,
                           @RequestParam("title") String title,
                           @RequestParam("year") int year,
                           @RequestParam("category") Category category,
                           @RequestParam("courseType") CourseType courseType) {
        Paper paper = new Paper(title, year, category, courseType);
        ExamFile examFile = examFileService.addExamFile(file);
        if (examFile != null) {
            paper.setFileUrl("http://localhost:8083/api/v2/files/" + examFile.getId());
        }
        return paperService.addPaper(paper);
    }

    @GetMapping(value = "/files/{id}", produces = {"application/pdf"})
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {
        ExamFile file = examFileService.getFileById(id);
        if (file != null) {
            Instant instant = Instant.now();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                            instant.getEpochSecond())
                    .body(file.getData());
        }
        return null;
    }
}
