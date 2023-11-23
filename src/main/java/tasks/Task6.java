package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Map<Person, Set<Area>> convertPersonAreas(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    var areasMap = areas.stream()
      .collect(Collectors.toMap(
        Area::getId,
        Function.identity()
      ));

    var personsMap = persons.stream()
      .collect(Collectors.toMap(
        Person::getId,
        Function.identity()
      ));

    var result = new HashMap<Person, Set<Area>>();
    personAreaIds.entrySet().forEach(personEntry -> result.put(
      personsMap.get(personEntry.getKey()),
      personEntry.getValue().stream()
        .map(areasMap::get)
        .collect(Collectors.toSet())
    ));
    return result;
  }

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    
    return convertPersonAreas(persons, personAreaIds, areas).entrySet().stream()
      .flatMap(personEntry -> personEntry.getValue().stream()
        .map(area -> personEntry.getKey().getFirstName() + " - " + area.getName()))
      .collect(Collectors.toSet());

  }
}
