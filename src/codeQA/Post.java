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
    private String type;
    private String status;

    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};

    ArrayList<String> postComments = new ArrayList<>();

    public Post(int postID, String postTitle, String postBody, String[] postTags, String type, String status) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.type = type;
        this.status = status;
    }

    public boolean addPost() {
        if (validateTitle(postTitle)
                && validateBody(postBody, type)
                && validateTags(postTags, type)
                && validateType(type)
                && validateEmergency(type, status)) {
            // If all conditions are met, add the post to the TXT file
            try {
                FileWriter writer = new FileWriter("post.txt", true);
                writer.write("ID: " + postID + "\n");
                writer.write("Title: " + postTitle + "\n");
                writer.write("Body: " + postBody + "\n");
                writer.write("Type: " + type + "\n");
                writer.write("Emergency: " + status + "\n");
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
        String pattern = "^[a-zA-Z]{5}.*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(title);
        if (title.length() >= 10 && title.length() <= 250 && matcher.matches()) {
            System.out.println("The post title is valid.");
            return true;
        } else
            System.out.println("The post title is invalid.");
        return false;
    }

    /**
     * Check if the post body character count is >= 300 for post types "Difficult" and "Very Difficult".
     * Other types of post should have a minimum of 250 characters.
     **/
    private boolean validateBody(String body, String type) {
        if ((type.equals("Very Difficult") || type.equals("Difficult")) && body.length() >= 300) {
            System.out.println("Type is Difficult and Very Difficult, The post body is valid.");
            return true;
        } else if (body.length() >= 250) {
            System.out.println("The post body is valid.");
            return true;
        } else
            System.out.println("The post body is invalid.");
        return false;
    }

    /**
     * Check if the post type exist in the given array of types
     **/
    public boolean validateType(String type) {
        for (String validType : postTypes) {
            if (validType.equals(type)) {
                return true;
            }
        }
        System.out.println("The post Type does not exist.");
        return false;
    }

    public boolean containsUpperCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if each of the post Tag character count is >= 2 && <= 10.
     * Check if each of the post Tag does not contain uppercase leters.
     * The number of tags for all posts should be >=2
     * Easy type posts number of tags should >=2 && <=3.
     **/
    private boolean validateTags(String[] tags, String type) {
        // go through each tag to check character count and case criteria
        for (String tag : tags) {
            if (!(tag.length() >= 2 && tag.length() <= 10) || containsUpperCase(tag)) {
                System.out.println("Tag length not in criteria or contains uppercase letters.");
                return false;
            }
        }
        int numberOfTags = postTags.length;
        if (type.equals("Easy") && numberOfTags > 3) {
            System.out.println("Type is Easy, The number of tags is invalid.");
            return false;
        } else if (numberOfTags < 2 || numberOfTags > 5) {
            System.out.println("The number of tags is invalid.");
            return false;
        }
        return true;
    }

    private boolean checkStatusExist(String status) {
        for (String emergency : postEmergency) {
            if (emergency.equals(status)) {
                return false;
            }
        }
        System.out.println("The post emergency status does not exist.");
        return false;
    }

    private boolean validateEmergency(String type, String status) {
        if (!checkStatusExist(status) && type.equals("Easy") && (status.equals("Immediately Needed") || !status.equals("Highly Needed")))
            return false;
        else if (!checkStatusExist(status) && type.equals("Easy") && status.equals("Ordinary"))
            return false;
        return true;
    }


    public boolean addComment2(String commentText) {
        // Condition 1: Check if comment text meets length and capitalization criteria
        String[] words = commentText.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return false; // Comment text doesn't meet the conditions
        }

        // Condition 2: Check the maximum number of comments based on post type
        if (type.equals("Easy") || status.equals("Ordinary")) {
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


    public boolean addComment(String commentText) throws IOException {
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
        if (type.equals("Easy") || status.equals("Ordinary")) {
            return postComments.size() < 3; // Maximum comment limit not reached for Easy or Ordinary posts
        } else {
            return postComments.size() < 5; // Maximum comment limit not reached for other posts
        }
    }


    public static void main(String[] args) {
        Post post = new Post(1, "Valid12345",
                "This123 ",
                new String[]{"tag1", "tag1", "tag2"}, "Difficult", "Highly Needed");

        post.validateEmergency(post.type, post.status);
//        System.out.println(post.validateTags(post.postTags, post.type));
        System.out.println(post.validateEmergency(post.type, post.status));

//        private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
//        private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
//

//        if (post.addPost()) {
//            System.out.println("Post added successfully.");
//        } else {
//            System.out.println("Failed to add post.");
//        }
//

    }

}
