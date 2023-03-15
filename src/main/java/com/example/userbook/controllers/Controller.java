package com.example.userbook.controllers;

import com.example.userbook.models.Book;
import com.example.userbook.models.User;
import com.example.userbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class Controller {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/show")//выводит всех юзеров
    public Iterable<User> showUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/show/{id}")//выводит юзера по ID
    public Optional<User> showIdUser(@PathVariable("id") long id, User user) {
        return userRepository.findById(id);

    }



    @GetMapping("/show/{id}/books")//выводит все книги определённого юзера
    public List<Book> getBookIdUser(@PathVariable("id") long id,
                                      User user,
                                      Book book) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get().getBook();
    }


    @PostMapping("/save")//создаёт юзера
    public String saveUser(@RequestBody User user) {
        userRepository.save(user);
        return "Юзер " + user.getName()+ " " + user.getLast_name() + " успешно создан";
    }

    @PostMapping("/save/{id}/books")//добавляет книги определённому юзеру
    public String saveBookIdUser(@PathVariable("id") long id,
                                       @RequestBody Book book,
                                       User user) {
        Optional<User> userOptional = userRepository.findById(id);
        List<Book> listBook = userOptional.get().getBook();
        listBook.add(book);
        user = userOptional.get();
        user.setBook(listBook);
        userRepository.save(user);

        return "Книга " + book.getBook_name() + " добавлена юзеру " + user.getName() + " " + user.getLast_name();
    }
}

