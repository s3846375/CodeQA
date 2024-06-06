package test;

import codeQA.DatabaseManager;
import codeQA.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    Post easyTypePost;
    Post difficultTypePost;


    /**
     * Create a valid easy type and difficult type post with no duplicate post ID.
     * **/
    @BeforeEach
    void setUp() {
        ArrayList<Post> postHistory = DatabaseManager.getPostHistoryFromDB();
        int postID1 = postHistory.size() + 1;
        int postID2 = postHistory.size() + 2;
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        String postBodyCharacterCount305 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star. Wake up where the clouds are far behind me. Where trouble melts like lemon drops.";

        easyTypePost = new Post(postID1, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        easyTypePost.addPost();

        difficultTypePost = new Post(postID2, "Hello12345", postBodyCharacterCount305, tags, "Difficult", "Highly Needed");
        difficultTypePost.addPost();
    }

    /**
     * Test adding comment to a post with valid word count
     **/
    @Test
    void testAddComment_case1() {
        assertTrue(easyTypePost.addComment("Hello world test 123"));
        assertTrue(difficultTypePost.addComment("Hello world test 123 for Master IT Software Engineering"));
    }

    /**
     * Test adding comment to a post with invalid word count
     **/
    @Test
    void testAddComment_case2() {
        assertFalse(easyTypePost.addComment("Hello world test"));
        assertFalse(difficultTypePost.addComment("Hello world test 123 for Master IT Software Engineering 2024 S2"));

    }


    /**
     * Test adding comment to a post with valid text format
     **/
    @Test
    void testAddComment_case3() {
        assertTrue(easyTypePost.addComment("H@#$%! world test 123"));
        assertTrue(difficultTypePost.addComment("WORLD hello test 123 @#$%!"));
    }

    /**
     * Test adding comment to a post with invalid text format
     **/
    @Test
    void testAddComment_case4() {
        assertFalse(easyTypePost.addComment("hello world test 123"));
        assertFalse(difficultTypePost.addComment("@hello world test 123"));
    }

    /**
     * Test adding comment to a post with valid comment count
     **/
    @Test
    void testAddComment_case5() {

        easyTypePost.addComment("Easy type post Comment1");
        easyTypePost.addComment("Easy type post Comment2");
        assertTrue(easyTypePost.addComment("Easy type post Comment3"));

        difficultTypePost.addComment("Difficult type post Comment1");
        difficultTypePost.addComment("Difficult type post Comment2");
        difficultTypePost.addComment("Difficult type post Comment3");
        difficultTypePost.addComment("Difficult type post Comment4");
        assertTrue(difficultTypePost.addComment("Difficult type post Comment5"));
    }

    /**
     * Test adding comment to a post with invalid comment count
     **/
    @Test
    void testAddComment_case6() {

        easyTypePost.addComment("Easy type post Comment1");
        easyTypePost.addComment("Easy type post Comment2");
        easyTypePost.addComment("Easy type post Comment3");
        assertFalse(easyTypePost.addComment("Easy type post Comment4"));

        difficultTypePost.addComment("Difficult type post Comment1");
        difficultTypePost.addComment("Difficult type post Comment2");
        difficultTypePost.addComment("Difficult type post Comment3");
        difficultTypePost.addComment("Difficult type post Comment4");
        difficultTypePost.addComment("Difficult type post Comment5");
        assertFalse(difficultTypePost.addComment("Difficult type post Comment6"));

    }

}