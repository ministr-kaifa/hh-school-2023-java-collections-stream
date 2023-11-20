package tasks;

import common.Person;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  public static List<String> getNames(Collection<Person> persons) {
    return persons.stream()
      .skip(1) //первая персона фальшивая
      .map(Person::getFirstName)
      .collect(Collectors.toList());
  }

  public static Set<String> getDifferentNames(Collection<Person> persons) {
    return getNames(persons).stream()
      .distinct()
      .collect(Collectors.toSet());
  }

  public static String convertPersonToString(Person person) {
    return List.of(person.getSecondName(), person.getFirstName(), person.getSecondName()).stream()
      .filter(Objects::nonNull)
      .collect(Collectors.joining(" "));
  }

  /**
   * @return Map where key is person id and value is person name
   */
  public static Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
      .collect(Collectors.toMap(
        Person::getId,
        Task8::convertPersonToString, 
        (p1, p2) -> p1, 
        HashMap::new));
  }

  public static boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    var persons2AsSet = new HashSet<>(persons2);
    return persons1.stream()
      .anyMatch(persons2AsSet::contains);
  }

  public static long countEven(Stream<Integer> numbers) {
    return numbers
      .filter(num -> num % 2 == 0)
      .count();
  }
}
