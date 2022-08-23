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

import java.util.Date;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    @Modifying
    @Query(value = "insert into products " +
            "(description,expiration_date,photo,price,title,weight,category_id,manufacturer_id) " +
            "values(:description,:expiration_date,:photo,:price,:title,:weight,:category_id,:manufacturer_id)",
            nativeQuery = true)
    void insertProduct(@Param("description") String description, @Param("expiration_date") Date expiration_date,
                       @Param("photo") byte[] photo, @Param("price") Double price, @Param("title") String title,
                       @Param("weight") Double weight, @Param("category_id") Long category_id,
                       @Param("manufacturer_id") Long manufacturer_id);

    Page<Product> findAllByCategory_Id( Long category_id, Pageable pageable);

    Optional<Product> findByTitle(String title);
}














