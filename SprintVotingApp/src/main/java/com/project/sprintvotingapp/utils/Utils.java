package com.project.sprintvotingapp.utils;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.entity.Votes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static void getResult(List listOfVotes){
        final Map<String, Integer> counts = new HashMap<>();
        for(Object vote:listOfVotes){


        }


    }
    public static  class Counter<T> {
        final Map<T, Integer> counts = new HashMap<>();
        public void add(T t) {
            counts.merge(t, 1, Integer::sum);
        }
        public int count(T t) {
            return counts.getOrDefault(t, 0);
        }
    }
}


