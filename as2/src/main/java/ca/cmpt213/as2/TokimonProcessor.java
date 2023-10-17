package ca.cmpt213.as2;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TokimonProcessor {

	private static ArrayList<File> findJson(File folder) {
		ArrayList<File> allJson = new ArrayList<File>();
		FileFilter filterJson = new FileFilter() {
			@Override
			public boolean accept(File pathName) {
				if (pathName.getName().endsWith(".json"))
					return true;
				return false;
			}
		};
		Queue<File> files = new LinkedList<File>();

		for (File file : folder.listFiles()) {
			System.out.println(file.getName());
			files.add(file);
		}

		while (!files.isEmpty()) {
			File file = files.poll();
			if (filterJson.accept(file))
				allJson.add(file);
			else {
				if (file.isDirectory()) {
					for (File each : file.listFiles())
						files.add(each);
				}
			}
		}
		for (File each : allJson)
			System.out.println(each.getName());
		return allJson;
	}

	private static void writeCsv(TokimonHandler[] handlers, File file) {
		File csv = new File("info.csv");
		try {
			FileWriter writer = new FileWriter(csv);
			writer.write("Team#,From Toki,To Toki,,Score,Comment,,Extra\nTeam 1,,,,,,,\n");

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// if (args.length != 2) {
		// System.out.println("Error: Invalid number of arguments. Expected 2
		// arguments");
		// return;
		// }

		Gson gson = new Gson();
		File inFolder = new File("./as2/src/main/java/ca/cmpt213/as2/InputTestDataSets/3-CoupleSmallTeams");
		File outFolder = new File("./as2/src/main/java/ca/cmpt213/as2/AnalysisResults_v2/1-OneTeamOneToki");

		if (!inFolder.exists() || !outFolder.exists()) {

			System.out.println("Error: Directory does not exist");
			System.exit(-1);
		}

		ArrayList<File> allJson = findJson(inFolder);
		if (allJson.isEmpty()) {
			System.out.println("Error: No json files found. Provide json files in specified directory");
			System.exit(-1);
		}

		int j = 0;
		JsonTTeam[] allTokimon = new JsonTTeam[allJson.size()];
		for (File json : allJson) {
			for (int i = j; i < allTokimon.length; i++) {
				try (Reader reader = new FileReader(json)) {
					allTokimon[i] = (gson.fromJson(reader, JsonTTeam.class));
				} catch (IOException e) {
					System.out.println("Error: Reading");
					System.exit(-1);
				}
			}
			j++;
		}

		TeamSorter sort = new TeamSorter(allTokimon);
		if (sort.countTeams() == -1) {
			System.out.println("Error: Could not read team from ID");
			System.exit(-1);
		}
		System.out.println("this" + sort.countTeams());
		writeCsv(null, outFolder);
	}
}
