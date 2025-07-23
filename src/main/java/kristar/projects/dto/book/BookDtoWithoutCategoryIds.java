package kristar.projects.dto.book;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BookDtoWithoutCategoryIds {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
