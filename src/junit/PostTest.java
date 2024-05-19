package junit;

import codeQA.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void addPost() {
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
//        Post validTittle1 = new Post(1, "Hello12345", "post body characters >= 250.", tags, "Easy", "Ordinary");
//        Post validTittle2 = new Post(1, "abcde!@#$%", "post body characters >= 250.", tags, "Easy", "Ordinary");
//        Post invalidTittle1 = new Post(1, "abcdefg", "post body characters >= 250.", tags, "Easy", "Ordinary");
//        Post invalidTittle2 = new Post(1, "123Hello456", "post body characters >= 250.", tags, "Easy", "Ordinary");
//        Post invalidTittle3 = new Post(1, "!@#$%Hello", "post body characters >= 250.", tags, "Easy", "Ordinary");
//        assertEquals(true, validTittle1.addPost());
//        System.out.println("--------------------------------");
//        assertEquals(false, invalidTittle1.addPost());

//        Post validBody1 = new Post(1, "Hello12345", "This post body characters > 300.", tags, "Difficult", "Highly Needed");
//        Post validBody2 = new Post(1, "Hello12345", "The post body characters = 300", tags, "Difficult", "Highly Needed");
//        Post validBody3 = new Post(1, "Hello12345", "postBody characters = 250", tags, "Easy", "Ordinary");
//        Post invalidBody1 = new Post(1, "Hello12345", "post body characters < 300.", tags, "Very Difficult", "Highly Needed");
//        Post invalidBody2 = new Post(1, "Hello12345", "body characters < 250", tags, "Easy", "Ordinary");

//        assertEquals(true, validBody3.addPost());
//        System.out.println("--------------------------------");
//        assertEquals(false, invalidBody2.addPost());
//
//        String[] tags1 = new String[]{"tag1", "tag2"};
//        Post validTag1 = new Post(1, "Hello12345", "post body characters >= 250.", tags1, "Easy", "Ordinary");
//
//        String[] tags2 = new String[]{"tg", "tag123", "tag1234567", "tag4"};
//        Post validTag2 = new Post(1, "Hello12345", "This post body characters > 300.", tags2, "Difficult", "Highly Needed");
//
//        String[] tags3 = new String[]{"tg+", "tag123", "tag1234567"};
//        Post validTag3 = new Post(1, "Hello12345", "post body characters >= 250.", tags3, "Easy", "Ordinary");
//
//        String[] tags4 = new String[]{"tag1", "tag2", "tag3", "tag4"};
//        Post invalidTag1 = new Post(1, "Hello12345", "post body characters >= 250.", tags4, "Easy", "Ordinary");
//
//        String[] tags5 = new String[]{"tag1", "Tag123", "TAG"};
//        Post invalidTag2 = new Post(1, "Hello12345", "post body characters >= 250.", tags5, "Easy", "Ordinary");
//
//        String[] tags6 = new String[]{"tag123", "tag12345678"};
//        Post invalidTag3 = new Post(1, "Hello12345", "post body characters >= 250.", tags6, "Easy", "Ordinary");
//
//        assertEquals(true, validTag3.addPost());
//        System.out.println("--------------------------------");
//        assertEquals(false, invalidTag3.addPost());

//        Post invalidType1 = new Post(1, "Hello12345", "post body characters >= 250.", tags, "Hard", "Ordinary");
//        assertEquals(false, invalidType1.addPost());

        Post validStatus1 = new Post(1, "Hello12345", "This post body characters > 300.", tags, "Difficult", "Immediately Needed");
        Post validStatus2 = new Post(1, "Hello12345", "This post body characters > 300.", tags, "Very Difficult", "Highly Needed");
        Post invalidStatus1 = new Post(1, "Hello12345", "This post body characters > 300.", tags, "Difficult", "Ordinary");
        Post invalidStatus2 = new Post(1, "Hello12345", "post body characters >= 250.", tags, "Easy", "Immediately Needed");
        assertEquals(true, validStatus1.addPost());
        assertEquals(true, validStatus2.addPost());
        assertEquals(false, invalidStatus1.addPost());
        assertEquals(false, invalidStatus2.addPost());




    }
//
//    @Test
//    void addComment() {
//    }
}