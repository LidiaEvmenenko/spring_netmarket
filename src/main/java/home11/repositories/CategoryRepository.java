package home11.repositories;


import home11.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
    //@Override
//    List<Category> findAll();
  //      @Query("select c from Categories r where r.name = name")
//    Role findR(String name);
}
