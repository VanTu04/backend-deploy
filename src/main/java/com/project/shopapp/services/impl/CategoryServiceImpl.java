package com.project.shopapp.services.impl;

import com.project.shopapp.DTO.CategoryDTO;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.repositories.OrderItemRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long categoryId,
                                   CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    @Transactional
    public void deleteCategory(long id) {
        // Xóa tất cả sản phẩm liên quan đến danh mục
        List<Product> products = productRepository.findByCategoryId(id); // Lấy danh sách sản phẩm theo category_id
        for (Product product : products) {
            Product product1 = productRepository.findById(product.getId()).get();
            orderItemRepository.deleteByFood(product1); // Xóa tất cả order_item liên quan đến sản phẩm
            productRepository.deleteById(product.getId()); // Xóa sản phẩm
        }

        // Xóa danh mục
        categoryRepository.deleteById(id);
    }

}
