package home11.model;

import home11.entity.Manufacturer;
import lombok.Data;

@Data
public class ManufacturerDto {
    private Long id;
    private String title;
    private Double balance;

    public ManufacturerDto(Manufacturer manufacturer) {
        this.id = manufacturer.getId();
        this.title = manufacturer.getTitle();
        this.balance = manufacturer.getBalance();
    }
}
