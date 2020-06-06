package com.helpit.posts;

import com.helpit.posts.repositories.CommentRepository;
import com.helpit.posts.repositories.FoundationRepository;
import com.helpit.posts.repositories.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class FoundationApplicationTests
{

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FoundationRepository foundationRepository;

    @Test
    void contextLoads()
    {

    }

//    @Test
//    void addAndDeleteUserFromDataBase()
//    {
//        User newest = user_repository.save(new User("Adrian"));
//
//        assertEquals(3, user_repository.count());  //3 bo juz wczesniej wrzucam 2 urzytkownikow w bootstrapData
//
//        user_repository.delete(newest);
//
//        assertEquals(2, user_repository.count());
//    }
//
//    @Test
//    void addAndDeleteCommentFromDataBase()
//    {
//        Comment newest = comment_repository.save(new Comment("Mama ma mamcie"));
//
//        assertEquals(4, comment_repository.count());  //4 bo juz wczesniej wrzucam 3 komentarze w bootstrapData
//
//        comment_repository.delete(newest);
//
//        assertEquals(3, comment_repository.count());
//    }
//
//    @Test
//    void addAndDeleteFoundationFromDataBase()
//    {
//        Foundation newest = foundation_repository.save(new Foundation("Fundacja Studencka"));
//
//        assertEquals(3, foundation_repository.count());  //3 bo juz wczesniej wrzucam 2 komentarze w bootstrapData
//
//        foundation_repository.delete(newest);
//
//        assertEquals(2, foundation_repository.count());
//    }

}
