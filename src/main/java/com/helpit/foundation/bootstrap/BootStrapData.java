package com.helpit.foundation.bootstrap;

import com.helpit.foundation.model.Comment;
import com.helpit.foundation.model.Foundation;
import com.helpit.foundation.model.User;
import com.helpit.foundation.repositories.CommentRepository;
import com.helpit.foundation.repositories.FoundationRepository;
import com.helpit.foundation.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner
{
    private final CommentRepository comment_repository;
    private final UserRepository user_repository;
    private final FoundationRepository foundation_repository;

    public BootStrapData(CommentRepository comment_repository, UserRepository user_repository, FoundationRepository foundation_repository)
    {
        this.comment_repository = comment_repository;
        this.user_repository = user_repository;
        this.foundation_repository = foundation_repository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        User u1 = new User();
        User u2 = new User();
        u1.setLogin("adrian");
        u2.setLogin("szymon");
        user_repository.save(u1);
        user_repository.save(u2);

        Comment c1 = new Comment();
        Comment c2 = new Comment();
        Comment c3 = new Comment();
        c1.setContent("Jestem pierwszy");
        c2.setContent("Jestem drugi");
        c3.setContent("Jestem trzeci");
        comment_repository.save(c1);
        comment_repository.save(c2);
        comment_repository.save(c3);

        Foundation f1 = new Foundation();
        Foundation f2 = new Foundation();
        f1.setName("helpIT");
        f2.setName("killIT");
        foundation_repository.save(f1);
        foundation_repository.save(f2);

        u1.getComments().add(c1);
        u1.getComments().add(c2);
        u2.getComments().add(c3);

        f1.getComment().add(c1);
        f1.getComment().add(c3);
        f2.getComment().add(c2);

        c1.setAuthor(u1);
        c2.setAuthor(u1);
        c3.setAuthor(u2);

        c1.setFoundation(f1);
        c2.setFoundation(f2);
        c3.setFoundation(f1);


        comment_repository.save(c1);
        comment_repository.save(c2);
        comment_repository.save(c3);

        user_repository.save(u1);
        user_repository.save(u2);

        foundation_repository.save(f1);
        foundation_repository.save(f2);





        //System.out.println("Repo: u : c : f -- " + user_repository.count() + " : " + comment_repository.count() + " : " + foundation_repository.count());

    }
}
