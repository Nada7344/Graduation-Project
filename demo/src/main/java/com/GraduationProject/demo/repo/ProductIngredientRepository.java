package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.Ingredient;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.model.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Integer> {
    boolean existsByProductAndIngredient(Product product, Ingredient ingredient);

}
