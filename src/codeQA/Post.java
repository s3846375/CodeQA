package codeQA;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private static final String[] POST_TYPES = {"Very Difficult", "Difficult", "Easy"};
    private static final String[] POST_EMERGENCY = {"Immediately Needed", "Highly Needed", "Ordinary"};

//    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
//    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};

    ArrayList<String> postComments = new ArrayList<>();

    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    public boolean addPost() {
        if (validateTitle(postTitle)
                && validateBody(postBody, postType)
                && validateTags(postTags, postType)
                && validateEmergency(postType, postEmergency)) {
            // If all conditions are met, add the post to the TXT file
            try {
                FileWriter writer = new FileWriter("post.txt", true);
                writer.write("ID: " + postID + "\n");
                writer.write("Title: " + postTitle + "\n");
                writer.write("Body: " + postBody + "\n");
                writer.write("Type: " + postType + "\n");
                writer.write("Emergency: " + postEmergency + "\n");
                writer.write("Tags: ");
                for (String tag : postTags) {
                    writer.write(tag + ", ");
                }
                writer.write("\n\n");
                writer.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            // If any condition fails, return false
            return false;
        }
    }

    /**
     * The title's length is between 10 and 250 characters, inclusive.
     * The title starts with exactly five alphabetic characters (either uppercase or lowercase).
     **/
    private boolean validateTitle(String title) {
        // Condition 1: Title length and format validation
        String pattern = "^[a-zA-Z]{5}.*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(title);
        boolean match = matcher.matches();
        if (title.length() >= 10 && title.length() <= 250 && match) {
            System.out.println("The post title is valid.");
            return true;
        } else
            System.out.println("The post title is invalid.");
        return false;
    }

    private boolean validateBody(String body, String type) {
        // Condition 4: Body length validation for "Very Difficult" and "Difficult" types
        return !(type.equals("Very Difficult") || type.equals("Difficult")) || body.length() >= 300;
    }

    private boolean validateTags(String[] tags, String type) {
        // Condition 3: Tags validation
        if (tags.length < 2 || tags.length > 5) {
            return false;
        }
        for (String tag : tags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }
        // Condition 5: Maximum tag count for "Easy" type
        return !(type.equals("Easy") && tags.length > 3);
    }

    private boolean validateEmergency(String type, String emergency) {
        // Condition 5: Emergency validation based on type
        if (type.equals("Easy")) {
            return !emergency.equals("Immediately Needed") && !emergency.equals("Highly Needed");
        } else {
            return !emergency.equals("Ordinary");
        }
    }

    public boolean addComment(String commentText) {
        // Condition 1: Check if comment text meets length and capitalization criteria
        String[] words = commentText.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return false; // Comment text doesn't meet the conditions
        }

        // Condition 2: Check the maximum number of comments based on post type
        if (postType.equals("Easy") || postEmergency.equals("Ordinary")) {
            if (postComments.size() >= 3) {
                return false; // Maximum comment limit reached for Easy or Ordinary posts
            }
        } else {
            if (postComments.size() >= 5) {
                return false; // Maximum comment limit reached for other posts
            }
        }

        // If conditions met, add the comment to the TXT file
        try (FileWriter writer = new FileWriter("comment.txt", true)) {
            writer.write("ID: " + postID + "\n");
            writer.write("Comment: " + commentText + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error writing to file
        }

        // Add the comment to the post's comments list
        postComments.add(commentText);

        return true; // Comment successfully added
    }


    public boolean addComment2(String commentText) throws IOException {
        if (validateCommentText(commentText) && validateCommentCount()) {
            // If both conditions are met, add the comment to the TXT file and post's comments list
//            try (
                    FileWriter writer = new FileWriter("comment.txt", true);
//            ) {
                writer.write("ID: " + postID + "\n");
                writer.write("Comment: " + commentText + "\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false; // Error writing to file
//            }

            postComments.add(commentText);
            return true; // Comment successfully added
        }
        return false; // Comment text or count validation failed
    }

    private boolean validateCommentText(String commentText) {
        // Check if comment text meets length and capitalization criteria
        String[] words = commentText.split("\\s+");
        return words.length >= 4 && words.length <= 10 && Character.isUpperCase(words[0].charAt(0));
    }

    private boolean validateCommentCount() {
        // Check the maximum number of comments based on post type
        if (postType.equals("Easy") || postEmergency.equals("Ordinary")) {
            return postComments.size() < 3; // Maximum comment limit not reached for Easy or Ordinary posts
        } else {
            return postComments.size() < 5; // Maximum comment limit not reached for other posts
        }
    }







    public static void main(String[] args) {
        Post post = new Post(1, "Valid12345",
                "This is the body of the post for Easy type. can be less than 300 characters. ",
                new String[]{"tag1", "tag2", "tag3"}, "Easy", "Ordinary");

        // Add the post
        if (post.addPost()) {
            System.out.println("Post added successfully.");
        } else {
            System.out.println("Failed to add post.");
        }
        System.out.println(post.postTitle);
        post.validateTitle(post.postTitle);

    }

}
