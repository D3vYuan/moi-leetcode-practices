package com.example.katana.Q2001_Q2500;

import java.util.LinkedList;
import java.util.Queue;

class Buyer {
    int buyerId;
    int remainingTickets;

    public Buyer(int buyerId, int remainingTickets) {
        this.buyerId = buyerId;
        this.remainingTickets = remainingTickets;
    }

    public void purchaseTicket() {
        this.remainingTickets -= 1;
        this.remainingTickets = this.remainingTickets < 0 ? 0 : this.remainingTickets;
    }

    public int getBuyerId() {
        return this.buyerId;
    }

    public int getRemainingTickets() {
        return this.remainingTickets;
    }
}

public class P2073_Time_to_Buy_Tickets {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Buyer> messageQueue = new LinkedList<>();

        for (int i = 0; i < tickets.length; i++) {
            Buyer buyer = new Buyer(i, tickets[i]);
            messageQueue.offer(buyer);
        }

        int timeOccurred = 0;
        while (!messageQueue.isEmpty()) {
            Buyer currentBuyer = messageQueue.poll();
            currentBuyer.purchaseTicket();
            timeOccurred += 1;

            if (currentBuyer.getBuyerId() == k && currentBuyer.getRemainingTickets() <= 0) {
                break;
            }

            if (currentBuyer.getRemainingTickets() > 0) {
                messageQueue.offer(currentBuyer);
            }
        }

        return timeOccurred;
    }
}
