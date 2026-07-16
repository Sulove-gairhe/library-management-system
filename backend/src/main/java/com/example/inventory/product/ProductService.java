package com.example.inventory.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (productRepository.existsBySkuIgnoreCase(product.getSku())) {
            throw new DuplicateSkuException(product.getSku());
        }

        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            if (productRepository.existsBySkuIgnoreCaseAndIdNot(productDetails.getSku(), id)) {
                throw new DuplicateSkuException(productDetails.getSku());
            }

            product.setName(productDetails.getName());
            product.setSku(productDetails.getSku());
            product.setCategory(productDetails.getCategory());
            product.setStock(productDetails.getStock());
            return productRepository.save(product);
        });
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }
}
