package common.basic.htmlFetcher;

import java.util.HashMap;
import java.util.Map;

public class MaxSelector<T> {
	Map<T, Integer> mapCount = new HashMap<T, Integer>();

	public void put(T t, int value) {
		mapCount.put(t, value);
	}

	public void increase(T t) {
		if(mapCount.containsKey(t))
			mapCount.put(t, mapCount.get(t) + 1);
		else
			mapCount.put(t, 1);
	}
	
	
	public T getMax()
	{
    	T max = null;
    	int count = 0;
    	for (T t : mapCount.keySet()) {
    		if(count < mapCount.get(t))
    		{
    			count = mapCount.get(t);
    			max = t;
    		}
		}
    	
    	return max;
	}
}
