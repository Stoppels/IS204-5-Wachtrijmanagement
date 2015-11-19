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
		Integer[] data = new Integer[15];
		String[] labels = {
			"Minimum duration", "Minimum morning", "Minimum noon", "Minimum afternoon", "Minimum evening",
			"Average duration", "Average morning", "Average noon", "Average afternoon", "Average evening",
			"Maximum duration", "Maximum morning", "Maximum noon", "Maximum afternoon", "Maximum evening"
		};

		int morningDuration = 0;
		int noonDuration = 0;
		int afternoonDuration = 0;
		int eveningDuration = 0;
		int temp[] = Arrays.copyOf(list.get(0).getStatData(), list.get(0).getStatData().length); // Fetch duration per person.
		ArrayList<Integer> morningOrdered = new ArrayList<>();
		ArrayList<Integer> noonOrdered = new ArrayList<>();
		ArrayList<Integer> afternoonOrdered = new ArrayList<>();
		ArrayList<Integer> eveningOrdered = new ArrayList<>();

		// Sum and store all visitors' visit durations.
		for (int i = 0; i < personList.size(); i++) {
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
		data[5] = totalVisitors > 0 ? totalDuration / totalVisitors : 0;

		// Sort daypart duration data ascending.
		Collections.sort(morningOrdered);
		Collections.sort(noonOrdered);
		Collections.sort(afternoonOrdered);
		Collections.sort(eveningOrdered);

		// Stores the shortest, average and longest durations per daypart.
		if (morningOrdered.size() > 0) { // Stats for the morning.
			data[1] = morningOrdered.get(0); // Minimum duration.
			data[6] = morningDuration / morningOrdered.size(); // Average duration.
			data[11] = morningOrdered.get(morningOrdered.size() - 1); // Maximum duration.
			tempIntVisitors.add(0, morningOrdered.size());
		} else { // If there're no (usable) data, assume zero. Hide daypart.
			data[1] = data[6] = data[11] = null;
			tempIntVisitors.add(0, 0);
		}
		if (noonOrdered.size() > 0) { // Stats for noon.
			data[2] = noonOrdered.get(0); // Minimum duration.
			data[7] = noonDuration / noonOrdered.size(); // Average duration.
			data[12] = noonOrdered.get(noonOrdered.size() - 1); // Maximum duration.
			tempIntVisitors.add(1, noonOrdered.size());
		} else { // If there're no (usable) data, assume zero. Hide daypart.
			data[2] = data[7] = data[12] = null;
			tempIntVisitors.add(1, 0);
		}
		if (afternoonOrdered.size() > 0) { // Stats for the afternoon.
			data[3] = afternoonOrdered.get(0); // Minimum duration.
			data[8] = afternoonDuration / afternoonOrdered.size(); // Average duration.
			data[13] = afternoonOrdered.get(afternoonOrdered.size() - 1); // Maximum duration.
			tempIntVisitors.add(2, afternoonOrdered.size());
		} else { // If there're no (usable) data, assume zero. Hide daypart.
			data[3] = data[8] = data[13] = null;
			tempIntVisitors.add(2, 0);
		}
		if (eveningOrdered.size() > 0) { // Stats for the evening.
			data[4] = eveningOrdered.get(0); // Minimum duration.
			data[9] = eveningDuration / eveningOrdered.size(); // Average duration.
			data[14] = eveningOrdered.get(eveningOrdered.size() - 1); // Maximum duration.
			tempIntVisitors.add(3, eveningOrdered.size());
		} else { // If there're no (usable) data, assume zero. Hide daypart.
			data[4] = data[9] = data[14] = null;
			tempIntVisitors.add(3, 0);
		}

		// Filter out the nulls. Null means no visitors that daypart. Will be omitted from chart.
		int[] tempData = new int[data.length];
		String[] tempLabels = new String[data.length];
		int index = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) { // If data[i] is empty, this will skip that daypart section.
				tempData[index] = data[i];
				tempLabels[index++] = labels[i];
			}
		}
		int[] finalData = Arrays.copyOfRange(tempData, 0, index);
		String[] finalLabels = Arrays.copyOf(tempLabels, index);
		Statistic result = new Statistic(list.size(), "Daily overview", finalLabels, finalData);
		this.list.add(result);
	}

	private void statDurationsIndividually(ArrayList<PersonObject> personList) {
		int[] data = new int[personList.size()];
		String[] labels = new String[personList.size()];
		for (int i = 0; i < personList.size(); i++) {
			data[i] = personList.get(i).getEnd().secondsTotal() - personList.get(i).getStart().secondsTotal();
			labels[i] = "Person " + personList.get(i).getPersonId();
		}
		Statistic result = new Statistic(list.size(), "Time per visitor", labels, data);
		this.list.add(result);
	}

	private void statVisitorDetails(ArrayList<PersonObject> personList) {
		int[] data = new int[tempIntVisitors.size() + 1];
		String[] labels = {"Morning visitors", "Noon visitors", "Afternoon visitors", "Evening visitors", "Total number of visitors"};

		// Stores the total number of visitors for dayparts and total.
		for (int i = 0; i < data.length - 1; i++) {
			data[i] = tempIntVisitors.get(i);
		}
		tempIntVisitors.clear();
		data[4] = data[0] + data[1] + data[2] + data[3];

		// Filter out the nulls. Null means no visitors that daypart. Will be omitted from chart.
		int[] tempData = new int[data.length];
		String[] tempLabels = new String[data.length];
		int index = 0;
		for (int i = 0; i < data.length; i++) {
			if (i != 0) {
				tempData[index] = data[i];
				tempLabels[index++] = labels[i];
			}
		}
		int[] finalData = Arrays.copyOfRange(tempData, 0, index);
		String[] finalLabels = Arrays.copyOf(tempLabels, index);
		Statistic result = new Statistic(list.size(), "Number of visitors", finalLabels, finalData);
		this.list.add(result);
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
