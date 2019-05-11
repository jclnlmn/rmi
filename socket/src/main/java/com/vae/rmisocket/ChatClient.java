package com.vae.rmisocket;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] argv) {
        String message = "Proper Usage is: java program filename [-host=address_ip][ -port=port_number ]\n";
        HashMap<String, String> hmArgs = new HashMap<String, String>();
        hmArgs.put("host", "127.0.0.1");
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
        host = hmArgs.get("host");

        try {
            System.setProperty("java.security.policy", "file:./security.policy");
            System.setProperty("java.rmi.server.hostname", host);
            System.setSecurityManager(new SecurityManager());
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Your username and press Enter:");
            String name = s.nextLine().trim();
            ChatInterface client = new Chat(name);

            String url = "rmi://" + host + "/live";
            
            ChatInterface server = (ChatInterface) Naming.lookup("rmi://" + host + "/live");
            String msg = "[" + client.getName() + "] got connected on " + url;
            server.send(msg);
            System.out.println("[System] Chat Remote Object is ready:");
            server.setClient(client);

            while (true) {
                msg = s.nextLine().trim();
                msg = "[" + client.getName() + "] " + msg;
                if( !s.nextLine().trim().equals("") )
                    server.send(msg);
            }

        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}
