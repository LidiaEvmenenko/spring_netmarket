package home11.repositories;

import home11.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

//import java.awt.print.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    //   @Query("update p from Product p where p.price <= :maxPrice and p.price >= :minPrice")
//    List<Product> findQ(int minPrice, int maxPrice);
    // Product saveOrUpdate(Product product);
    @Modifying
    @Query(value = "insert into products (title, price, category_id) values(:title, :price, :category)",
            nativeQuery = true)
    void insertProduct(@Param("title") String title, @Param("price") int price, @Param("category") Long category);

//    @Modifying
//    @Query(value = "select p from Products p where p.category_id =  :category_id)",
//            nativeQuery = true)
//    Page<Product> findAllByCategory_Id(@Param("category_id") Long category_id, Pageable pageable);
    Page<Product> findAllByCategory_Id( Long category_id, Pageable pageable);
}














