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

		for (File file : folder.listFiles())
			files.add(file);

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
		return allJson;
	}

	private static void writeCsv(ArrayList<TokimonHandler> handlers, File file) {
		File csv = new File(file + "/team_info.csv");
		try {
			FileWriter writer = new FileWriter(csv);
			writer.write("Team#,From Toki,To Toki,Score,Comment,,Extra\n");

			int teamNum = 1;
			Tokimon toki;
			String id;

			for (TokimonHandler handler : handlers) {

				writer.write("Team " + teamNum++ + ",,,,,,\n");

				for (JsonTokimonTeam team : handler.getTeam()) {

					id = team.getFileTeam().get(team.getFileTeam().size() - 1).getId();

					for (int i = 0; i < team.getFileTeam().size(); i++) {
						toki = team.getFileTeam().get(i);
						// System.out.println(toki.getId());
						writer.write("," + id + ",");
						if (!id.equalsIgnoreCase(toki.getId()))
							writer.write(toki.getId());
						else
							writer.write("-");
						writer.write("," + toki.getScore() + ",\"" + toki.getComment() + "\",,");
						if (id.equalsIgnoreCase(toki.getId()))
							writer.write(team.getExtCom());
						writer.write("\n");
					}

				}

				if (!handler.equals(handlers.get(handlers.size() - 1)))
					writer.write(",,,,,,\n");

			}

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Error: Invalid number of arguments. Expected 2 arguments");
			return;
		}

		Gson gson = new Gson();
		File inFolder = new File(args[0]); // ./as2/src/main/java/ca/cmpt213/as2/InputTestDataSets/3-CoupleSmallTeams
		File outFolder = new File(args[1]); // ./as2/src/main/java/ca/cmpt213/as2/AnalysisResults_v2/3-CoupleSmallTeams

		if (!inFolder.exists() || !outFolder.exists()) {

			System.out.println("Error: Directory does not exist");
			System.exit(-1);
		}

		ArrayList<File> allJson = findJson(inFolder);
		if (allJson.isEmpty()) {
			System.out.println("Error: No json files found. Provide json files in specified directory");
			System.exit(-1);
		}

		ArrayList<JsonTokimonTeam> allTokimon = new ArrayList<>();

		for (File json : allJson) {

			try (Reader reader = new FileReader(json)) {
				allTokimon.add((gson.fromJson(reader, JsonTokimonTeam.class)));
			} catch (IOException e) {
				System.out.println("Error: Reading");
				System.exit(-1);
			}

		}

		TeamSorter sort = new TeamSorter(allTokimon);
		writeCsv(sort.divideTeams(), outFolder);

	}
}
