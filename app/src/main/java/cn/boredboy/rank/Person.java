package cn.boredboy.rank;

/**
 * 声明Person类，用于存储个人数据
 */

public class Person {
    private String name;
    private String sex;
    private String score;

//    可传参的构造方法
    public Person(String name, String sex, String score){
        this.name = name;
        this.sex = sex;
        this.score = score;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
