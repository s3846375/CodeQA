package codeQA;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    /**
     * Get the current post count in file to avoid duplicate postID created
     * **/

    // valid word count
    @Test
    void testAddComment_case1() {
        ArrayList<Post> postHistory = DatabaseManager.getPostFromDB();
        int postID = postHistory.size() + 1;
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        Post validPost = new Post(postID, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        validPost.addPost();

        String comment1 = "Hello world test 123";
        String comment2 = "Hello world test 123 for Software Engineering 2024 S2";

        assertTrue(validPost.addComment(comment1));
        assertTrue(validPost.addComment(comment2));
    }

    // invalid word count
    @Test
    void testAddComment_case2() {
        ArrayList<Post> postHistory = DatabaseManager.getPostFromDB();
        int postID = postHistory.size() + 1;
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        Post validPost = new Post(postID, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        validPost.addPost();

        String comment1 = "Hello world test";
        String comment2 = "Hello world test 123 for Master IT Software Engineering 2024 S2";

        assertFalse(validPost.addComment(comment1));
        assertFalse(validPost.addComment(comment2));
    }


    // valid text format
    @Test
    void testAddComment_case3() {
        ArrayList<Post> postHistory = DatabaseManager.getPostFromDB();
        int postID = postHistory.size() + 1;
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        Post validPost = new Post(postID, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        validPost.addPost();

        String comment1 = "H@#$%! world test 123";
        String comment2 = "Hello world test 123 @#$%!";

        assertTrue(validPost.addComment(comment1));
        assertTrue(validPost.addComment(comment2));

    }

    //invalid text format
    @Test
    void testAddComment_case4() {
        ArrayList<Post> postHistory = DatabaseManager.getPostFromDB();
        int postID = postHistory.size() + 1;
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        Post validPost = new Post(postID, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        validPost.addPost();

        String comment1 = "hello world test 123";
        String comment2 = "@hello world test 123";

        assertFalse(validPost.addComment(comment1));
        assertFalse(validPost.addComment(comment2));

    }

    //valid comment count
    @Test
    void testAddComment_case5() {
        ArrayList<Post> postHistory = DatabaseManager.getPostFromDB();
        int postID1 = postHistory.size() + 1;
        int postID2 = postHistory.size() + 2;

        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        String postBodyCharacterCount305 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star. Wake up where the clouds are far behind me. Where trouble melts like lemon drops.";

        Post validPost1 = new Post(postID1, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        Post validPost2 = new Post(postID2, "Hello12345", postBodyCharacterCount305, tags, "Difficult", "Highly Needed");
        validPost1.addPost();
        validPost2.addPost();

        validPost1.addComment("Valid easy post Comment1");
        validPost1.addComment("Valid easy post Comment2");

        validPost2.addComment("Valid difficult post Comment1");
        validPost2.addComment("Valid difficult post Comment2");
        validPost2.addComment("Valid difficult post Comment3");
        validPost2.addComment("Valid difficult post Comment4");


        String comment1 = "Hello world test 123";
        String comment2 = "Hello world test 123";

        assertTrue(validPost1.addComment(comment1));
        assertTrue(validPost2.addComment(comment2));
    }

    //invalid comment count
    @Test
    void testAddComment_case6() {
        ArrayList<Post> postHistory = DatabaseManager.getPostFromDB();
        int postID1 = postHistory.size() + 1;
        int postID2 = postHistory.size() + 2;

        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        String postBodyCharacterCount305 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star. Wake up where the clouds are far behind me. Where trouble melts like lemon drops.";

        Post validPost1 = new Post(postID1, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        Post validPost2 = new Post(postID2, "Hello12345", postBodyCharacterCount305, tags, "Difficult", "Highly Needed");
        validPost1.addPost();
        validPost2.addPost();

        validPost1.addComment("Easy valid post Comment1");
        validPost1.addComment("Easy valid post Comment2");
        validPost1.addComment("Easy valid post Comment3");

        validPost2.addComment("Difficult valid post Comment1");
        validPost2.addComment("Difficult valid post Comment2");
        validPost2.addComment("Difficult valid post Comment3");
        validPost2.addComment("Difficult valid post Comment4");
        validPost2.addComment("Difficult valid post Comment5");


        String comment1 = "Hello world test 123";
        String comment2 = "Hello world test 123";

        assertFalse(validPost1.addComment(comment1));
        assertFalse(validPost2.addComment(comment2));

    }

}