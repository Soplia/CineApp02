/**
 * @file User
 * @author jrxie
 * @date 2019/1/30 10:23 PM
 * @description A container for users
*/

package imad.jrxie.cineapp;

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
