package home11.mapper.cart;

import home11.entity.Cart;
import home11.model.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {
    public CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setProductTitle(cart.getProduct().getTitle());
        dto.setProductPrice(cart.getProduct().getPrice());
        dto.setAmount(cart.getAmount());
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setProductId(cart.getProduct().getId());
        return dto;
    }
}
