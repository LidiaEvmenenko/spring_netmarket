package home11.services;

import home11.entity.Category;
import home11.entity.Manufacturer;
import home11.entity.Product;
import home11.model.ProductDto;
import home11.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public Page<Product> findAll(int pageIndex, int pageSize){
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @Transactional
    public void create(ProductDto dto) {
        byte[] bytes = new byte[1];
        Optional<Category> category = categoryService.findByTitle(dto.getCategoryTitle());
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(dto.getManufacturerTitle());
        productRepository.insertProduct(dto.getDescription(),
                dto.getExpiration_date(), bytes,dto.getPrice(),dto.getTitle(),
                dto.getWeight(),category.get().getId(),manufacturer.get().getId());
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


}
