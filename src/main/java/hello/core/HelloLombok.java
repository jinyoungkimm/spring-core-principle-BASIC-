package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // lombok
@Setter // lombok
@ToString // lombok
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(11);
        int age = helloLombok.getAge();
        System.out.println("age = " + age);

        helloLombok.setName("jyk");
        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}