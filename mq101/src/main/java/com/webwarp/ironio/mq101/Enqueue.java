package com.webwarp.ironio.mq101;

import io.iron.ironmq.Client;
import io.iron.ironmq.Cloud;
import io.iron.ironmq.Message;
import io.iron.ironmq.Queue;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.iron.ironmq.HTTPException;

/**
 * Enqueue to IronMQ
 */
public class Enqueue {

    public static void main(String[] args) {
        
        System.out.println("Hello World!");

        // Get your Iron.io credentials from the environment
        Map<String, String> env = System.getenv();

        // Create a Client object
        Client client = new Client(env.get("IRON_MQ_PROJECT_ID"), env.get("IRON_MQ_TOKEN"), Cloud.ironAWSUSEast);

        // Get a queue (if it doesn't exist, it will be created when you first post a message)
        Queue queue = client.queue("testqueue");
                
        try {
            // Post a message
            queue.push("hello world!");

            // Get a message
            Message msg = queue.get();
            System.out.println(msg.getBody());

            // Delete a message (you must delete a message when you're done with it 
            // or it will go back on the queue after a timeout)
            queue.deleteMessage(msg);
            
        } catch (IOException ex) {
            Logger.getLogger(Enqueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
