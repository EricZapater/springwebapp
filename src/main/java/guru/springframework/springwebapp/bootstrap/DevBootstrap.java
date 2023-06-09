package guru.springframework.springwebapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.springwebapp.model.Author;
import guru.springframework.springwebapp.model.Book;
import guru.springframework.springwebapp.model.Publisher;
import guru.springframework.springwebapp.repositories.AuthorRepository;
import guru.springframework.springwebapp.repositories.BookRepository;
import guru.springframework.springwebapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }
    
    private void initData(){
        Publisher harper = new Publisher("Harper Collins", "London");
        publisherRepository.save(harper);

        Author eric = new Author("Eric", "Evans");
        
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        

        Publisher worx = new Publisher("Worx", "NY");
        publisherRepository.save(worx);

        Author rod = new Author("Rod", "Johnson");        
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        
    }
}

