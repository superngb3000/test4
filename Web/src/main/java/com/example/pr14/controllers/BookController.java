package com.example.pr14.controllers;

import com.example.pr14.models.Author;
import com.example.pr14.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс контроллер страницы книг со свойством <b>books</b>
 */
@Component
@RequestMapping("/book")
public class BookController {

    /** Поле массив книг */
    List<Book> books = new ArrayList();

    /**
     * Функция отображения списка всех кинг {@link BookController#books}
     * @param model - хеш-таблица ключ-значений страницы
     * @return HTML страница
     */
    @GetMapping()
    public String get(Model model) {
        model.addAttribute("books", books);
        return "book";
    }

    /**
     * Функция создания элемента и добавления его в массив {@link BookController#books}
     * @param name - низвание книги
     * @param model - хеш-таблица ключ-значений страницы
     * @return HTML страница
     */
    @PostMapping("/create")
    public String create(@RequestParam String name, Model model){
        books.add(new Book(name));
        model.addAttribute("books", books);
        return "redirect:/book";
    }

    /**
     * Функция удаления элемента массива {@link BookController#books}
     * @param name - низвание книги
     * @return HTML страница
     */
    @PostMapping("/delete")
    public String delete(@RequestParam String name){
        books.removeIf(book -> (book.getName().equals(name)));
        return "redirect:/book";
    }
}
