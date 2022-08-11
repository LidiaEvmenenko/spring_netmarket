package home11.services;

import home11.dto.ProductDto;
import home11.exceptions.ResourceNotFoundException;
import home11.model.Category;
import home11.model.Product;
import home11.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
  //  private final Cart cart;

    public Page<Product> findAll(int pageIndex, int pageSize){
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }
    @Transactional
    public Page<Product> findByCategory(int pageIndex, int pageSize, String title){
        Optional<Category> category = categoryService.findByTitle(title);

       return productRepository.findAllByCategory_Id(category.get().getId(), PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

//    @Transactional
//    public Product save(Product product){
//        return productRepository.save(product);
//    }
@Transactional
public void save(ProductDto productDto){
    Optional<Category> category = categoryService.findByTitle(productDto.getCategoryTitle());
    if (category.isEmpty()) {
        productRepository.insertProduct(productDto.getTitle(), productDto.getPrice(), 1L);
    }else {
        productRepository.insertProduct(productDto.getTitle(), productDto.getPrice(),
                category.get().getId());
    }

}

    //    public List<Product> findByPriceBetween(int minPrice, int maxPrice){
//        return productRepository.findQ(minPrice, maxPrice);
//    }
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public Product findByIdFromUpdate(Long id){
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public void updateProduct(ProductDto productDto){
     //   Product product = findByIdFromUpdate(productDto.getId());
        Product product = findById(productDto.getId()).get();
        //   product.setId(productDto.getId());

        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto
                .getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category title = "+ productDto.getCategoryTitle() +" not found"));
        product.setCategory(category);

    }



  //  public List<ProductDto> findAllToCart(){
//        return cart.getProductCart();
//    }
}
