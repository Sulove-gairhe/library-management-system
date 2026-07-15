package com.example.library.book;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookWebController {

    private final BookService bookService;

    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        prepareForm(model, new Book(), "/books", "Add Book", false);
        return "books/form";
    }

    @PostMapping("/books")
    public String createBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, book, "/books", "Add Book", false);
            return "books/form";
        }

        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        return bookService.getBookById(id)
                .map(book -> {
                    prepareForm(model, book, "/books/" + id + "/update", "Edit Book", true);
                    return "books/form";
                })
                .orElseGet(() -> showError(model, "Book not found"));
    }

    @PostMapping("/books/{id}/update")
    public String updateBook(
            @PathVariable Long id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            Model model) {
        book.setId(id);
        if (bindingResult.hasErrors()) {
            prepareForm(model, book, "/books/" + id + "/update", "Edit Book", true);
            return "books/form";
        }

        if (bookService.updateBook(id, book).isEmpty()) {
            return showError(model, "Book not found");
        }

        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable Long id, Model model) {
        if (!bookService.deleteBook(id)) {
            return showError(model, "Book not found");
        }

        return "redirect:/books";
    }

    private void prepareForm(Model model, Book book, String actionUrl, String pageTitle, boolean editing) {
        model.addAttribute("book", book);
        model.addAttribute("actionUrl", actionUrl);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("editing", editing);
    }

    private String showError(Model model, String message) {
        model.addAttribute("message", message);
        return "error";
    }
}
