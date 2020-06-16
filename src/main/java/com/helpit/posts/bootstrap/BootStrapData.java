//package com.helpit.posts.bootstrap;
//
//import com.helpit.model.Foundation;
//import com.helpit.model.Volunteer;
//import com.helpit.posts.model.*;
//import com.helpit.posts.repositories.*;
//import com.helpit.repositories.FoundationRepository;
//import com.helpit.repositories.VolunteerRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BootStrapData implements CommandLineRunner
//{
//    private final CommentRepository commentRepository;
//    private final VolunteerRepository volunteerRepository;
//    private final FoundationRepository foundationRepository;
//    private final PostRepository postRepository;
//    private final CommentUnderPostRepository commentUnderPostRepository;
//
//    public BootStrapData(CommentRepository commentRepository, VolunteerRepository volunteerRepository, FoundationRepository foundationRepository, PostRepository postRepository, CommentUnderPostRepository commentUnderPostRepository) {
//        this.commentRepository = commentRepository;
//        this.volunteerRepository = volunteerRepository;
//        this.foundationRepository = foundationRepository;
//        this.postRepository = postRepository;
//        this.commentUnderPostRepository = commentUnderPostRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception
//    {
//        LoadData();
//    }
//
//    private void LoadData() {
//        Volunteer u1 = new Volunteer();
//        Volunteer u2 = new Volunteer();
//        u1.setName("adrian");
//        u1.setSurname("jakiela");
//        u2.setName("szymon");
//        u2.setSurname("jakiela");
//        volunteerRepository.save(u1);
//        volunteerRepository.save(u2);
//
//        Comment c1 = new Comment();
//        Comment c2 = new Comment();
//        Comment c3 = new Comment();
//        c1.setContent("Jestem pierwszy");
//        c2.setContent("Jestem drugi");
//        c3.setContent("Jestem trzeci");
//        commentRepository.save(c1);
//        commentRepository.save(c2);
//        commentRepository.save(c3);
//
//        Post p1 = new Post();
//        Post p2 = new Post();
//        Post p3 = new Post();
//        Post p4 = new Post();
//        p1.setContent("Zdjecie naszej nowej podopiecznej");
//        p2.setContent("Czy to ktoś objęty waszą opieką?");
//        p3.setContent("Chciałbym pomóc");
//        p4.setContent("To zdjecie pokazuje ze warto pomagać");
//        postRepository.save(p1);
//        postRepository.save(p2);
//        postRepository.save(p3);
//        postRepository.save(p4);
//
//        CommentUnderPost cp1 = new CommentUnderPost();
//        CommentUnderPost cp2 = new CommentUnderPost();
//        cp1.setContent("Jestem pierwszym komentarzem pod postem");
//        cp1.setPost(p1);
//        cp1.setVolunteer(u1);
//        cp2.setContent("Jestem drugim komentarzem pod postem");
//        cp2.setPost(p2);
//        cp2.setVolunteer(u2);
//        commentUnderPostRepository.save(cp1);
//        commentUnderPostRepository.save(cp2);
//
//
//
//
//        Foundation f1 = new Foundation();
//        Foundation f2 = new Foundation();
//        f1.setName("helpIT");
//        f2.setName("killIT");
//        foundationRepository.save(f1);
//        foundationRepository.save(f2);
//
//        u1.getComments().add(c1);
//        u1.getComments().add(c2);
//        u2.getComments().add(c3);
//
//        u1.getPosts().add(p1);
//        u1.getPosts().add(p2);
//        u1.getPosts().add(p3);
//        u2.getPosts().add(p4);
//
//        f1.getComment().add(c1);
//        f1.getComment().add(c3);
//        f2.getComment().add(c2);
//
//        f1.getPost().add(p1);
//        f2.getPost().add(p2);
//        f2.getPost().add(p3);
//        f2.getPost().add(p4);
//
//        c1.setVolunteer(u1);
//        c2.setVolunteer(u1);
//        c3.setVolunteer(u2);
//
//        c1.setFoundation(f1);
//        c2.setFoundation(f2);
//        c3.setFoundation(f1);
//
//        p1.setVolunteer(u1);
//        p2.setVolunteer(u1);
//        p3.setVolunteer(u1);
//        p4.setVolunteer(u2);
//
//        p1.setFoundation(f1);
//        p2.setFoundation(f2);
//        p3.setFoundation(f2);
//        p4.setFoundation(f2);
//
//        postRepository.save(p1);
//        postRepository.save(p2);
//        postRepository.save(p3);
//        postRepository.save(p4);
//
//
//
//
//        commentRepository.save(c1);
//        commentRepository.save(c2);
//        commentRepository.save(c3);
//
//        volunteerRepository.save(u1);
//        volunteerRepository.save(u2);
//
//        foundationRepository.save(f1);
//        foundationRepository.save(f2);
//
//
//        System.out.println("Bootstrap data loaded");
//
//
//        //System.out.println("Repo: u : c : f -- " + user_repository.count() + " : " + comment_repository.count() + " : " + foundation_repository.count());
//    }
//}
