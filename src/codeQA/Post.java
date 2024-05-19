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
        if (validTitle(postTitle)
                && validBody(postBody, type)
                && validTags(postTags, type)
                && validType(type)
                && validStatus(type, status)) {
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
    private boolean validTitle(String title) {
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
    private boolean validBody(String body, String type) {
        if ((type.equals("Very Difficult") || type.equals("Difficult")) && body.length() < 30) {
            System.out.println("Type is Difficult or Very Difficult, The post body character count is invalid.");
            return false;
        } else if (body.length() < 25) {
            System.out.println("The post body is invalid.");
            return false;
        } else
            System.out.println("The post body is valid.");
        return true;
    }

    /**
     * Check if the post type exist in the given array of types
     **/
    public boolean validType(String type) {
        for (String validType : postTypes) {
            if (validType.equals(type)) {
                System.out.println("The post Type exist.");
                return true;
            }
        }
        System.out.println("The post Type does not exist.");
        return false;
    }

    public boolean containsUpperCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                System.out.println("The string has upper case.");
                return true;
            }
        }
        System.out.println("The string has no upper case.");
        return false;
    }

    /**
     * Check if each of the post Tag character count is >= 2 && <= 10.
     * Check if each of the post Tag does not contain uppercase leters.
     * The number of tags for all posts should be >=2
     * Easy type posts number of tags should >=2 && <=3.
     **/
    private boolean validTags(String[] tags, String type) {
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
        System.out.println("The post tags is valid.");
        return true;
    }

    private boolean checkStatusExist(String status) {
        for (String emergency : postEmergency) {
            if (emergency.equals(status)) {
                System.out.println("The post status exist.");
                return true;
            }
        }
        System.out.println("The post status does not exist.");
        return false;
    }

    private boolean validStatus(String type, String status) {
        if (checkStatusExist(status) && type.equals("Easy") && (status.equals("Immediately Needed") || status.equals("Highly Needed"))) {
            System.out.println("Easy post type status cannot be Immediately Needed or Highly Needed.");
            return false;
        } else if (checkStatusExist(status) && (type.equals("Difficult") || type.equals("Very Difficult")) && status.equals("Ordinary")) {
            System.out.println("Difficult and Very Difficult post type status cannot be Ordinary.");
            return false;
        }
        System.out.println("The post status is valid.");
        return true;
    }
//////////////////////////////////////////////////////////////////////////////////////////

    public boolean addComment(String commentText) {
        if (validWordCount(commentText) && validCommentCount()) {
            postComments.add(commentText);
            try {
                FileWriter writer = new FileWriter("comment.txt", true);
                writer.write("ID: " + postID + "\n");
                writer.write("Comment: " + commentText + "\n");
                System.out.println("add Comment success.");
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


    private boolean validWordCount(String commentText) {
        // removes leading and trailing whitespace
        // "\\s+" matches one or more consecutive whitespace characters
        // splits the string into an array of words
        String[] words = commentText.trim().split("\\s+");
        if (words.length >= 4 && words.length <= 10 && Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("Comment word count is valid.");
            return true;
        } else
            System.out.println("Comment word count or first character is not valid.");
        return false;
    }

    private boolean validCommentCount() {
        if ((type.equals("Easy") || status.equals("Ordinary")) && postComments.size() == 3) {
            System.out.println("Easy post type comment count reached max 3");
            return false;
        } else if (postComments.size() == 5) {
            System.out.println("Comment count reached max 5");
            return false;
        }
        System.out.println("Comment valid");
        return true;
    }


    public static void main(String[] args) {
        String[] tags = new String[]{"tag1", "tag2", "tag3"};
        Post post = new Post(1, "Hello123", "post body characters < 300.", tags, "Easy", "Ordinary");
        System.out.println(post.postComments.size());

        post.addComment("Test 123 abc qwe");

//        System.out.println(post.postBody.length());
//        System.out.println(post.validStatus(post.type, post.status));

//        post.validateEmergency(post.type, post.status);
//        System.out.println(post.validateTags(post.postTags, post.type));
//        System.out.println(post.validateEmergency(post.type, post.status));

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
