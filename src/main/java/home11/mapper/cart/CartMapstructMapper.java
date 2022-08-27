package home11.mapper.cart;
import home11.entity.Cart;
import home11.entity.Product;
import home11.entity.User;
import home11.model.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.InjectionStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapstructMapper {
    @Mappings(value = {
            @Mapping(target = "id", source = "cart.id"),
            @Mapping(target = "productTitle", source = "product.title"),
            @Mapping(target = "productPrice", source = "product.price"),
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "userId", source = "user.id")
    })
    CartDto mapToDto(Product product, User user, Cart cart);
}
