package com.zenredcomputing.util;

import java.util.ArrayList;
import java.util.List;

public class ListOperations {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List reverseList( List sourceList){
		List targetList = new ArrayList();
		for(int idex = sourceList.size(); idex != 0; idex--){
			targetList.add(sourceList.get(idex-1));
		}
		return targetList;
	}

}
