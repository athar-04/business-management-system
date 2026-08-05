package com.athar.bms.inventory.repository;

import com.athar.bms.godown.entity.Godown;
import com.athar.bms.inventory.entity.Inventory;
import com.athar.bms.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductAndGodown(Product product, Godown godown);

    List<Inventory> findByProduct(Product product);

    List<Inventory> findByGodown(Godown godown);
}