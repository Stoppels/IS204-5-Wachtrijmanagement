/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import spring.model.PersonObject;
import spring.model.Statistic;

/**
 *
 * @author Stefan
 */
public class StatController {

	ArrayList<Statistic> list;
	ArrayList<Integer> tempIntVisitors = new ArrayList<>();

	public StatController() {
		this.list = new ArrayList<>();
	}

	public void extractStatistics(ArrayList<PersonObject> list) {
		statDurationsIndividually(list);
		statDurationsInDetail(list);
		statVisitorDetails(list);
	}

	public ArrayList<Statistic> getList() {
		return list;
	}

	private void statDurationsInDetail(ArrayList<PersonObject> personList) {
		int[] data = new int[15];
		String[] labels = new String[15];

		labels[0] = "Minimum duration";
		labels[1] = "Minimum morning";
		labels[2] = "Minimum noon";
		labels[3] = "Minimum afternoon";
		labels[4] = "Minimum evening";
		labels[5] = "Average duration";
		labels[6] = "Average morning";
		labels[7] = "Average noon";
		labels[8] = "Average afternoon";
		labels[9] = "Average evening";
		labels[10] = "Maximum duration";
		labels[11] = "Maximum morning";
		labels[12] = "Maximum noon";
		labels[13] = "Maximum afternoon";
		labels[14] = "Maximum evening";

		int morningDuration = 0;
		int noonDuration = 0;
		int afternoonDuration = 0;
		int eveningDuration = 0;
		int temp[] = Arrays.copyOf(list.get(0).getStatData(), list.get(0).getStatData().length); // Fetch duration per person.
		ArrayList<Integer> morningOrdered = new ArrayList<Integer>();
		ArrayList<Integer> noonOrdered = new ArrayList<Integer>();
		ArrayList<Integer> afternoonOrdered = new ArrayList<Integer>();
		ArrayList<Integer> eveningOrdered = new ArrayList<Integer>();

		for (int i = 0; i < personList.size(); i++) { // Sum and store all visitors' visit duration
			try {
				if (personList.get(i).getEnd().getHour() >= 0 && personList.get(i).getEnd().getHour() < 12) {
					// Morning stats.
					morningOrdered.add(temp[i]); // Store individual durations daypart.
					morningDuration += temp[i]; // Incremement total duration daypart.
				} else if (personList.get(i).getEnd().getHour() >= 12 && personList.get(i).getEnd().getHour() < 14) {
					// Noon stats.
					noonOrdered.add(temp[i]); // Store individual durations daypart.
					noonDuration += temp[i]; // Incremement total duration daypart.
				} else if (personList.get(i).getEnd().getHour() >= 14 && personList.get(i).getEnd().getHour() < 18) {
					// Afternoon stats.
					afternoonOrdered.add(temp[i]); // Store individual durations daypart.
					afternoonDuration += temp[i]; // Incremement total duration daypart.
				} else {
					// Evening stats.
					eveningOrdered.add(temp[i]); // Store individual durations daypart.
					eveningDuration += temp[i]; // Incremement total duration daypart.
				}
			} catch (NullPointerException e) {
				e.getLocalizedMessage();
			}
		}

		Arrays.sort(temp); // Sort temp Array of durations by size.
		if (temp.length > 0) { // Stats for the whole day.
			data[0] = temp[0]; // Stores shortest duration.
			data[10] = temp[temp.length - 1]; // Stores longest duration.
		} else {
			data[0] = data[10] = 0; // If there's no (usable) data, assume zero.
		}

		// Sum and store all visitors' visit duration.
		int totalDuration = morningDuration + noonDuration + afternoonDuration + eveningDuration;
		int totalVisitors = temp.length;
		tempIntVisitors.add(0, temp.length);
		data[5] = totalVisitors > 0 ? totalDuration / totalVisitors : 0;

		// Sort daypart duration data ascending.
		Collections.sort(morningOrdered);
		Collections.sort(noonOrdered);
		Collections.sort(afternoonOrdered);
		Collections.sort(eveningOrdered);

		// Stores the shortest, average and longest durations per daypart.
		try {
			if (morningOrdered.size() > 0) { // Stats for the morning.
				data[1] = morningOrdered.get(0);
				data[6] = morningDuration / morningOrdered.size();
				data[11] = morningOrdered.get(morningOrdered.size() - 1);
				tempIntVisitors.add(1, morningOrdered.size());
			} else { // If there's no (usable) data, assume zero.
				data[1] = data[6] = data[11] = 0;
				tempIntVisitors.add(1, 0);
			}
			if (noonOrdered.size() > 0) { // Stats for noon.
				data[2] = noonOrdered.get(0);
				data[7] = noonDuration / noonOrdered.size();
				data[12] = noonOrdered.get(noonOrdered.size() - 1);
				tempIntVisitors.add(2, noonOrdered.size());
			} else { // If there's no (usable) data, assume zero.
				data[2] = data[7] = data[12] = 0;
				tempIntVisitors.add(2, 0);
			}
			if (afternoonOrdered.size() > 0) { // Stats for the afternoon.
				data[3] = afternoonOrdered.get(0);
				data[8] = afternoonDuration / afternoonOrdered.size();
				data[13] = afternoonOrdered.get(afternoonOrdered.size() - 1);
				tempIntVisitors.add(3, afternoonOrdered.size());
			} else { // If there's no (usable) data, assume zero.
				data[3] = data[8] = data[13] = 0;
				tempIntVisitors.add(3, 0);
			}
			if (eveningOrdered.size() > 0) { // Stats for the evening.
				data[4] = eveningOrdered.get(0);
				data[9] = eveningDuration / eveningOrdered.size();
				data[14] = eveningOrdered.get(eveningOrdered.size() - 1);
				tempIntVisitors.add(4, eveningOrdered.size());
			} else { // If there's no (usable) data, assume zero.
				data[4] = data[9] = data[14] = 0;
				tempIntVisitors.add(4, 0);
			}
		} catch (NullPointerException e) {
			e.getLocalizedMessage();
		}

		Statistic result = new Statistic(list.size(), "Details visit durations", labels, data);
		this.list.add(result);
	}

	private void statDurationsIndividually(ArrayList<PersonObject> personList) {
		int[] data = new int[personList.size()];
		String[] labels = new String[personList.size()];
		for (int i = 0; i < personList.size(); i++) {
			data[i] = personList.get(i).getEnd().secondsTotal() - personList.get(i).getStart().secondsTotal();
			labels[i] = "Person " + personList.get(i).getPersonId();
		}
		Statistic result = new Statistic(list.size(), "Individual visit durations", labels, data);
		this.list.add(result);
	}

	private void statVisitorDetails(ArrayList<PersonObject> personList) {
		int[] data = new int[5];
		String[] labels = new String[5];

		labels[0] = "Total number of visitors";
		labels[1] = "Morning visitors";
		labels[2] = "Noon visitors";
		labels[3] = "Afternoon visitors";
		labels[4] = "Evening visitors";
		data[0] = tempIntVisitors.get(0); // Stores the total number of visitors.
		data[1] = tempIntVisitors.get(1); // Stores the morning visitors.
		data[2] = tempIntVisitors.get(2); // Stores the noon visitors.
		data[3] = tempIntVisitors.get(3); // Stores the afternoon visitors.
		data[4] = tempIntVisitors.get(4); // Stores the evening visitors.
		tempIntVisitors.clear();
		// To do: visitor length details

		Statistic result = new Statistic(list.size(), "Statistics visitors", labels, data);
		this.list.add(result);
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
