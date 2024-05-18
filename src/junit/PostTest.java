package junit;

import codeQA.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void addPost() {

        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        Post post = new Post(1, "Valid12345",
                "This is the body of the post for Easy type. can be less than 300 characters. ",
                tags, "Easy", "Ordinary");
        assertEquals(true, post.addPost());
    }

    @Test
    void addComment() {
    }
}