package com.demo.data.model;

import java.util.List;

/**
 * Created by Beka on 8/3/16.
 */
public interface Mapper<R, V> {
    R map(V v);

    List<R> map(List<V> v);
}
