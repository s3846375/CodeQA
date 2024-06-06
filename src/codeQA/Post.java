package codeQA;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post {
    protected int postID;
    protected String postTitle;
    protected String postBody;
    protected String[] postTags;
    protected String type;
    protected String status;

    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList<String> postComments;

    public Post(int postID, String postTitle, String postBody, String[] postTags, String type, String status) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.type = type;
        this.status = status;
        this.postComments = new ArrayList<>();
    }

    /**
     * Check all validations for post and add to text file if valid.
     * **/
    public boolean addPost() {
        if (validTitle(postTitle)
                && validBody(postBody, type)
                && validTags(postTags, type)
                && validType(type)
                && validStatus(type, status)) {

            DatabaseManager.savePostToDB(this);
            System.out.println("Post added successfully.");
            return true;
        } else {
            System.out.println("Post added fail.");
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
        if ((type.equals("Very Difficult") || type.equals("Difficult")) && body.length() < 300) {
            System.out.println("Type is Difficult or Very Difficult, The post body character count is invalid.");
            return false;
        } else if (body.length() < 250) {
            System.out.println("The post body is invalid.");
            return false;
        } else
            System.out.println("The post body is valid.");
        return true;
    }

    /**
     * Check if the post type exist in the given array of types
     **/
    private boolean validType(String type) {
        for (String validType : postTypes) {
            if (validType.equals(type)) {
                System.out.println("The post Type exist.");
                return true;
            }
        }
        System.out.println("The post Type does not exist.");
        return false;
    }

    /**
     * Check if any post Tag contain uppercase letters.
     **/
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
     * If each post tag character count >=2 && <=10 and has no uppercase,
     * will then check the number of tag counts for all posts should be >=2.
     * Easy type posts number of tags should >=2 && <=3.
     **/
    private boolean validTags(String[] tags, String type) {
        for (String tag : tags) {
            if (!(tag.length() >= 2 && tag.length() <= 10) || containsUpperCase(tag)) {
                System.out.println("Tag length not in criteria or contains uppercase letters.");
                return false;
            }
        }
        int numberOfTags = tags.length;
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

    /**
     * Check if the post status exist in the given array of status (postEmergency)
     **/
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

    /**
     * Check post status based on post type.
     **/
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

    // check all validations for comment and add to text file "comment.txt" if valid.
    public boolean addComment(String commentText) {
        if (validText(commentText) && validCommentCount()) {
            DatabaseManager.saveCommentToDB(this.postID, commentText);
            System.out.println("Comment added successfully.");
            return true;
        } else {
            System.out.println("Comment added fail.");
            return false;
        }
    }


    /**
     * Split the comment string into an array of words by finding whitespace between characters.
     * Use array length to verify the word count >=4 and <=10.
     **/
    private boolean validText(String commentText) {
        String[] words = commentText.trim().split("\\s+");
        if (words.length >= 4 && words.length <= 10 && Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("Comment text is valid.");
            return true;
        } else
            System.out.println("Comment word count or first character is not valid.");
        return false;
    }

    private boolean validCommentCount() {
        postComments = DatabaseManager.getCommentFromDB(postID);
        if ((type.equals("Easy") || status.equals("Ordinary")) && postComments.size() >= 3) {
            System.out.println("Easy post type comment count reached max 3");
            return false;
        } else if (postComments.size() >= 5) {
            System.out.println("Comment count reached max 5");
            return false;
        }
        System.out.println("Comment count valid");
        return true;
    }

}
