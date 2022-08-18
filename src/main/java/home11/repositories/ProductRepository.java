package home11.repositories;

import home11.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    @Modifying
    @Query(value = "insert into products (title, price, category_id) values(:title, :price, :category)",
            nativeQuery = true)
    void insertProduct(@Param("title") String title, @Param("price") int price, @Param("category") Long category);

    Page<Product> findAllByCategory_Id( Long category_id, Pageable pageable);
}














