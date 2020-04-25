package rl.medicine.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AppLogger {

	public static void appDebug(final String data) {
		System.out.println(data);
	}

	public static void hashMapDebug(HashMap<String, Integer> hashMap) {
		final Set<String> keySet = hashMap.keySet();
		final List<String> keySetList = new ArrayList<>(keySet);
		for(int i=0; i<keySetList.size();i++) {
			appDebug("Key Hash Code : "+keySetList.get(i).hashCode());
			appDebug("Key : "+keySetList.get(i));
			appDebug("Value : "+hashMap.get(keySetList.get(i)));
		}
		
	}
}
