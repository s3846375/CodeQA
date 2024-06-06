package test;

import codeQA.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    /**
     * Test adding post to file with valid post title
     **/
    @Test
    void testAddPost_case1() {
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        Post validPost1 = new Post(1, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Ordinary");
        Post validPost2 = new Post(2, "abcde!@#$%", postBodyCharacterCount250, tags, "Easy", "Ordinary");

        assertTrue(validPost1.addPost());
        assertTrue(validPost2.addPost());
    }

    /**
     * Test adding post to file with invalid post body
     **/
    @Test
    void testAddPost_case2() {
        String[] tags = new String[]{"tag1", "tag2", "tag3"};

        String postBodyCharacterCount265 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds are far behind me";
        Post invalidPost1 = new Post(1, "Hello12345", postBodyCharacterCount265, tags, "Very Difficult", "Highly Needed");

        String postBodyCharacterCount225 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star.";
        Post invalidPost2 = new Post(2, "Hello12345", postBodyCharacterCount225, tags, "Easy", "Ordinary");

        assertFalse(invalidPost1.addPost());
        assertFalse(invalidPost2.addPost());
    }


    /**
     * Test adding post to file with valid post tags
     **/
    @Test
    void testAddPost_case3() {
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        String[] tags1 = new String[]{"tag1", "tag2"};
        Post validPost1 = new Post(3, "Hello12345", postBodyCharacterCount250, tags1, "Easy", "Ordinary");

        String postBodyCharacterCount305 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star. Wake up where the clouds are far behind me. Where trouble melts like lemon drops.";
        String[] tags2 = new String[]{"tg", "tag123", "tag1234567", "tag4"};
        Post validPost2 = new Post(4, "Hello12345", postBodyCharacterCount305, tags2, "Difficult", "Highly Needed");

        assertTrue(validPost1.addPost());
        assertTrue(validPost2.addPost());

    }

    /**
     * Test adding post to file with invalid post tags
     **/
    @Test
    void testAddPost_case4() {
        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        String[] tags1 = new String[]{"tag1", "Tag123", "TAG"};
        Post invalidPost1 = new Post(1, "Hello12345", postBodyCharacterCount250, tags1, "Easy", "Ordinary");

        String[] tags2 = new String[]{"tag123", "tag12345678"};
        Post invalidPost2 = new Post(2, "Hello12345", postBodyCharacterCount250, tags2, "Easy", "Ordinary");

        assertFalse(invalidPost1.addPost());
        assertFalse(invalidPost2.addPost());

    }


    /**
     * Test adding post to file with valid post status
     **/
    @Test
    void testAddPost_case5() {
        String postBodyCharacterCount305 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star. Wake up where the clouds are far behind me. Where trouble melts like lemon drops.";
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        Post validPost1 = new Post(5, "Hello12345", postBodyCharacterCount305, tags, "Difficult", "Immediately Needed");
        Post validPost2 = new Post(6, "Hello12345", postBodyCharacterCount305, tags, "Very Difficult", "Highly Needed");
        assertTrue(validPost1.addPost());
        assertTrue(validPost2.addPost());
    }

    /**
     * Test adding post to file with invalid post status
     **/
    @Test
    void testAddPost_case6() {
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        String postBodyCharacterCount305 = "Somewhere over the rainbow Way up high And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star. Wake up where the clouds are far behind me. Where trouble melts like lemon drops.";
        Post invalidPost1 = new Post(1, "Hello12345", postBodyCharacterCount305, tags, "Difficult", "Ordinary");

        String postBodyCharacterCount250 = "Somewhere over the rainbow Way up high. And the dreams that you dream of Once in a lullaby. Somewhere over the rainbow Bluebirds fly. And the dreams that you dream of Dreams really do come true. Someday I'll wish upon a star Wake up where the clouds.";
        Post invalidPost2 = new Post(2, "Hello12345", postBodyCharacterCount250, tags, "Easy", "Immediately Needed");
        assertFalse(invalidPost1.addPost());
        assertFalse(invalidPost2.addPost());
    }

}