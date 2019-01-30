package imad.jrxie.cineapp;

/**
 * Created by jrxie on 2019/1/30.
 */

public class User
{
    private String name;
    private String age;
    public String getName()
    {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + "]";
    }


}
