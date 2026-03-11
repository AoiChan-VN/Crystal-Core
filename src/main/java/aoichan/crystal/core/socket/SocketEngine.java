package aoichan.crystal.core.socket;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

// [!] Code: Socket system logic
public class SocketEngine {

    public static List<SocketData> getSockets(
            ItemStack item
    ) {

        String json =
                SocketItemUtil.getSocketData(item);

        List<SocketData> list =
                new ArrayList<>();

        if (json == null || json.isEmpty())
            return list;

        // simple parse
        json = json.replace("[","")
                .replace("]","");

        String[] entries =
                json.split("},");

        for (String entry : entries) {

            entry = entry
                    .replace("{","")
                    .replace("}","");

            String[] parts =
                    entry.split(",");

            String id = null;
            int level = 1;

            for (String part : parts) {

                String[] kv =
                        part.split(":");

                if (kv.length != 2)
                    continue;

                if (kv[0].contains("id"))
                    id = kv[1].replace("\"","");

                if (kv[0].contains("level"))
                    level = Integer.parseInt(
                            kv[1]
                    );
            }

            if (id != null)
                list.add(
                        new SocketData(
                                id,
                                level
                        )
                );
        }

        return list;
    }

    public static void addGem(

            ItemStack item,
            String gemId,
            int level

    ) {

        List<SocketData> list =
                getSockets(item);

        list.add(
                new SocketData(
                        gemId,
                        level
                )
        );

        saveSockets(
                item,
                list
        );
    }

    public static void saveSockets(

            ItemStack item,
            List<SocketData> sockets

    ) {

        StringBuilder json =
                new StringBuilder();

        json.append("[");

        for (int i = 0; i < sockets.size(); i++) {

            SocketData s =
                    sockets.get(i);

            json.append("{");

            json.append("\"id\":\"")
                    .append(s.getGemId())
                    .append("\",");

            json.append("\"level\":")
                    .append(s.getLevel());

            json.append("}");

            if (i < sockets.size() - 1)
                json.append(",");
        }

        json.append("]");

        SocketItemUtil.setSocketData(
                item,
                json.toString()
        );
    }

} 
