package home11.mapper.product;

import home11.entity.Product;
import home11.model.ProductDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapstructMapper {
    @Mappings(value = {
            @Mapping(target = "categoryTitle", source = "category.title"),
            @Mapping(target = "manufacturerTitle", source = "manufacturer.title")
    })
    ProductDto mapToDto(Product product);
}
