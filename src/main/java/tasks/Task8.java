package tasks;

import common.Person;
import java.util.Collection;
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
    return new HashSet<>(getNames(persons));
  }

  public static String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getSecondName())
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
        (p1, p2) -> p1));
  }

  public static boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    if(persons2 instanceof HashSet persons2AsSet) {
      return persons1.stream()
        .anyMatch(persons2AsSet::contains);
    } else if (persons1 instanceof HashSet persons1AsSet) {
      return persons2.stream()
        .anyMatch(persons1AsSet::contains);
    } else {
      return persons1.stream()
        .anyMatch(new HashSet<>(persons2)::contains);
    }
  }

  public static long countEven(Stream<Integer> numbers) {
    return numbers
      .filter(num -> num % 2 == 0)
      .count();
  }
}
