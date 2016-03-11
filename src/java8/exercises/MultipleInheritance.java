package java8.exercises;

public class MultipleInheritance implements Kot,Dog {

    public static void main(String[] args) {
        Kot kot = new Kot(){};
        Dog dog  = new Dog() {};
        

        System.out.println(kot.eatKot());
        System.out.println(dog.eatDog());

    }
    
}

interface Kot {
    default String eatKot (){
        return "Kotik already has eat";
    }
}

interface Dog {
    default String eatDog (){
        return "Dog already has eat";
    }
}