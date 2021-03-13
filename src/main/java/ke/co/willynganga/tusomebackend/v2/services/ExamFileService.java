package ke.co.willynganga.tusomebackend.v2.services;

import ke.co.willynganga.tusomebackend.v2.models.ExamFile;
import ke.co.willynganga.tusomebackend.v2.repository.ExamFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ExamFileService {

    private final ExamFileRepository examFileRepository;

    public ExamFileService(ExamFileRepository examFileRepository) {
        this.examFileRepository = examFileRepository;
    }

    public ExamFile getFileById(long id) {
        return examFileRepository.findById(id).orElse(null);
    }

    public ExamFile addExamFile(MultipartFile file) {
        if (file != null) {
            try {
                byte[] fileBytes = file.getBytes();
                ExamFile examFile = new ExamFile(fileBytes);
                examFileRepository.save(examFile);
                examFileRepository.flush();
                return examFile;
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    public boolean deleteExamFile(long id) {
        Optional<ExamFile> examFile = examFileRepository.findById(id);
        examFile.ifPresent(examFileRepository::delete);
        examFileRepository.flush();
        return examFile.isPresent();
    }
}
