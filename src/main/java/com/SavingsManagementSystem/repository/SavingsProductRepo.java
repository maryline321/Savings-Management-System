package com.SavingsManagementSystem.repository;



import com.SavingsManagementSystem.model.SavingsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsProductRepo extends JpaRepository<SavingsProduct, Long> {
}
