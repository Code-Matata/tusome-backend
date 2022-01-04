package ke.co.willynganga.tusomebackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaperDTO {
    private Integer pages;
    private Integer currentPage;
    private List<Paper> papers;
}
