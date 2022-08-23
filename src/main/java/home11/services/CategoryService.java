package home11.services;

import home11.entity.Category;
import home11.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Transactional
    public void create(String title) {
        categoryRepository.insertCategory(title);
    }
}

