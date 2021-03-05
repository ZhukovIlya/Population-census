import java.util.*;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // Количесвто несовешенолетних
        int minors = (int) persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
       System.out.println("В Лондоне " + minors + " несовешенолетних");

        //Список призывников
        List<String> conscripts = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() <= 27)
                .map(p -> p.getFamily())
                .collect(Collectors.toList());
       // System.out.println("Список призывников" + conscripts);
        //список потенциально работоспособных людей
        List<Person> workable = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() <= 60)
                .filter(p -> p.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(p -> p.getFamily()))
                .collect(Collectors.toList());
        //System.out.println("Список потенциально работоспособных людей " + workable);

    }
}
