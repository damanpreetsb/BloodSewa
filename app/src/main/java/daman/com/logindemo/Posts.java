package daman.com.logindemo;

/**
 * Created by DELL on 02-Jun-16.
 */
public class Posts {
    private String email;
    private String name;
    private String bloodgroup;
    private String area;
    private String content;
    public Posts() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public String setEmail(String email) {
        this.email= email;
        return email;
    } public String setName(String name) {
        this.name= name;
        return name;
    }
    public String setBloodGroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
        return bloodgroup;
    }
    public String setArea(String area){
        this.area = area;
        return area;
    }
    public String setContent(String content){
        this.content = content;
        return content;
    }
    public String getEmail() {
        return email;
    }
    public String getBloodGroup() {
        return bloodgroup;
    }
    public String getArea(){
        return area;
    }
    public String getContent(){
        return content;
    }
    public String getName() {
        return name;
    }
}