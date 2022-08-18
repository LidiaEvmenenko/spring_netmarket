package home11.mapper.product;

import home11.entity.Product;
import home11.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    public ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            dto.setCategoryTitle(product.getCategory().getTitle());
        }
        return dto;
    }
}
