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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final UserService userService;
    private final RoleService roleService;
    private final SecurityConfig securityConfig;


    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }


    @PostMapping
    public void createNewProduct(@RequestBody ProductDto product) {
        productService.create(product);
    }
}
