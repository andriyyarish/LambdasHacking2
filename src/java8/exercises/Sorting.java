package java8.exercises;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by olexandra on 3/10/16.
 */
public class Sorting {

    /**
     * Ex 1. Sort the List ascending
     */

    @Test
    public void sortListAsc() {

//given
        List<String> list = Arrays.asList("Maria", "Victor", "Pavel", "Alfina", "Sasha");
//when

        List actual = list.stream().sorted().collect(Collectors.toList()); //TODO write your implementation here
//then
        List<String> expected = Arrays.asList("Alfina", "Maria", "Pavel", "Sasha", "Victor");
        assertEquals(expected, actual);
    }

    /**
     * Ex 2. Sort the List descending
     */

    @Test
    public void sortListDesc() {

//given
        List<String> list = Arrays.asList("Maria", "Victor", "Pavel", "Alfina", "Sasha");
//when

        List actual = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()); //TODO write your implementation here
//then
        List<String> expected = Arrays.asList("Victor", "Sasha", "Pavel", "Maria", "Alfina");
        assertEquals(expected, actual);
    }

    /**
     * Ex 3. Sort the list of FamilyMembers first by name and then my age
     */

    @Test
    public void sortListObject() {

//given
        List<FamilyMember> list = Arrays.asList(
                new FamilyMember("Kolia", 3, "grandson"),
                new FamilyMember("Illina Petrovna", 89, "great grandma"),
                new FamilyMember("Illina Petrovna", 50, "grandma"),
                new FamilyMember("Ivan Vasilich", 92, "great grandpa"),
                new FamilyMember("Vitalina Fedorovna", 70, "grandmother"),
                new FamilyMember("Vitalij Valerianovich", 70, "grandfather"));

//when

        Comparator<FamilyMember> comparatorName = (fm1,fm2) -> fm1.getName().compareTo(fm2.getName());
        Comparator<FamilyMember> comparatorAge = (fm1,fm2) -> ((Integer) fm1.getAge()).compareTo(fm2.getAge());
       // Comparator<FamilyMember> compareNameThenAge = comparatorName.thenComparing(comparatorAge);

        List<FamilyMember> actual = list.stream()
                .sorted(Comparator.comparing(FamilyMember::getName).thenComparing(FamilyMember::getAge))
                .collect(Collectors.toList());

// TODO: insert the code which updates list heres
        System.out.println("sorted by name and age");
        actual.stream().forEach(System.out::println);

        List<FamilyMember> expected = Arrays.asList(
                new FamilyMember("Illina Petrovna", 50, "grandma"),
                new FamilyMember("Illina Petrovna", 89, "great grandma"),
                new FamilyMember("Ivan Vasilich", 92, "great grandpa"),
                new FamilyMember("Kolia", 3, "grandson"),
                new FamilyMember("Vitalij Valerianovich", 70, "grandfather"),
                new FamilyMember("Vitalina Fedorovna", 70, "grandmother"));

        assertEquals(expected, actual);
        assertEquals(expected.get(0).toString(), actual.get(0).toString());
        assertEquals(expected.get(1).toString(), actual.get(1).toString());
    }


    class FamilyMember {

        String name;
        int age;
        String role;

        public FamilyMember(String name, int age, String role) {
            this.name = name;
            this.age = age;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getRole() {
            return role;
        }

        @Override
        public String toString() {
            return name +
                    ", age=" + age +
                    ", role=" + role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FamilyMember)) return false;

            FamilyMember that = (FamilyMember) o;

            if (age != that.age) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return role != null ? role.equals(that.role) : that.role == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            result = 31 * result + (role != null ? role.hashCode() : 0);
            return result;
        }
    }
}