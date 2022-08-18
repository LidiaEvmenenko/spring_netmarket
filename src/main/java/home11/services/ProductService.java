package home11.services;

import home11.entity.Category;
import home11.entity.Product;
import home11.exceptions.ResourceNotFoundException;
import home11.model.ProductDto;
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
        Product product = findById(productDto.getId()).get();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto
                .getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category title = "+ productDto.getCategoryTitle() +" not found"));
        product.setCategory(category);

    }

}
