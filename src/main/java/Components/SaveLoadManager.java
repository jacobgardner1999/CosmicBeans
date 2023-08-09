package Components;

import java.io.*;
public class SaveLoadManager {
    public static void saveGame(Player player, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(player);
            System.out.println("Game Saved.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Player loadGame(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Player loadedPlayer = (Player) inputStream.readObject();
            System.out.println("Game loaded.");
            return loadedPlayer;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
