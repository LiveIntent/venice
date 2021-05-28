package com.linkedin.venice.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;


public class CollectionUtils {

  /**
   * A manual implementation of list equality.
   *
   * This is (unfortunately) useful with Avro lists since they do not work reliably.
   * There are cases where a {@link List<T>} coming out of an Avro record will be
   * implemented as a {@link org.apache.avro.generic.GenericData.Array} and other
   * times it will be a java {@link ArrayList}. When this happens, the equality check
   * fails...
   *
   * @return true if both lists have the same items in the same order
   */
  public static <T> boolean listEquals(List<T> list1, List<T> list2) {
    if (list1.size() != list2.size()) {
      return false;
    } else {
      for (int i = 0; i < list2.size(); i++) {
        if (!list1.get(i).equals(list2.get(i))) {
          return false;
        }
      }
    }
    return true;
  }

  public static <T> Collection<T> assertCollectionsNotEmpty(Collection<T> collection) {
    if (collection.isEmpty()) {
      throw new IllegalArgumentException("This collection object cannot be empty.");
    }
    return collection;
  }

  public static List<Float> asUnmodifiableList(float[] array)
  {
    Objects.requireNonNull(array);
    class ResultList extends AbstractList<Float> implements RandomAccess
    {
      @Override
      public Float get(int index)
      {
        return array[index];
      }

      @Override
      public int size()
      {
        return array.length;
      }
    };
    return new ResultList();
  }

  /**
   * A reversed copy of the given list
   *
   * @param <T> The type of the items in the list
   * @param l The list to reverse
   * @return The list, reversed
   */
  public static <T> List<T> reversed(List<T> l) {
    List<T> copy = new ArrayList<T>(l);
    Collections.reverse(copy);
    return copy;
  }

  public static Map<CharSequence, CharSequence> getCharSequenceMapFromStringMap(Map<String, String> stringStringMap) {
    return new HashMap<>(stringStringMap);
  }

  public static Map<String, String> getStringMapFromCharSequenceMap(Map<CharSequence, CharSequence> charSequenceMap) {
    if (charSequenceMap == null) {
      return null;
    }

    Map<String, String> ssMap = new HashMap<>();
    charSequenceMap.forEach((key, value) -> ssMap.put(key.toString(), value.toString()));
    return ssMap;
  }

  public static Map<CharSequence, CharSequence> convertStringMapToCharSequenceMap(Map<String, String> stringMap) {
    Map<CharSequence, CharSequence> res = new HashMap<>();
    stringMap.forEach( (k, v) -> res.put(k, v));
    return res;
  }

  public static Map<String, String> convertCharSequenceMapToStringMap(Map<CharSequence, CharSequence> charSequenceMap) {
    Map<String, String> res = new HashMap<>();
    charSequenceMap.forEach((k, v) -> res.put(k.toString(), v.toString()));
    return res;
  }
}
