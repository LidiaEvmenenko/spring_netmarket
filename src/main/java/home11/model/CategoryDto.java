package home11.model;

import home11.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }
}