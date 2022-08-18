package home11.controllers;

import home11.configs.SecurityConfig;
import home11.entity.Category;
import home11.exceptions.DataValidationException;
import home11.exceptions.ResourceNotFoundException;
import home11.model.*;
import home11.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final UserService userService;
    private final RoleService roleService;
    private final SecurityConfig securityConfig;


    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
    }
    @GetMapping("/category")
    public List<CategoryDto> findAll(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        for (Category c:categories) {
            CategoryDto categoryDto = new CategoryDto(c);
           categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }
    @GetMapping("/products/category")
    public Page<ProductDto> findByCategory(@RequestParam(name = "p", defaultValue = "1") int pageIndex,
                 @RequestParam(name = "title", defaultValue = "1") String title) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findByCategory(pageIndex - 1, 5, title).map(ProductDto::new);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @DeleteMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        productService.deleteAll();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found")));
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct( @RequestBody ProductDto productDto) {//@PathVariable Long id,
        productService.updateProduct(productDto);
    }



}
