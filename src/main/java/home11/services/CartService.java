package home11.services;

import home11.dto.ProductDto;
import home11.model.Cart;
import home11.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Page<Cart> findAll(int pageIndex, int pageSize){
        return cartRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id){
        cartRepository.deleteById(id);
    }

    public void deleteAll(){
        cartRepository.deleteAll();
    }

    @Transactional
    public void addNewProductToCart(ProductDto productDto, int count){
     //   List<Cart> carts = cartRepository.findAll();
        Cart cart = cartRepository.findByTitle(productDto.getTitle());
        if(cart == null) {
            cartRepository.insertCart(productDto.getTitle(), productDto.getPrice(), count);
//            cart = new Cart();
//            Long newId = 1L;
//            if (carts.size() != 0) {
//                newId = carts.stream().mapToLong(Cart::getId).max().getAsLong() + 1;
//            }
//            cart.setId(newId);
//            cart.setTitle(productDto.getTitle());
//            cart.setPrice(productDto.getPrice());
//            cart.setAmount(1);
        }else {

            cart.setAmount( cart.getAmount() + count);
            cartRepository.save(cart);
        }

    }
    public int sumItogCart(){
        List<Cart> carts = cartRepository.findAll();
        int sum = 0;
        for (int i=0; i<carts.size(); i++){
            sum = sum + carts.get(i).getPrice() * carts.get(i).getAmount();
        }
        return sum;
    }
}
