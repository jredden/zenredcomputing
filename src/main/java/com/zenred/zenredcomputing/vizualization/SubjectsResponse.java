package com.zenred.zenredcomputing.vizualization;

import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("subjectsResponse")
public class SubjectsResponse {

		private String [] subjects;

		public String[] getSubjects() {
			return subjects;
		}

		public void setSubjects(String[] subjects) {
			this.subjects = subjects;
		}

		@Override
		public String toString() {
			return "SubjectsResponse [subjects=" + Arrays.toString(subjects)
					+ "]";
		}
		
		
	
}
