package pl.szczesniak.dominik.tictactoe.consoletictactoeapp.gamehistory.adapter;

import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.gamehistory.domain.GameHistoryStorage;
import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.gamehistory.domain.SingleGameResult;
import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.player.model.PlayerScore;
import pl.szczesniak.dominik.tictactoe.core.singlegame.domain.model.PlayerName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FilePerPlayerGameHistoryStorage implements GameHistoryStorage {


	@Override
	public void store(SingleGameResult singleGameResult) {
		createFile(singleGameResult.getValue());
		final String playerName = singleGameResult.getValue().getValue();
		int playerWins = loadPlayerScore(singleGameResult.getValue()).getValue();

		try {
			BufferedReader br = new BufferedReader(new FileReader(playerName + ".txt"));
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
					br.readLine();
					buffer.append(line + "\n");
					line = String.valueOf(playerWins + 1);
					buffer.append(line + "\n");
			}
			if (!playerIsInFile(singleGameResult.getValue())) {
				buffer.append(singleGameResult.getValue().getValue() + "\n");
				buffer.append(1 + "\n");
			}
			br.close();

			FileOutputStream fileOut = new FileOutputStream(playerName + ".txt");
			fileOut.write(buffer.toString().getBytes());
			fileOut.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PlayerScore loadPlayerScore(final PlayerName playerName) {
		int wins = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(playerName.getValue() + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals(playerName.getValue())) {
					wins = Integer.parseInt(br.readLine());
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new PlayerScore(wins);
	}

	private void createFile(PlayerName name) {
		try {
			File myObj = new File(name.getValue() + ".txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}


	private boolean playerIsInFile(final PlayerName name) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(name.getValue() + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals(name.getValue())) {
					return true;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

}
