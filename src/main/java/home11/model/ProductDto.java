package home11.model;

import home11.entity.Product;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long id;
    private int nom = 0;
    private String title;
    private String categoryTitle;
    private Double price;
    private byte[] image;
    private Date expiration_date;
    private String description;
    private Double weight;
    private String manufacturerTitle;
    private int count;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.nom++;
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
        this.count = 1;
        this.image = product.getPhoto();
        this.expiration_date = product.getExpiration_date();
        this.description = product.getDescription();
        this.weight = product.getWeight();
        this.manufacturerTitle = product.getManufacturer().getTitle();

    }

    public ProductDto() {
    }
}
