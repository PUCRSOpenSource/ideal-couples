package com.company;

import java.util.Comparator;

public class QueueElementComparator implements Comparator<QueueElement> {
    @Override
    public int compare(QueueElement qe1, QueueElement qe2) {
        if (qe1.getPriority() < qe2.getPriority())
            return -1;
        if (qe1.getPriority() > qe2.getPriority())
            return 1;
        return 0;
    }
}
