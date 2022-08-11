package home11.repositories;

import home11.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByTitle(String title);

    @Override
    List<Cart> findAll();

    @Modifying
    @Query(value = "insert into cart (title, price, amount) values(:title, :price, :amount)",
            nativeQuery = true)
    void insertCart(@Param("title") String title, @Param("price") int price, @Param("amount") int amount);
}
