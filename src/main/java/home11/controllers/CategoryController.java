package home11.controllers;

import home11.entity.Category;
import home11.mapper.product.ProductMapper;
import home11.model.CategoryDto;
import home11.model.ProductDto;
import home11.services.CategoryService;
import home11.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @PostMapping()
    public void createNewCategory(@RequestBody CategoryDto category) {
        categoryService.create(category.getTitle());
    }

    @GetMapping("/list")
    public List<CategoryDto> findAllCategory(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        for (Category c:categories) {
            CategoryDto categoryDto = new CategoryDto(c);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @GetMapping()
    public List<CategoryDto> findAllByCategory(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        for (Category c:categories) {
            CategoryDto categoryDto = new CategoryDto(c);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @GetMapping("/page")
    public Page<ProductDto> findByCategory(@RequestParam(name = "p", defaultValue = "1") int pageIndex,
                                           @RequestParam(name = "title", defaultValue = "1") String title) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findByCategory(pageIndex - 1, 5, title).map(ProductDto::new);
    }
}
