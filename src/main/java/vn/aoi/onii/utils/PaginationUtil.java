package vn.aoi.onii.utils;

import java.util.List;

public class PaginationUtil<T> {

    public List<T> paginate(List<T> list, int page, int size) {
        int from = (page - 1) * size;
        int to = Math.min(from + size, list.size());

        if (from >= list.size()) return List.of();
        return list.subList(from, to);
    }
} 
