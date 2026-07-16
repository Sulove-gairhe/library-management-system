package com.example.inventory.product;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductWebController {

    private final ProductService productService;

    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        prepareForm(model, new Product(), "/products", "Add New Product", false);
        return "products/form";
    }

    @PostMapping("/products")
    public String createProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, product, "/products", "Add New Product", false);
            return "products/form";
        }

        try {
            productService.createProduct(product);
        } catch (DuplicateSkuException exception) {
            bindingResult.rejectValue("sku", "duplicate", "SKU already exists");
            prepareForm(model, product, "/products", "Add New Product", false);
            return "products/form";
        }

        redirectAttributes.addFlashAttribute("toastMessage", "Product created successfully.");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        return productService.getProductById(id)
                .map(product -> {
                    prepareForm(model, product, "/products/" + id + "/update", "Edit Product", true);
                    return "products/form";
                })
                .orElseGet(() -> showError(model, "Product not found"));
    }

    @PostMapping("/products/{id}/update")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        product.setId(id);
        if (bindingResult.hasErrors()) {
            prepareForm(model, product, "/products/" + id + "/update", "Edit Product", true);
            return "products/form";
        }

        try {
            if (productService.updateProduct(id, product).isEmpty()) {
                return showError(model, "Product not found");
            }
        } catch (DuplicateSkuException exception) {
            bindingResult.rejectValue("sku", "duplicate", "SKU already exists");
            prepareForm(model, product, "/products/" + id + "/update", "Edit Product", true);
            return "products/form";
        }

        redirectAttributes.addFlashAttribute("toastMessage", "Product updated successfully.");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        if (!productService.deleteProduct(id)) {
            return showError(model, "Product not found");
        }

        redirectAttributes.addFlashAttribute("toastMessage", "Product deleted successfully.");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/products";
    }

    private void prepareForm(Model model, Product product, String actionUrl, String pageTitle, boolean editing) {
        model.addAttribute("product", product);
        model.addAttribute("actionUrl", actionUrl);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("editing", editing);
    }

    private String showError(Model model, String message) {
        model.addAttribute("message", message);
        return "error";
    }
}
