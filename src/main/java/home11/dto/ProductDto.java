package home11.dto;

import home11.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private int nom = 0;

    @NotNull(message = "Товар должен иметь название")
    @Length(min = 3, max = 255, message = "Длина названия товара должна составлять 3-255 символов")
    private String title;

    @Min(value = 1, message = "Цена товара должна быть не менее 1 рубля")
    private int price;

    @NotNull(message = "Товар должен иметь категорию")
    private String categoryTitle;

    private int count;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.nom++;
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
        this.count = 1;
    }
}