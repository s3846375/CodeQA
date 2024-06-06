package codeQA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseManager {

    /**
     * Save post to file "post.txt"
     **/
    public static void savePostToDB(Post post) {
        try {
            FileWriter writer = new FileWriter("post.txt", true);
            writer.write(post.postID + "\n");
            writer.write(post.postTitle + "\n");
            writer.write(post.postBody + "\n");
            writer.write(post.type + "\n");
            writer.write(post.status + "\n");
            // Saving the tags
            for (int i = 0; i < post.postTags.length; i++) {
                writer.write(post.postTags[i]);
                if (i < post.postTags.length - 1) { // Add comma if it's not the last tag
                    writer.write(",");
                }
            }
            writer.write("\n\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Save file failed.");
        }
    }


    /**
     * Save post comment to file "comment.txt"
     **/
    public static void saveCommentToDB(int postID, String comment) {
        try {
            FileWriter writer = new FileWriter("comment.txt", true);
            writer.write(postID + "\n");
            writer.write(comment + "\n");
            writer.write("\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Save file failed.");
        }
    }

    /**
     * Retrieve all posts from file "post.txt"
     **/
    public static ArrayList<Post> getPostHistoryFromDB() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("post.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                int postID = Integer.parseInt(line);
                String postTitle = br.readLine();
                String postBody = br.readLine();
                String type = br.readLine();
                String status = br.readLine();

                // Read tags (comma separated)
                String tagLine = br.readLine();
                String[] tags = tagLine.split(",");

                // Create a Post object and add it to the list
                Post post = new Post(postID, postTitle, postBody, tags, type, status);
                posts.add(post);

                // Skip the empty line after each post
                br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Import file failed.");
        }

        return posts;
    }

    /**
     * Retrieve comments that are from the specific post ID "comment.txt"
     **/
    public static ArrayList<String> getCommentFromDB(int postID) {
        ArrayList<String> comments = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("comment.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                int id = Integer.parseInt(line);
                String comment = br.readLine();
                // Check if the ID matches the provided postID
                if (id == postID) {
                    comments.add(comment);
                }
                // Skip the empty line after each post
                br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Import file failed.");
        }
        return comments;
    }

}