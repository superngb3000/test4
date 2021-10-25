package com.example.pr14.controllers;

import com.example.pr14.models.Author;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс контроллер страницы авторов со свойством <b>authors</b>
 */
@Component
@RequestMapping("/author")
public class AuthorController {

    /** Поле массив авторов */
    List<Author> authors = new ArrayList();

    /**
     * Функция отображения списка всех авторов {@link AuthorController#authors}
     * @param model - хеш-таблица ключ-значений страницы
     * @return HTML страница
     */
    @GetMapping()
    public String get(Model model) {
        model.addAttribute("authors", authors);
        return "author";
    }

    /**
     * Функция создания элемента и добавления его в массив {@link AuthorController#authors}
     * @param firstName - имя автора
     * @param lastName - фамилия автора
     * @param middleName - отчество автора
     * @param birthDate - дата рождения автора
     * @param model - хеш-таблица ключ-значений страницы
     * @return HTML страница
     */
    @PostMapping("/create")
    public String create(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String middleName,
                         @RequestParam String birthDate,
                         Model model){
        authors.add(new Author(firstName, lastName, middleName, birthDate));
        model.addAttribute("authors", authors);
        return "redirect:/author";
    }

    /**
     * Функция удаления элемента массива {@link AuthorController#authors}
     * @param firstName - имя автора
     * @param lastName - фамилия автора
     * @param middleName - отчество автора
     * @param birthDate - дата рождения автора
     * @return HTML страница
     */
    @PostMapping("/delete")
    public String delete(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String middleName,
                         @RequestParam String birthDate){
        authors.removeIf(author -> (author.getFirstName().equals(firstName)
                && author.getLastName().equals(lastName)
                && author.getMiddleName().equals(middleName)
                && author.getBirthDate().equals(birthDate)));
        return "redirect:/author";
    }
}
