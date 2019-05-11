package com.vae.rmisocket;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class ChatServer {

    public static void main(String[] argv) {
        String message = "Proper Usage is: java program filename [-host=address_ip][ -port=port_number ]\n";
        HashMap<String, String> hmArgs = new HashMap<String, String>();
        hmArgs.put("host", "127.0.0.1");
        hmArgs.put("port", "1099");
        int port = -1;
        String host = null;

        //Get parameters
        int i = 0;
        while ((i < argv.length)) {
            if (!argv[i].matches("\\-\\w+=.*")) {
                System.out.println("An argurment is not valid:" + argv[i] + "\n" + message);
                System.exit(0);
            }
            String[] cmd = argv[i].substring(1, argv[i].length()).split("=");
            hmArgs.put(cmd[0], cmd[1]);
            i++;
        }
        port = Integer.parseInt(hmArgs.get("port"));
        host = hmArgs.get("host");

        try {
            System.setProperty("java.security.policy", "file:./security.policy");
            System.setProperty("java.rmi.server.hostname", host);
            LocateRegistry.createRegistry(port);

            System.setSecurityManager(new SecurityManager());
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Your username and press Enter:");
            String name = s.nextLine().trim();

            Chat server = new Chat(name);
            String url = "rmi://" + host + "/live";
            Naming.rebind( url , server);
            System.out.println("[System] Binding " + url + " port:" + port );

            System.out.println("[System] Chat Remote Object is ready:");

            while (true) {
                String msg = s.nextLine().trim();
                if (server.getClient() != null) {
                    ChatInterface client = server.getClient();
                    msg = "[" + server.getName() + "] " + msg;
                    if( !s.nextLine().trim().equals("") )
                        client.send(msg);
                }
            }

        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e.getMessage());
        }
    }
}
